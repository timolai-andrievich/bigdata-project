USE team31_projectdb;

DROP TABLE IF EXISTS q1_results;
CREATE TABLE q1_results(
  event_id STRING,
  `timestamp` INT
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q1';

INSERT INTO q1_results(event_id, `timestamp`)
SELECT event_id, `timestamp` FROM transfers
-- Uniformly sample (roughly 100,000 items from the table)
WHERE rand() < (SELECT 100000.0 / count(*) FROM transfers);

INSERT OVERWRITE DIRECTORY 'project/output/q1' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q1_results;
