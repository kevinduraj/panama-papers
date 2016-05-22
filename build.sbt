name := "PanamaGraph"

version := "1.0"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.1",
  "org.apache.spark" %% "spark-sql"  % "1.6.1",
  "com.databricks"   %% "spark-avro" % "2.0.1",
  "org.apache.spark" % "spark-graphx_2.10" % "1.6.1"
)

