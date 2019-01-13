package zi.tc.y16.mmrf

import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by balamurugan on 12/23/16.
 */
object MLStart extends App {
  val root = "/Users/balamurugan/Temp/mmrf"
  val sc = new SparkContext(new SparkConf().setAppName("MMRF").setMaster("local"))
  val spark = SparkSession.builder().appName("MMRF").getOrCreate()
  val labelArray = Source.fromFile(s"$root/consolidated.out").getLines().map(_.toDouble).toArray
  val featureArray = Source.fromFile(s"$root/consolidated.in").getLines().map(x => Vectors.dense(x.split(",").map(_.toDouble))).toArray
  val training = spark.createDataFrame(labelArray.zip(featureArray)).toDF("label", "features")
  val lr = new LogisticRegression()
  lr.setMaxIter(10).setRegParam(0.01)
  val mdl = lr.fit(training)
  println(mdl)
}
