#!/bin/bash

# Create a python environment
VENV=$HOME/.venv/
python3 -m venv $VENV
source $VENV/bin/activate
pip3 install kaggle > /dev/null

if [ ! -f $HOME/kaggle/kaggle.json ] 
then
  echo "Please put your Kaggle API key into ~/.kaggle/"
  exit 1
fi

# Download the dataset
kaggle datasets download -d simiotic/ethereum-nfts -p ./data
unzip ethereum-nfts.zip
