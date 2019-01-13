package zi.jam.y16.r1C

import scala.io
/**
  * Created by balaudt on 5/8/16.
  */
object Slides extends App {
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt

  def test(edges: Array[Array[Boolean]], m: Int, b: Int): Boolean = {
    val ct = Array.ofDim[Int](b)
    ct(b - 1) = 1
    var ans = 0
    for (i <- b - 2 to 0 by -1) {
      var curCt = 0
      for (j <- i + 1 until b) {
        if (edges(i)(j))
          curCt += ct(j)
      }
      ct(i) = curCt
    }
    return ct(0) == m
  }

  for (i <- 1 to t) {
    val bm = lineIt.next().split(" ").map(_.toInt)
    val (b, m) = (bm(0), bm(1))
    val maxRoutes = Math.pow(2, b * (b - 1) / 2).toInt
    var flag = false
    for (j <- 0 until maxRoutes if (!flag)) {
      val builder = new StringBuilder(Integer.toBinaryString(j))
      while (builder.length != b * (b - 1) / 2) {
        builder.insert(0, '0')
      }
      var buildInd = 0
      val edges = Array.ofDim[Array[Boolean]](b)
      for (k <- 0 until b) {
        edges(k) = Array.ofDim[Boolean](b)
        for (l <- 0 to k)
          edges(k)(l) = false
        for (l <- k + 1 until b) {
          edges(k)(l) = builder(buildInd) == '1'
          buildInd += 1
        }
      }
      if (test(edges, m, b)) {
        flag = true
        println(s"Case #$i: POSSIBLE")
        for (k <- 0 until b) {
          for (l <- 0 until b) {
            print(if (edges(k)(l)) 1 else 0)
          }
          println()
        }
      }
    }
    if (!flag) {
      println(s"Case #$i: IMPOSSIBLE")
    }
  }
}
