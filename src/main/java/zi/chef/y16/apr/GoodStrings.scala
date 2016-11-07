package zi.chef.y16.apr

/**
  * Created by balaudt on 4/3/16.
  */

import scala.collection.mutable
import scala.collection.mutable._
import scala.io.Source

object GoodStrings extends App {

  val aps = Array.ofDim[Int](600, 3)
  val goodStrings = new HashMap[Int, Map[Int, Seq[Array[Char]]]]()

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  def initialize = {
    var ct = 0
    for (d <- 1 to 50) {
      for (a <- 1 to 50) {
        if (a + 2 * d <= 50) {
          aps(ct)(0) = a
          aps(ct)(1) = a + d
          aps(ct)(2) = a + 2 * d
          ct += 1
        }
      }
    }
    @inline def isGood(in: Array[Char]): Boolean = {
      aps.foreach(x => {
        if (in.length >= x(2) && in(x(0) - 1) == in(x(1) - 1) && in(x(1) - 1) == in(x(2) - 1)) return false
      })
      true
    }

    val alpOne = new HashMap[Int, Seq[Array[Char]]]()
    alpOne += 1 -> ArrayBuffer("0".toCharArray)
    alpOne += 2 -> ArrayBuffer("00".toCharArray)
    goodStrings += 1 -> alpOne
    for (a <- 2 to 3) {
      val alp = new mutable.HashMap[Int, Seq[Array[Char]]]()
      goodStrings += a -> alp
      for (s <- 1 to 12) {
        val goodStrings = ArrayBuffer[Array[Char]]()
        val max = Math.pow(a, s).toInt
        var goodCt = 0
        for (i <- 0 until max) {
          var pat = new StringBuilder(Integer.toString(i, a))
          while (pat.size < s)
            pat.insert(0, '0')
          if (isGood(pat.toArray))
            goodStrings += pat.toArray
        }
        alp += s -> goodStrings
      }
    }
  }

  initialize

  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val ak = lineIt.next().split(" ").map(_.toInt)
    val (a, k) = (ak(0), ak(1))
    val str = lineIt.next()
    val slen = str.length
    if (a == 1 && slen > 2)
      println(0)
    else if (a == 2 && slen > 8)
      println(0)
    else if (a == 3 && slen > 13)
      throw new UnsupportedOperationException()
    else {
      val s = str.toCharArray
      val curGoodStrs = goodStrings.get(a).get.get(slen).get
      var ans = 0
      curGoodStrs.foreach(goodStr => {
        var diff = 0
        for (j <- 0 until slen if (diff <= k)) if (goodStr(j) - '0' != s(j) - 'a')
          diff += 1
        if (diff <= k) ans += 1
      })
      println(ans)
    }
  }
}
