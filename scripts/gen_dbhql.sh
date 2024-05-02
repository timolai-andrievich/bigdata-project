#!/bin/bash
REPO_DIR=$(dirname $0)/../
echo 'DROP DATABASE IF EXISTS team31_projectdb CASCADE'
echo 'CREATE DATABASE team31_projectdb LOCATION "project/hive/warehouse";'
echo 'USE team31_projectdb;'
find $REPO_DIR/output/*.avsc -printf "%f\n" | while read line; do
  table="${line%.*}"
  filename="$line"
  echo "CREATE EXTERNAL TABLE $table STORED AS AVRO LOCATION 'project/warehouse/$table' TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/$filename');"
done
