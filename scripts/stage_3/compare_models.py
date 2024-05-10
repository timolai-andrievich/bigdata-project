"""
This script loads trained models and their predictions,
and then evaluates them and saves the report
"""
from pyspark.ml.regression import LinearRegressionModel, GBTRegressionModel

from scripts.stage_3.train_models import (
    create_session,
    get_rmse_evaluator,
    get_r2_evaluator,
)


def main() -> None:
    """Entry point for comparison script"""
    spark = create_session("comparing models")
    model1 = LinearRegressionModel.load(  # pylint: disable=no-member
        "project/models/model1"
    )
    model2 = GBTRegressionModel.load("project/models/model2")

    model1_predictions = spark.read.csv(
        "project/output/model1_predictions.csv", header=True, inferSchema=True
    )
    model2_predictions = spark.read.csv(
        "project/output/model2_predictions.csv", header=True, inferSchema=True
    )

    rmse_evaluator, r2_evaluator = get_rmse_evaluator(), get_r2_evaluator()

    rmse_1 = rmse_evaluator.evaluate(model1_predictions)
    rmse_2 = rmse_evaluator.evaluate(model2_predictions)

    r2_score_1 = r2_evaluator.evaluate(model1_predictions)
    r2_score_2 = r2_evaluator.evaluate(model2_predictions)

    models = [[str(model1), rmse_1, r2_score_1], [str(model2), rmse_2, r2_score_2]]

    comparison_report = spark.createDataFrame(models, ["model", "RMSE", "R2"])

    comparison_report.coalesce(1).write.mode("overwrite").format("csv").option(
        "sep", ","
    ).option("header", "true").save("project/output/evaluation.csv")


if __name__ == "__main__":
    main()
