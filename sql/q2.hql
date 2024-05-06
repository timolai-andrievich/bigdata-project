USE team31_projectdb;

DROP TABLE IF EXISTS q2_results;
CREATE TABLE q2_results(
  event_id STRING,
  `timestamp` INT
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q2';

INSERT INTO q2_results(event_id, `timestamp`)
SELECT event_id, `timestamp` FROM mints
-- Uniformly sample (roughly 100,000 items from the table)
WHERE rand() < (SELECT 100000.0 / count(*) FROM mints);

INSERT OVERWRITE DIRECTORY 'project/output/q2' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q2_results;
