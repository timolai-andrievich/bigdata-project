USE team31_projectdb;

DROP TABLE IF EXISTS q5_results;
CREATE TABLE q5_results(
  nft_address STRING,
  tokens INT
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q5';

INSERT INTO q5_results(nft_address, tokens)
SELECT nfts.address, count(DISTINCT mints.token_id)
FROM nfts LEFT OUTER JOIN mints ON nfts.address = mints.nft_address
GROUP BY nfts.address ORDER BY tokens ASC;

INSERT OVERWRITE DIRECTORY 'project/output/q5' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q5_results;
