#!/bin/bash

# Prepare data
echo "Preparing data..."
spark-submit --master yarn scripts/stage_3/prepare_data.py
echo "Finished preparing data!"

# Copy data
echo "Copying prepared data..."
hdfs dfs -cat project/data/train/*.json > data/train.json
hdfs dfs -cat project/data/test/*.json > data/test.json
echo "Finished copying data!"

# Compress data
echo "Compressing data..."
gzip -c -9 data/train.json > data/train.json.gz
gzip -c -9 data/test.json > data/test.json.gz
echo "Finished compressing data!"

# Train first and second models
echo "Training models..."
spark-submit --master yarn scripts/stage_3/train_models.py
echo "Finished training models!"

# Copy results
echo "Copying results..."
hdfs dfs -get project/models/model1 models/model1
hdfs dfs -cat project/output/model1_predictions.csv/*.csv > output/model1_predictions.csv

hdfs dfs -get project/models/model2 models/model2
hdfs dfs -cat project/output/model2_predictions.csv/*.csv > output/model2_predictions.csv
echo "Finished copying results!"

# Compare models
echo "Comparing models..."
spark-submit --master yarn scripts/stage_3/compare_models.py
echo "Finished comparing models!"

# Copy results
echo "Copying results..."
hdfs dfs -cat project/output/evaluation.csv/*.csv > output/evaluation.csv
echo "Finished copying results!"