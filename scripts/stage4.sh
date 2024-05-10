#!/bin/bash

HIVE_PASSWORD=$(cat ~/.secrets/.hive.pass)
HIVE_HOST=jdbc:hive2://hadoop-03.uni.innopolis.ru:10001

# Copy output data back to HDFS
echo "Copying data from 3rd stage..."
hdfs dfs -rm -f -r project/output/*.csv
hdfs dfs -put -f output/*.csv project/output
echo "Finished copying data from 3rd stage!"

# Export data from previous stage
echo "Exporting data from 3rd stage..."
beeline -u $HIVE_HOST -n team31 -p $HIVE_PASSWORD -f sql/stage_3_export.hql
echo "Finished exporting data from 3rd stage!"