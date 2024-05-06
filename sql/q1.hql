USE team31_projectdb;

DROP TABLE IF EXISTS q1_results;
CREATE TABLE q1_results(
  token_id STRING,
  timestamp_ INT
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q1';

INSERT INTO q1_results(token_id, timestamp_)
SELECT token_id, timestamp from transfers;

INSERT OVERWRITE DIRECTORY 'project/output/q1' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q1_results;
