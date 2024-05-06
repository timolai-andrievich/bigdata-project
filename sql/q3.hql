SET hive.execution.engine=spark;

USE team31_projectdb;

DROP TABLE IF EXISTS q3_results;
CREATE TABLE q3_results(
  event_id STRING,
  transaction_value DOUBLE
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q3';

INSERT INTO q3_results(event_id, transaction_value)
SELECT event_id, transaction_value FROM transfers
-- Uniformly sample roughly 100,000 items from the table
WHERE rand() < (SELECT 100000.0 / count(*) FROM transfers);

INSERT OVERWRITE DIRECTORY 'project/output/q3' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q3_results;
