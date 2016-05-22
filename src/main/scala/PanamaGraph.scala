import org.apache.spark._
import org.apache.spark.sql._
//import org.apache.spark.graphx._
//import org.apache.spark.rdd.RDD
import org.apache.log4j.{Level, Logger}
//class Graph[VD, ED] {
//  val vertices: VertexRDD[VD];
//  val edges: EdgeRDD[ED]
//}

case class Person(name: String, info: String)

object PanamaGraph {

  val threshold = 3

  /*----------------------------------------------------------------------------*/
  //                           Main 
  /*----------------------------------------------------------------------------*/
  def main(args: Array[String]) {

    //social_graph(inputFile, outputDir)
    //student_graph(inputFile, outputDir)
    //users_graph(inputFile, outputDir)
    officers_analysis()

  }
  /*----------------------------------------------------------------------------*/
  //                         Officers Analysis 
  // https://spark.apache.org/docs/1.3.1/sql-programming-guide.html
  /*----------------------------------------------------------------------------*/
  def officers_analysis() {

    println("********** Officers Analysis ************")

    val sc = new SparkContext(new SparkConf().setAppName("PanamaGraph"))
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    /*
       val textFile = sc.textFile("data/Officers.csv")
       val counts = textFile.flatMap(line => line.split(",")).map(word => (word, 1)).reduceByKey(_ + _)
       counts.foreach(println)
    */

    //val people = sc.textFile("/home/nootrino/panama-papers/data/Officers.csv")
    val people = sc.textFile("/home/nootrino/panama-papers/data/Officers.csv.clean")
            .map(_.split(","))
            .map(col => Person(col(0), col(1)))
            .toDF()


    people.registerTempTable("people")
    val person = sqlContext.sql("SELECT name, info FROM people WHERE name = 'Edhie Hardjanto'")

    // Result SQL Queries

    // The columns of a row in the result can be accessed by field index:
    person.map(t => "Name: " + t(0)).collect().foreach(println)
    
    // Result by field name:
    person.map(t => "Name: " + t.getAs[String]("name")).collect().foreach(println)

    // retrieves multiple columns at once into a Map[String, T]
    person.map(_.getValuesMap[Any](List("name", "info"))).collect().foreach(println)

    //people.registerTempTable("people")

    //people.registerTempTable("people")
    //val kevins = sqlContext.sql("SELECT name FROM people WHERE name LIKE '%kevin%'")
    //kevins.map(t => "Name: " + t(0)).collect().foreach(println) 


    //val df1 = sqlContext.read.format("com.databricks.spark.avro").load(avroFile).registerTempTable("officers")
    //sqlContext.sql(
    //    """
    //    SELECT name, COUNT(*) AS times
    //    FROM officers
    //    GROUP BY name 
    //    ORDER BY times DESC
    //    """).save("/output/names.csv", "com.databricks.spark.csv")

  }

}


