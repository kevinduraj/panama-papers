#!/bin/bash
#----------------------------------------------------------#
#rm -fR temp/
sbt clean
sbt package

#----------------------------------------------------------#
spark-submit             \
  --packages com.databricks:spark-csv_2.10:1.4.0 \
  --class "PanamaGraph"  \
  --master local[4]      \
  --driver-memory 4G     \
  --executor-memory 4G   \
  target/scala-2.10/panamagraph_2.10-1.0.jar  

#----------------------------------------------------------#
