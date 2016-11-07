package zi.jam.y16.r1A

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balaudt on 4/15/16.
  */
object RankAndFile extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val n = lineIt.next().toInt
    var in = Array.ofDim[Int](2 * n - 1, n)
    for (j <- 0 until 2 * n - 1)
      in(j) = lineIt.next().split(" ").map(_.toInt)
    def comp(l: Array[Int], r: Array[Int]): Boolean = {
      for (ind <- l.indices)
        if (l(ind) < r(ind))
          return true
        else if (l(ind) > r(ind))
          return false
      true
    }
    in = in.sortWith(comp)
    var flag = false
    for (j <- 0 until n if (!flag)) {
      val arr = new Array[Int](n)
      for (k <- 0 until n) {
        arr(k) = in(k * 2)(j)
      }

      var found = false
      for (k <- 0 until n - 1 if (!found)) {
        var isSame = true
        for (l <- 0 until n if (isSame)) {
          isSame = arr(l) == in(2 * k + 1)(l)
        }
        if (isSame)
          found = true
      }
      if (!found) {
        println(s"Case #$i: ${arr.mkString(" ")}")
        flag = true
      }
    }
  }
}
