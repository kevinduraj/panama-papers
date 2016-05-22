#!/bin/bash
#-------------------------------------------------------------#
# nohup ./clean-quotes.sh data/Officers.csv >> /dev/null 2>&1 &
# watch ls -lh data
#-------------------------------------------------------------#

rm -fr "$1.quotes"
echo "$1.quotes"
sleep 1
sed -n -e '/^"/p' $1 | tee "$1.quotes" 
sed -i '/^"/d;' data/Officers.csv.clean 

#-------------------------------------------------------------#

