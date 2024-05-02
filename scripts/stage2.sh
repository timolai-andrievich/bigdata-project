#!/bin/bash

# Set directories
REPO_DIR=$(dirname $0)/..
SCRIPTS_DIR=$(dirname $0)
DATA_DIR=$(pwd)/data
VENV=$(pwd)/.venv/
HDFS_CHECKPOINT=project/warehouse
HIVE_DIR=project/hive/warehouse
AVSC_DIR=$HDFS_CHECKPOINT/avsc

HIVE_PASSWORD=$(cat ~/.secrets/.hive.pass)
HIVE_HOST=jdbc:hive2://hadoop-03.uni.innopolis.ru:10001

function clear_hdfs_dir {
  if $( hdfs dfs -test -d $1 )
  then
    hdfs dfs -rm -r $1
  fi
  hdfs dfs -mkdir -p $1
}

clear_hdfs_dir $AVSC_DIR
clear_hdfs_dir $HIVE_DIR
hdfs -put $REPO_DIR/output/*.avsc $AVSC_DIR
beeline -u $HIVE_HOST -n team31 -p $HIVE_PASSWORD -f $REPO_DIR/sql/db.hql > $REPO_DIR/output/hive_results.txt
