package zi.tc.y16.mmrf

import org.apache.spark.ml.linalg.{Matrix, Vector}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by balamurugan on 12/28/16.
 */
object MlJavaTest extends App {

  val conf = new SparkConf().setAppName("MMRF")
  conf.setMaster("local")
  conf.set("spark.kryoserializer.buffer.max", "256m")
  val sc = new SparkContext(conf)
  val spark = SparkSession.builder().appName("MMRF").getOrCreate()
  val mdlDf = spark.read.parquet("/Users/balamurugan/Temp/mmrf/mdl/data/")
  val intercept = mdlDf.select("interceptVector").collect()(0).get(0).asInstanceOf[Vector](0)
  val coefficientsMatrix = mdlDf.select("coefficientMatrix").collect()(0).get(0).asInstanceOf[Matrix]
  val coefficient = Array.ofDim[Double](coefficientsMatrix.numCols)
  for (i <- 0 until coefficientsMatrix.numCols) coefficient(i) = coefficientsMatrix(0, i)
  println(coefficient.mkString(","))

  val sampleDf = spark.read.parquet("/Users/balamurugan/Temp/mmrf/sample-predict/")
  val features = sampleDf.select("features").collect()(0).get(0).asInstanceOf[Vector]
  val out = (for (i <- 0 until features.size) yield coefficient(i) * features(i)).sum + intercept
  println((out, sampleDf.select("rawPrediction").collect()(0).get(0)))
}
