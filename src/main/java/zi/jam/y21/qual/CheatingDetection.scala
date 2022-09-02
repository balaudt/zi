package zi.jam.y21.qual

import scala.io.Source

object CheatingDetection {

  def std(in: Array[Int]): Double = {
    val mean = in.sum.toDouble / in.length
    val variance = in.map(_ - mean).map(Math.pow(_, 2)).sum / in.length
    Math.sqrt(variance)
  }

  def main(args: Array[String]): Unit = {
        val lines = Source.fromInputStream(System.in).getLines()
//    val lines = Source.fromFile("/Users/bchandrasekaran/Temp/cj_cd_in.txt").getLines()
    val t = lines.next().toInt
    val p = lines.next().toInt
    for (i <- 1 to t) {
      val scores = Array.ofDim[Boolean](100, 10000)
      for (j <- 0 until 100)
        scores(j) = lines.next().toCharArray.map(_ == '1')
      val difficulties = Array.ofDim[Int](10000)
      for (j <- 0 until 10000)
        difficulties(j) = scores.map(_ (j)).count(!_)
      //      println(difficulties.mkString(" "))
      var maxStd = 0.0
      var res = -1
      for (j <- 0 until 100) {
        val ansDiffs = difficulties.zipWithIndex.filter(x => scores(j)(x._2)).map(_._1)
        val cur = std(ansDiffs)
        if (cur > maxStd) {
          maxStd = cur
          res = j
        }
      }
      println(s"Case #$i: ${res + 1}")
    }
  }
}
