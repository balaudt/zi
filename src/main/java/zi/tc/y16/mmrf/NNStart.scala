package zi.tc.y16.mmrf

import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.ChiSqSelector
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

/**
 * Created by balamurugan on 12/28/16.
 */
object NNStart {

  def main(args: Array[String]) {
    val root = args(0)
    val sc = new SparkContext(new SparkConf().setAppName("MMRF").setMaster("local"))
    val spark = SparkSession.builder().appName("MMRF").getOrCreate()
    val labels = sc.textFile(s"$root/nn-consolidated.out").map(_.toDouble)
    val features = sc.textFile(s"$root/nn-consolidated.in").map(x => Vectors.dense(x.split(",").map(_.toDouble)))

    val data = spark.createDataFrame(labels.zip(features)).toDF("label", "rawFeatures")
    val selector = new ChiSqSelector().setNumTopFeatures(1000).setFeaturesCol("rawFeatures").setLabelCol("label").setOutputCol("features")
    var selectedData = selector.fit(data).transform(data)
    selectedData = selectedData.drop("rawFeatures")

    val splits = selectedData.randomSplit(Array(0.7, 0.3), seed = Random.nextLong())
    val lr = new LinearRegression()
    lr.setMaxIter(10).setRegParam(0.01)
    val mdl = lr.fit(splits(0))
    val evaluator = new RegressionEvaluator().setMetricName("rmse")
    val results = mdl.transform(splits(1))
    println(evaluator.evaluate(results.select("prediction", "label")))

  }
}
