#!/bin/bash

DATA_DIR=$(pwd)/data
VENV=$HOME/.venv/

function clear_dir {
  if [ -d $1 ]
  then
    rm -r $1
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
unzip $DATA_DIR/ethereum-nfts.zip
