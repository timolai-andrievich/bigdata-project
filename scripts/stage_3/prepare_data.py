"""
Data preparation scripts, includes feature selection,
feature engineering and feature extraction.
"""
from typing import List

import math

from pyspark.sql import SparkSession, DataFrame
from pyspark.sql import functions as F
import pyspark.sql.types as T
from pyspark.ml.feature import Tokenizer, Word2Vec, VectorAssembler, StandardScaler
from pyspark import keyword_only
from pyspark.ml import Transformer
from pyspark.ml.param.shared import (
    HasInputCol,
    HasOutputCol,
    Param,
    Params,
    TypeConverters,
)
from pyspark.ml.util import DefaultParamsReadable, DefaultParamsWritable
from pyspark.ml import Pipeline

TEAM_ID = "team31"
LAST_N_TX = 10
DB_NAME = f"{TEAM_ID}_projectdb"
# location of your Hive database in HDFS
WAREHOUSE = "project/hive/warehouse"


class DateCyclicalEncodingTransformer(  # pylint: disable=too-many-ancestors
    Transformer, HasInputCol, HasOutputCol, DefaultParamsReadable, DefaultParamsWritable
):
    """
    Custom date transformer for cyclical encoding.
    1. Splits date into year, month, day, hour, minutes, and seconds
    2. Encodes all the features except year as sin and cos components
    """

    input_col = Param(
        Params._dummy(),  # pylint: disable=protected-access
        "input_col",
        "input column name.",
        typeConverter=TypeConverters.toString,
    )
    output_col = Param(
        Params._dummy(),  # pylint: disable=protected-access
        "output_col",
        "output column name.",
        typeConverter=TypeConverters.toString,
    )

    @keyword_only
    def __init__(
        self,
        input_col: str = "input",  # pylint: disable=unused-argument
        output_col: str = "output",  # pylint: disable=unused-argument
    ):
        super().__init__()
        self._setDefault(input_col=None, output_col=None)
        kwargs = self._input_kwargs  # pylint: disable=no-member
        self.set_params(**kwargs)

    @keyword_only
    def set_params(
        self,
        input_col: str = "input",  # pylint: disable=unused-argument
        output_col: str = "output",  # pylint: disable=unused-argument
    ):
        """Some magic function I copied from the original implementation"""
        kwargs = self._input_kwargs  # pylint: disable=no-member
        self._set(**kwargs)

    def get_input_col(self):
        """Safer getter for input_col"""
        return self.getOrDefault(self.input_col)

    def get_output_col(self):
        """Safer getter for output_col"""
        return self.getOrDefault(self.output_col)

    def _transform(self, dataset):
        input_col = self.get_input_col()
        output_col = self.get_output_col()
        dataset = dataset.withColumn(output_col + "_year", F.year(F.col(input_col)))
        for col, val_count in (
            ("month", 12),
            ("day", 31),
            ("hour", 24),
            ("minute", 60),
            ("second", 60),
        ):
            dataset = dataset.withColumn(
                output_col + f"_{col}_sin",
                F.sin(2 * math.pi * F.expr(f"{col}({input_col})") / val_count),
            ).withColumn(
                output_col + f"_{col}_cos",
                F.cos(2 * math.pi * F.expr(f"{col}({input_col})") / val_count),
            )
        return dataset

    def get_all_column_names(self):
        """Returns all output column names"""
        output_col = self.get_output_col()
        return (
            [output_col + "_year"]
            + [
                output_col + f"_{col}_sin"
                for col in ("month", "day", "hour", "minute", "second")
            ]
            + [
                output_col + f"_{col}_cos"
                for col in ("month", "day", "hour", "minute", "second")
            ]
        )


def make_pipeline(date_cols: List[str], text_cols: List[str]) -> Pipeline:
    """Returns a pipeline for feature extraction"""
    tokenizer = Tokenizer(inputCol=text_cols[0], outputCol=text_cols[0] + "_tokens")
    word2vec = Word2Vec(
        vectorSize=16,
        minCount=1,
        inputCol=tokenizer.getOutputCol(),
        outputCol=text_cols[0] + "_w2v",
    )

    date_transformers = [
        DateCyclicalEncodingTransformer(input_col=col, output_col="encoded_" + col)
        for col in date_cols
    ]
    cols_to_assemble = [text_cols[0] + "_w2v"] + sum(
        (dt.get_all_column_names() for dt in date_transformers), []
    )

    assembler = VectorAssembler(inputCols=cols_to_assemble, outputCol="raw_features")
    scaler = StandardScaler(
        inputCol="raw_features", outputCol="features", withMean=True, withStd=True
    )

    pipeline = Pipeline(
        stages=[tokenizer, word2vec] + date_transformers + [assembler, scaler]
    )
    return pipeline


def make_features(spark: SparkSession) -> DataFrame:
    """Returns the dataframe with all selected and created features"""
    mints = (
        spark.read.format("avro")
        .table(f"{DB_NAME}.mints")
        .select("token_id", "timestamp", "nft_address", "transaction_value")
    )

    transfers = (
        spark.read.format("avro")
        .table(f"{DB_NAME}.transfers")
        .select("token_id", "timestamp", "transaction_value")
    )

    nfts = spark.read.format("avro").table(f"{DB_NAME}.nfts").select("address", "name")

    contract_stats = mints.groupBy("nft_address").agg(
        F.countDistinct("token_id").alias("num_tokens"),
        F.avg("transaction_value").alias("avg_mint_price"),
        F.max("transaction_value").alias("max_mint_price"),
        F.min("transaction_value").alias("min_mint_price"),
        F.min("timestamp").alias("first_mint_date"),
        F.max("timestamp").alias("last_mint_date"),
    )

    def _get_last_n_txs(arr, last_n):
        return arr[-last_n:]

    get_last_n_txs = F.udf(
        _get_last_n_txs,
        T.ArrayType(
            T.StructType(
                [
                    T.StructField("timestamp", T.LongType()),
                    T.StructField("transaction_value", T.DoubleType()),
                ]
            )
        ),
    )

    nft_history = (
        transfers.groupBy("token_id")
        .agg(
            F.sort_array(
                F.collect_list(F.struct("timestamp", "transaction_value"))
            ).alias("tx_data"),
        )
        .withColumn(
            "tx_data_except_last", F.expr("slice(tx_data, 1, size(tx_data) - 1)")
        )
        .withColumn("last_tx", F.element_at("tx_data", -1))
        .withColumn("last_tx_value", F.col("last_tx.transaction_value"))
        .drop("last_tx")
        .drop("tx_data")
        # Overall stats (excluding last tx)
        .withColumn("tx_count", F.size("tx_data_except_last"))
        .withColumn(
            "min_tx_value", F.array_min("tx_data_except_last.transaction_value")
        )
        .withColumn(
            "max_tx_value", F.array_max("tx_data_except_last.transaction_value")
        )
        .withColumn(
            "first_tx_timestamp", F.element_at("tx_data_except_last.timestamp", 1)
        )
        .withColumn(
            "last_tx_timestamp", F.element_at("tx_data_except_last.timestamp", -1)
        )
        .withColumn(
            "last_n_transactions",
            get_last_n_txs("tx_data_except_last", F.lit(LAST_N_TX)),
        )
        .drop("tx_data_except_last")
        # History for the last N transactions
        .withColumn("tx_values", F.col("last_n_transactions.transaction_value"))
        .withColumn("tx_timestamps", F.col("last_n_transactions.timestamp"))
        .drop("last_n_transactions")
    )

    features = (
        mints.selectExpr(
            "nft_address",
            "token_id",
            "timestamp as mint_timestamp",
            "transaction_value as mint_tx_value",
        )
        .join(contract_stats, on="nft_address", how="left")
        .join(nft_history, on="token_id", how="left")
        .join(nfts, on=(mints.nft_address == nfts.address), how="left")
        .drop("nft_address", "address")
    )
    return features


def main() -> None:
    """Entry point for data preparation script"""
    spark = (
        SparkSession.builder.appName(f"{TEAM_ID} - spark ML - data preparation")
        .master("yarn")
        .config("hive.metastore.uris", "thrift://hadoop-02.uni.innopolis.ru:9883")
        .config("spark.sql.warehouse.dir", WAREHOUSE)
        .config("spark.sql.avro.compression.codec", "snappy")
        .enableHiveSupport()
        .getOrCreate()
    )

    features = make_features(spark)

    features = features.withColumnRenamed("last_tx_value", "label")

    filtered_features = features.na.drop()

    date_cols = [
        "mint_timestamp",
        "first_mint_date",
        "last_mint_date",
        "first_tx_timestamp",
        "last_tx_timestamp",
    ]
    text_cols = ["name"]

    filtered_features_with_dt = filtered_features.selectExpr(
        *(
            f"from_unixtime({col}) as {col}" if col in date_cols else col
            for col in filtered_features.columns
        )
    )

    pipeline = make_pipeline(date_cols, text_cols)

    pipeline_model = pipeline.fit(filtered_features_with_dt)
    transformed_features = pipeline_model.transform(filtered_features_with_dt).select(
        "features", "label"
    )
    (train_data, test_data) = transformed_features.randomSplit([0.7, 0.3], seed=42)
    train_data.coalesce(1).write.mode("overwrite").format("json").save(
        "project/data/train"
    )

    test_data.coalesce(1).write.mode("overwrite").format("json").save(
        "project/data/test"
    )


if __name__ == "__main__":
    main()
