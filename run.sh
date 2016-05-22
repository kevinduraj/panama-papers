#!/bin/bash
#----------------------------------------------------------#
#rm -fR temp/
sbt clean
sbt package

#----------------------------------------------------------#
spark-submit             \
  --class "PanamaGraph"  \
  --master local[4]      \
  --driver-memory 4G     \
  --executor-memory 4G   \
  target/scala-2.10/spark-graph_2.10-1.0.jar      \
  /Users/kevin.duraj/github/spark-graph/words.txt \
  /Users/kevin.duraj/github/spark-graph/temp

#----------------------------------------------------------#
