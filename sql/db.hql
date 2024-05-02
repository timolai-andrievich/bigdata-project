DROP DATABASE IF EXISTS team31_projectdb CASCADE
CREATE DATABASE team31_projectdb LOCATION "project/hive/warehouse";
USE team31_projectdb;
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
