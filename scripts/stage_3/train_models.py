"""
This script allows to train one of two models with and without cross-validation,
tune its hyperparameters and evaluate both variants on test data
"""
from typing import Tuple

import numpy as np
from pyspark.sql import functions as F, SparkSession, DataFrame
from pyspark.ml.linalg import Vectors, VectorUDT
from pyspark.ml.regression import LinearRegression
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.ml.regression import GBTRegressor

TEAM_ID = "team31"


def create_session(session_name: str = "model training") -> SparkSession:
    """Returns new SparkSession"""
    return (
        SparkSession.builder.appName(f"{TEAM_ID} - spark ML - {session_name}")
        .master("yarn")
        .getOrCreate()
    )


def load_data(spark: SparkSession, is_train: bool = True) -> DataFrame:
    """
    Loads required DF (train or test) from HDFS,
    converts its features to vectors and returns it
    """
    to_vector = F.udf(Vectors.dense, VectorUDT())

    if is_train:
        dataset = spark.read.json("project/data/train")
    else:
        dataset = spark.read.json("project/data/test")

    return dataset.selectExpr("features.values as features", "label").withColumn(
        "features", to_vector("features")
    )


def get_rmse_evaluator() -> RegressionEvaluator:
    """Returns RMSE evaluator"""
    return RegressionEvaluator(
        metricName="rmse", labelCol="label", predictionCol="prediction"
    )


def get_r2_evaluator() -> RegressionEvaluator:
    """Returns R2 evaluator"""
    return RegressionEvaluator(
        metricName="r2", labelCol="label", predictionCol="prediction"
    )


def evaluate_predictions(predictions: DataFrame) -> Tuple[float, float]:
    """Evaluates predictions and returns RMSE and R2"""
    rmse = get_rmse_evaluator().evaluate(predictions)
    r2_score = get_r2_evaluator().evaluate(predictions)
    return rmse, r2_score


def train_first_model(spark: SparkSession) -> None:
    """Train linear regression model with and without hyperparameters tuning"""
    train_data = load_data(spark, is_train=True)
    test_data = load_data(spark, is_train=False)

    lin_reg = LinearRegression()
    lr_model = lin_reg.fit(train_data)
    print(
        "Linear Regression with default parameters:",
        evaluate_predictions(lr_model.transform(test_data)),
    )

    grid = ParamGridBuilder()
    grid = (
        grid.addGrid(lr_model.aggregationDepth, [2, 3, 4])
        .addGrid(lr_model.regParam, np.logspace(1e-3, 1e-1))
        .build()
    )

    cv_wrapper = CrossValidator(
        estimator=lin_reg,
        estimatorParamMaps=grid,
        evaluator=get_r2_evaluator(),
        parallelism=5,
        numFolds=3,
    )

    cv_model = cv_wrapper.fit(train_data)
    model1 = cv_model.bestModel

    predictions = model1.transform(test_data)
    print("Tuned Linear Regression:", evaluate_predictions(predictions))

    model1.write().overwrite().save("project/models/model1")

    predictions.select("label", "prediction").coalesce(1).write.mode(
        "overwrite"
    ).format("csv").option("sep", ",").option("header", "true").save(
        "project/output/model1_predictions.csv"
    )


def train_second_model(spark: SparkSession) -> None:
    """Train gradient boosting model with and without hyperparameters tuning"""
    train_data = load_data(spark, is_train=True)
    test_data = load_data(spark, is_train=False)

    gbt = GBTRegressor(seed=42)
    gbt_model = gbt.fit(train_data)
    print(
        "GBT Regressor with default parameters:",
        evaluate_predictions(gbt_model.transform(test_data)),
    )

    grid = ParamGridBuilder()
    grid = (
        grid.addGrid(gbt_model.maxDepth, [2, 5, 10, 15])
        .addGrid(gbt_model.maxBins, [32, 128])
        .build()
    )

    cv_wrapper = CrossValidator(
        estimator=gbt,
        estimatorParamMaps=grid,
        evaluator=get_r2_evaluator(),
        parallelism=5,
        numFolds=3,
    )

    cv_model = cv_wrapper.fit(train_data)
    model2 = cv_model.bestModel

    predictions = model2.transform(test_data)
    print("Tuned GBT Regressor:", evaluate_predictions(predictions))

    model2.write().overwrite().save("project/models/model2")

    predictions.select("label", "prediction").coalesce(1).write.mode(
        "overwrite"
    ).format("csv").option("sep", ",").option("header", "true").save(
        "project/output/model2_predictions.csv"
    )


def main() -> None:
    """Trains both models and saves them and predictions to HDFS"""
    spark = create_session()
    train_first_model(spark)
    train_second_model(spark)


if __name__ == "__main__":
    main()
