SET hive.execution.engine=tez;

USE team31_projectdb;

DROP TABLE IF EXISTS q4_results;
CREATE TABLE q4_results(
  event_id STRING,
  transaction_value DOUBLE
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q4';

INSERT INTO q4_results(event_id, transaction_value)
SELECT event_id, transaction_value FROM mints
-- Uniformly sample roughly 100,000 items from the table
WHERE rand() < (SELECT 100000.0 / count(*) FROM mints);

INSERT OVERWRITE DIRECTORY 'project/output/q4' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q4_results;
