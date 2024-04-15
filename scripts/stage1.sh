#!/bin/bash

POSTGRES_USER=team31
POSTGRES_HOST=hadoop-04.uni.innopolis.ru
POSTGRES_PASS=$(cat $HOME/.secrets/.psql.pass)
POSGRES_PORT=5432
POSTGRED_DB=team31_projectdb

SCRIPTS_DIR=$(dirname $0)
DATA_DIR=$(pwd)/data
VENV=$HOME/.venv/

function clear_dir {
  if [ -d $1 ]
  then
    rm -r $1
    mkdir $1
  fi
}

clear_dir $DATA_DIR
clear_dir $VENV

# Create a python environment
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
python3 $SCRIPTS_DIR/load_data.py $DATA_DIR/nfts.sqlite --host $POSTGRES_HOST --port $POSGRES_PORT --user $POSTGRES_USER --password $POSTGRES_PASS
