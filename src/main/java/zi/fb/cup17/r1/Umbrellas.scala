package zi.fb.cup17.r1

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 1/15/17.
  */
object Umbrellas extends App {
  val P = 1e9.toInt + 7
  val pBig = BigInt(P)
  val inv = (1 to 5000).map(x => BigInt(x).modInverse(pBig).toLong)
  val ncrMemo = mutable.HashMap[(Int, Int), Int]()
  val factMemo = mutable.HashMap[Int, Int]()

  private def ncr(n: Int, r: Int): Int = {
    if (r < 0) 0
    else if (r == 0) 1
    else {
      if (ncrMemo.contains((n, r))) return ncrMemo((n, r))
      var out = 1l
      for (i <- 0 until r) {
        out *= (n - i)
        out %= P
        out *= inv(i)
        out %= P
      }
      ncrMemo += (n, r) -> out.toInt
      out.toInt
    }
  }

  private def fact(n: Int): Int = {
    if (factMemo.contains(n)) return factMemo(n)
    var out = 1l
    for (i <- 1 to n) {
      out *= i
      out %= P
    }
    factMemo += n -> out.toInt
    out.toInt
  }

  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nm = lines.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val r = (1 to n).map(_ => lines.next().toInt)
    val total = r.sum * 2
    if (n == 1) println(s"Case #$i: $m")
    else {
      if (n == 2) {

      } else {
        var ct = 0l
        for (j <- 0 until n) {
          for (k <- 0 until n) {
            if (j != k) {
              ct += f(j, k)
              ct %= P
            }
          }
        }
        println(s"Case #$i: $ct")
      }
    }

    def f(left: Int, right: Int): Int = {
      val used = total - r(left) - r(right)
      val empty = m - used
      if (empty < 0) 0
      else if (empty == 0) fact(n - 2)
      else {
        var out = ncr(empty, n - 2).toLong
        out *= 2
        out %= P
        out += ncr(empty + 1, n - 4)
        out %= P
        out += ncr(empty - 2, 2 * n - 5)
        out %= P
        out *= fact(n - 2)
        out %= P
        out.toInt
      }
    }
  }
}
