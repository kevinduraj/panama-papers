#!/bin/bash
#-------------------------------------------------------------#
# Remove all lines not having least 3 comma delimiters
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
 
  if [ "${#delimiter}" -gt "3" ]
  then
      echo $line >> "$1.clean"
  fi

done < $1

#-------------------------------------------------------------#
