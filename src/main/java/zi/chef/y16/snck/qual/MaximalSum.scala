package zi.chef.y16.snck.qual

import scala.io
/**
  * Created by balaudt on 5/27/16.
  */
object MaximalSum extends App {
  //  val lineIt = io.Source.fromFile("/Users/balaudt/Temp/snck.qual/max.in").getLines()
  //  System.setOut(new PrintStream("/Users/balaudt/Temp/snck.qual/max.out"))
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val n = lineIt.next().toInt
    val a = lineIt.next().split(" ").map(_.toLong)
    def crossSubArray(low: Int, mid: Int, high: Int): (Long, Long) = {
      if (low == mid && mid == high) {
        //        println((low, low, low, a(low), Long.MinValue))
        return (a(low), Long.MinValue)
      }
      var (ls, rs, lsd, rsd, sum, min) = (Long.MinValue, Long.MinValue, Long.MinValue, Long.MinValue, 0l, Long.MaxValue)
      for (j <- mid to low by -1) {
        sum += a(j)
        if (sum > ls) ls = sum
        if (a(j) < min) min = a(j)
        if ((sum - min) > lsd) lsd = sum - min
      }

      sum = 0l
      min = Long.MaxValue
      for (j <- mid + 1 to high) {
        sum += a(j)
        if (sum > rs) rs = sum
        if (a(j) < min) min = a(j)
        if ((sum - min) > rsd) rsd = sum - min
      }
      //      println((low, mid, high, ls + rs, Math.max(ls + rsd, lsd + rs)))
      (ls + rs, Math.max(ls + rsd, lsd + rs))
    }

    def maxSubArray(low: Int, high: Int): (Long, Long) = {
      if (high == low) {
        //        println((low, low, a(low), Long.MinValue))
        return (a(low), Long.MinValue)
      }
      val mid = (low + high) / 2
      val (ls, rs, cross) = (maxSubArray(low, mid), maxSubArray(mid + 1, high), crossSubArray(low, mid, high))
      val wo = Array(ls._1, rs._1, cross._1).max
      val wd = Array(ls._2, rs._2, cross._2).max
      //      println((low, high, wo, wd))
      return (wo, wd)
    }

    if (n == 1)
      if (a(0) < 0)
        println(0)
      else println(a(0))
    else {
      val (wo, wd) = maxSubArray(0, n - 1)
      println(Math.max(wo, wd))
    }
  }


}
