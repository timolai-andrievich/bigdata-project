"""
This script loads trained models and their predictions,
and then evaluates them and saves the report
"""
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.sql import SparkSession

TEAM_ID = "team31"


def main() -> None:
    """Entry point for comparison script"""
    spark = (
        SparkSession.builder.appName(f"{TEAM_ID} - spark ML - comparing models")
        .master("yarn")
        .getOrCreate()
    )

    model1_predictions = spark.read.csv(
        "project/output/model1_predictions.csv", header=True, inferSchema=True
    )
    model2_predictions = spark.read.csv(
        "project/output/model2_predictions.csv", header=True, inferSchema=True
    )

    rmse_evaluator = RegressionEvaluator(
        labelCol="label", predictionCol="prediction", metricName="rmse"
    )
    r2_evaluator = RegressionEvaluator(
        labelCol="label", predictionCol="prediction", metricName="r2"
    )

    rmse_1 = rmse_evaluator.evaluate(model1_predictions)
    rmse_2 = rmse_evaluator.evaluate(model2_predictions)

    r2_score_1 = r2_evaluator.evaluate(model1_predictions)
    r2_score_2 = r2_evaluator.evaluate(model2_predictions)

    models = [
        ["Linear Regression", rmse_1, r2_score_1],
        ["Gradient Boosted Trees", rmse_2, r2_score_2],
    ]

    comparison_report = spark.createDataFrame(models, ["model", "RMSE", "R2"])

    comparison_report.coalesce(1).write.mode("overwrite").format("csv").option(
        "sep", ","
    ).option("header", "true").save("project/output/evaluation.csv")


if __name__ == "__main__":
    main()
