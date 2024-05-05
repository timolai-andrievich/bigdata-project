-- Setting Hive options
SET hive.exec.dynamic.partition = true;
SET hive.exec.dynamic.partition.mode = nonstrict;

-- (Re)create the team database
DROP DATABASE IF EXISTS team31_projectdb CASCADE;
CREATE DATABASE team31_projectdb LOCATION "project/hive/warehouse";
USE team31_projectdb;

-- Import tables
CREATE EXTERNAL TABLE checkpoint STORED AS AVRO LOCATION 'project/warehouse/checkpoint' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/checkpoint.avsc');
CREATE EXTERNAL TABLE current_market_values STORED AS AVRO LOCATION 'project/warehouse/current_market_values' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/current_market_values.avsc');
CREATE EXTERNAL TABLE current_owners STORED AS AVRO LOCATION 'project/warehouse/current_owners' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/current_owners.avsc');
CREATE EXTERNAL TABLE market_values_distribution STORED AS AVRO LOCATION 'project/warehouse/market_values_distribution' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/market_values_distribution.avsc');
CREATE EXTERNAL TABLE mint_holding_times STORED AS AVRO LOCATION 'project/warehouse/mint_holding_times' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/mint_holding_times.avsc');
CREATE EXTERNAL TABLE mints STORED AS AVRO LOCATION 'project/warehouse/mints' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/mints.avsc');
CREATE EXTERNAL TABLE nfts STORED AS AVRO LOCATION 'project/warehouse/nfts' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/nfts.avsc');
CREATE EXTERNAL TABLE ownership_transitions STORED AS AVRO LOCATION 'project/warehouse/ownership_transitions' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/ownership_transitions.avsc');
CREATE EXTERNAL TABLE transfer_holding_times STORED AS AVRO LOCATION 'project/warehouse/transfer_holding_times' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_holding_times.avsc');
CREATE EXTERNAL TABLE transfers STORED AS AVRO LOCATION 'project/warehouse/transfers' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfers.avsc');
CREATE EXTERNAL TABLE transfers_mints STORED AS AVRO LOCATION 'project/warehouse/transfers_mints' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfers_mints.avsc');
CREATE EXTERNAL TABLE transfer_statistics_by_address STORED AS AVRO LOCATION 'project/warehouse/transfer_statistics_by_address' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_statistics_by_address.avsc');
CREATE EXTERNAL TABLE transfer_values_quantile_10_distribution_per_address STORED AS AVRO LOCATION 'project/warehouse/transfer_values_quantile_10_distribution_per_address' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_values_quantile_10_distribution_per_address.avsc');
CREATE EXTERNAL TABLE transfer_values_quantile_25_distribution_per_address STORED AS AVRO LOCATION 'project/warehouse/transfer_values_quantile_25_distribution_per_address' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_values_quantile_25_distribution_per_address.avsc');
CREATE EXTERNAL TABLE transfer_values_quartile_10_distribution_per_address STORED AS AVRO LOCATION 'project/warehouse/transfer_values_quartile_10_distribution_per_address' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_values_quartile_10_distribution_per_address.avsc');

-- Partition and cluster the current_owners table
DROP TABLE IF EXISTS current_owners_partitioned;
CREATE EXTERNAL TABLE current_owners_partitioned (
  nft_address STRING,
  token_id STRING,
  owner STRING
) PARTITIONED BY (first_address_letter STRING)
CLUSTERED BY (token_id) INTO 10 BUCKETS
STORED AS AVRO LOCATION 'project/hive/warehouse/current_owners_part';

INSERT INTO TABLE current_owners_partitioned PARTITION (first_address_letter)
SELECT *, substr(nft_address, 3, 1) as first_address_letter FROM current_owners;

DROP TABLE current_owners;
ALTER TABLE current_owners_partitioned RENAME TO current_owners;

-- Check if the data can be accessed
SELECT * FROM current_owners LIMIT 10;

-- Bucket all other tables
CREATE EXTERNAL TABLE checkpoint_clustered CLUSTERED BY (event_type) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/checkpoint_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/checkpoint.avsc');
INSERT INTO checkpoint_clustered SELECT * FROM checkpoint;
DROP TABLE checkpoint;
ALTER TABLE checkpoint_clustered RENAME TO checkpoint;

CREATE EXTERNAL TABLE current_market_values_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/current_market_values_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/current_market_values.avsc');
INSERT INTO current_market_values_clustered SELECT * FROM current_market_values;
DROP TABLE current_market_values;
ALTER TABLE current_market_values_clustered RENAME TO current_market_values;

-- CREATE EXTERNAL TABLE current_owners_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/current_owners_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/current_owners.avsc');
-- INSERT INTO current_owners_clustered SELECT * FROM current_owners;
-- DROP TABLE current_owners;
-- ALTER TABLE current_owners_clustered RENAME TO current_owners;

CREATE EXTERNAL TABLE market_values_distribution_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/market_values_distribution_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/market_values_distribution.avsc');
INSERT INTO market_values_distribution_clustered SELECT * FROM market_values_distribution;
DROP TABLE market_values_distribution;
ALTER TABLE market_values_distribution_clustered RENAME TO market_values_distribution;

CREATE EXTERNAL TABLE mint_holding_times_clustered CLUSTERED BY (days) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/mint_holding_times_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/mint_holding_times.avsc');
INSERT INTO mint_holding_times_clustered SELECT * FROM mint_holding_times;
DROP TABLE mint_holding_times;
ALTER TABLE mint_holding_times_clustered RENAME TO mint_holding_times;

CREATE EXTERNAL TABLE mints_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/mints_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/mints.avsc');
INSERT INTO mints_clustered SELECT * FROM mints;
DROP TABLE mints;
ALTER TABLE mints_clustered RENAME TO mints;

CREATE EXTERNAL TABLE nfts_clustered CLUSTERED BY (address) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/nfts_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/nfts.avsc');
INSERT INTO nfts_clustered SELECT * FROM nfts;
DROP TABLE nfts;
ALTER TABLE nfts_clustered RENAME TO nfts;

CREATE EXTERNAL TABLE ownership_transitions_clustered CLUSTERED BY (from_address) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/ownership_transitions_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/ownership_transitions.avsc');
INSERT INTO ownership_transitions_clustered SELECT * FROM ownership_transitions;
DROP TABLE ownership_transitions;
ALTER TABLE ownership_transitions_clustered RENAME TO ownership_transitions;

CREATE EXTERNAL TABLE transfer_holding_times_clustered CLUSTERED BY (days) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/transfer_holding_times_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_holding_times.avsc');
INSERT INTO transfer_holding_times_clustered SELECT * FROM transfer_holding_times;
DROP TABLE transfer_holding_times;
ALTER TABLE transfer_holding_times_clustered RENAME TO transfer_holding_times;

CREATE EXTERNAL TABLE transfers_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/transfers_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfers.avsc');
INSERT INTO transfers_clustered SELECT * FROM transfers;
DROP TABLE transfers;
ALTER TABLE transfers_clustered RENAME TO transfers;

CREATE EXTERNAL TABLE transfers_mints_clustered CLUSTERED BY (transfer_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/transfers_mints_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfers_mints.avsc');
INSERT INTO transfers_mints_clustered SELECT * FROM transfers_mints;
DROP TABLE transfers_mints;
ALTER TABLE transfers_mints_clustered RENAME TO transfers_mints;

CREATE EXTERNAL TABLE transfer_statistics_by_address_clustered CLUSTERED BY (address) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/transfer_statistics_by_address_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_statistics_by_address.avsc');
INSERT INTO transfer_statistics_by_address_clustered SELECT * FROM transfer_statistics_by_address;
DROP TABLE transfer_statistics_by_address;
ALTER TABLE transfer_statistics_by_address_clustered RENAME TO transfer_statistics_by_address;

CREATE EXTERNAL TABLE transfer_values_quantile_10_distribution_per_address_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/transfer_values_quantile_10_distribution_per_address_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_values_quantile_10_distribution_per_address.avsc');
INSERT INTO transfer_values_quantile_10_distribution_per_address_clustered SELECT * FROM transfer_values_quantile_10_distribution_per_address;
DROP TABLE transfer_values_quantile_10_distribution_per_address;
ALTER TABLE transfer_values_quantile_10_distribution_per_address_clustered RENAME TO transfer_values_quantile_10_distribution_per_address;

CREATE EXTERNAL TABLE transfer_values_quantile_25_distribution_per_address_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/transfer_values_quantile_25_distribution_per_address_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_values_quantile_25_distribution_per_address.avsc');
INSERT INTO transfer_values_quantile_25_distribution_per_address_clustered SELECT * FROM transfer_values_quantile_25_distribution_per_address;
DROP TABLE transfer_values_quantile_25_distribution_per_address;
ALTER TABLE transfer_values_quantile_25_distribution_per_address_clustered RENAME TO transfer_values_quantile_25_distribution_per_address;

CREATE EXTERNAL TABLE transfer_values_quartile_10_distribution_per_address_clustered CLUSTERED BY (token_id) INTO 10 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/transfer_values_quartile_10_distribution_per_address_clustered' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/transfer_values_quartile_10_distribution_per_address.avsc');
INSERT INTO transfer_values_quartile_10_distribution_per_address_clustered SELECT * FROM transfer_values_quartile_10_distribution_per_address;
DROP TABLE transfer_values_quartile_10_distribution_per_address;
ALTER TABLE transfer_values_quartile_10_distribution_per_address_clustered RENAME TO transfer_values_quartile_10_distribution_per_address;

