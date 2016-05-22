#!/bin/bash
#-------------------------------------------------------------#
# Remove all lines not having least 6 [,] delimiters
# usage: 
# nohup ./clean-data.sh data/Officers.csv >> /dev/null 2>&1 &
#-------------------------------------------------------------#
echo "rm -f $1.clean"
rm -f "$1.clean"
sleep 1
#-------------------------------------------------------------#

while IFS= read -r line; do 

  delimiter=$line
  delimiter="${line//[!\,]/}"
 
  if [ "${#delimiter}" -gt "5" ]
  then
      echo $line >> "$1.clean"
  fi

done < $1

#-------------------------------------------------------------#
