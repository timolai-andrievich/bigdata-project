#!/bin/bash
url='https://disk.yandex.ru/d/Rb-ui8R5S9u-uA'
wget "$(yandex-direct $url)" -O $HOME/data/dataset.sql.lzma
cd $HOME/data/
lzma -kd dataset.sql.lzma
