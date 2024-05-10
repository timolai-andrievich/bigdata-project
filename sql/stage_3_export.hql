USE team31_projectdb;

DROP TABLE IF EXISTS evaluation;
CREATE TABLE IF NOT EXISTS evaluation
(
    model varchar(256),
    RMSE  float,
    R2    float
)
    row format delimited fields terminated by ','
    tblproperties ("skip.header.line.count" = "1");
LOAD DATA INPATH '../team31/project/output/evaluation.csv' OVERWRITE INTO TABLE evaluation;

DROP TABLE IF EXISTS model1_predictions;
CREATE TABLE IF NOT EXISTS model1_predictions
(
    label      float,
    prediction float
)
    row format delimited fields terminated by ','
    tblproperties ("skip.header.line.count" = "1");
LOAD DATA INPATH '../team31/project/output/model1_predictions.csv' OVERWRITE INTO TABLE model1_predictions;

DROP TABLE IF EXISTS model2_predictions;
CREATE TABLE IF NOT EXISTS model2_predictions
(
    label      float,
    prediction float
)
    row format delimited fields terminated by ','
    tblproperties ("skip.header.line.count" = "1");
LOAD DATA INPATH '../team31/project/output/model2_predictions.csv' OVERWRITE INTO TABLE model2_predictions;
