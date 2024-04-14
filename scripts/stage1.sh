#!/bin/bash

# Create a python environment
VENV=$HOME/.venv/
python3 -m venv $VENV
source $VENV/bin/activate

# Download the dataset
url='https://disk.yandex.ru/d/Rb-ui8R5S9u-uA'
wget "$(yandex-direct $url)" -O $HOME/data/dataset.sql.lzma
cd $HOME/data/

