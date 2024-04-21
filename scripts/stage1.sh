#!/bin/bash

# Set the postgres login information
POSTGRES_USER=team31
POSTGRES_HOST=hadoop-04.uni.innopolis.ru
if ! ping -c 1 $POSTGRES_HOST
then
  echo "Couldn't resolve $POSTGRES_HOST, using IP 10.100.30.60 instead"
  POSTGRES_HOST=10.100.30.60
fi
POSTGRES_PASS=$(cat $HOME/.secrets/.psql.pass)
POSGRES_PORT=5432
POSTGRED_DB=team31_projectdb

# Set directories
REPO_DIR=$(dirname $0)/..
SCRIPTS_DIR=$(dirname $0)
DATA_DIR=$(pwd)/data
VENV=$(pwd)/.venv/
HDFS_CHECKPOINT=project/warehouse

function clear_dir {
  if [ -d $1 ]
  then
    rm -r $1
    mkdir $1
  fi
}

function clear_hdfs_dir {
  if $( hdfs dfs -test -d $1 )
  then
    hdfs dfs -rm -r $1
  fi
}

clear_dir $DATA_DIR
clear_dir $VENV
clear_hdfs_dir $HDFS_CHECKPOINT

# Create a python environment and install kaggle
python3 -m venv $VENV
source $VENV/bin/activate
pip3 install kaggle > /dev/null
if [ ! -f $HOME/.kaggle/kaggle.json ] 
then
  echo "Please put your Kaggle API key into ~/.kaggle/"
  exit 1
fi

# Download the dataset
kaggle datasets download -d simiotic/ethereum-nfts -p $DATA_DIR
deactivate
unzip $DATA_DIR/ethereum-nfts.zip -d $DATA_DIR

# Sample from the dataset
python3 $SCRIPTS_DIR/sample.py $DATA_DIR/nfts.sqlite $DATA_DIR/sampled.sqlite
mv $DATA_DIR/sampled.sqlite $DATA_DIR/nfts.sqlite

# Load the data into Postgres database
python3 $SCRIPTS_DIR/load_data.py $DATA_DIR/nfts.sqlite \
  --host $POSTGRES_HOST \
  --port $POSGRES_PORT \
  --user $POSTGRES_USER \
  --password $POSTGRES_PASS \
  --dbname $POSTGRED_DB

# Import data into HDFS
sqoop import-all-tables \
  --connect jdbc:postgresql://hadoop-04.uni.innopolis.ru/team31_projectdb \
  --username team31 \
  --password $POSTGRES_PASS \
  --compression-codec=snappy \
  --compress \
  --as-avrodatafile \
  --warehouse-dir=$HDFS_CHECKPOINT \
  --m 1 \
  --outdir $REPO_DIR/output
