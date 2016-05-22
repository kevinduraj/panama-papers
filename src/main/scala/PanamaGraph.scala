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

    //find_officers()
    count_officers()

  }
  /*----------------------------------------------------------------------------*/
  //                         Officers Analysis 
  // https://spark.apache.org/docs/1.3.1/sql-programming-guide.html
  /*----------------------------------------------------------------------------*/
  def count_officers() {

    println("********** Officers Analysis ************")

    val sc = new SparkContext(new SparkConf().setAppName("PanamaGraph"))
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    //val people = sc.textFile("/home/nootrino/panama-papers/data/Officers.csv")
    val people = sc.textFile("/home/nootrino/panama-papers/data/Officers.csv.clean")
            .map(_.split(","))
            .map(col => Person(col(0), col(1)))
            .toDF()
    people.registerTempTable("people")
   
    val SQL = """
        SELECT name, COUNT(*) AS times
        FROM people 
        GROUP BY name 
        ORDER BY times DESC
        """
    
    sqlContext.sql( SQL ).save("/tmp/count1", "com.databricks.spark.csv")

/*        
    sqlContext.sql(
        """
        SELECT name, COUNT(*) AS times
        FROM people 
        GROUP BY name 
        ORDER BY times DESC
        """
        ).save("/tmp/count", "com.databricks.spark.csv")
*/

  }

  /*----------------------------------------------------------------------------*/
  //                          Find  Officers
  /*----------------------------------------------------------------------------*/
  def find_officers() {

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

  }

}


