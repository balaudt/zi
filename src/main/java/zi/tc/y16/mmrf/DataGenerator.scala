package zi.tc.y16.mmrf

import java.io.{PrintStream, FileReader, BufferedReader}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by balamurugan on 12/23/16.
 */
object DataGenerator extends App {
  val root = "/Users/balamurugan/Temp/mmrf/"
  val inFiles = Array("copynumber_example", "expressions_example", "mutations_example")
  val inps = inFiles.map(x => Source.fromFile(s"$root$x.csv").getLines())
  val isInVal = Array.ofDim[Boolean](332)
  val in = (1 to 332).map(x => ArrayBuffer[Double]())
  inps.foreach(inp => {
    for (i <- 1 to 332) {
      val sinIn = inp.next().split(",").map(_.trim)
      if (sinIn.exists(_.isEmpty) || sinIn.isEmpty) isInVal(i - 1) = true
      else in(i - 1) ++= sinIn.map(_.toDouble)
    }
  })
  val yOut = new PrintStream(s"${root}consolidated.out")
  val lines = Source.fromFile(s"${root}groundtruth_example.csv").getLines()
  for (i <- 1 to 332) {
    val line = lines.next()
    if (line.equals(",")) isInVal(i - 1) = true
    else if (!isInVal(i - 1)) yOut.println(if (line.split(",")(0).isEmpty) 0 else 1)
  }
  yOut.close()
  val out = new PrintStream(s"${root}consolidated.in")
  in.zipWithIndex.foreach(x => {
    if (!isInVal(x._2)) out.println(x._1.mkString(","))
  })
  out.close()
  println(isInVal.count(x => x))
}
