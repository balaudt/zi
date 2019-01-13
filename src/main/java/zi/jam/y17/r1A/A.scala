package zi.jam.y17.r1A

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 4/22/17.
  */
object A extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nk = lines.next().split(" ").map(_.toInt)
    val (n, k) = (nk(0), nk(1))
    var rh = Array.ofDim[Array[Int]](n)
    for (j <- 0 until n) {
      rh(j) = lines.next().split(" ").map(_.toInt)
    }
    rh = rh.sortBy(x => -x(0))
    val memo = mutable.HashMap[(Int, Int, Int), Double]()

    def max(st: Int, rem: Int, last: Int): Double = {
      if (rem == 0) {
        return 0
      }
      if (st == n) {
        return -1
      }
      memo.get((st, rem, last)) match {
        case Some(x) => x
        case None =>
          var cur = Math.PI * 2 * rh(st)(0) * rh(st)(1)
          if (last == -1) cur += Math.PI * rh(st)(0) * rh(st)(0)
          val withCur = max(st + 1, rem - 1, st) + cur
          val out = Math.max(max(st + 1, rem, last), if (withCur > -1) withCur else -1)
          memo += (st, rem, last) -> out
          out
      }
    }

    println(max(0, k, -1))
  }
}
