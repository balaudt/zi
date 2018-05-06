package zi.chef.y16.snck.qual

import java.io.PrintStream
import java.util

import scala.collection.mutable
import scala.io

/**
  * Created by balaudt on 5/28/16.
  */
object KGood extends App {
  val lineIt = io.Source.fromFile("/Users/balaudt/Temp/snck.qual/kgood-sin.in").getLines()
  //  System.setOut(new PrintStream("/Users/balaudt/Temp/snck.qual/kgood.out"))
  //  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val line = lineIt.next().split(" ")
    val (s, k) = (line(0), line(1).toInt)
    val freqMap = mutable.HashMap[Char, Int]()
    for (j <- 0 to 26) freqMap += ('a' + j).toChar -> 0
    s.foreach(x => freqMap += x -> (freqMap.get(x).get + 1))
    val a = freqMap.values.filter(_ != 0).toArray.sorted
    //    println(a.groupBy(x => x).map(x => (x._1, x._2.size)))
    val leftScan = a.scanLeft(0)((acc, x) => acc + x)
    val rightScan = a.scanRight(0)((acc, x) => acc + x)
    val n = a.length
    val (kl, kr) = (k / 2, if (k % 2 == 0) k / 2 else k / 2 + 1)

    val memo = mutable.HashMap[Int, Int]()
    def cost(x: Int): Int = {
      if (memo.contains(x))
        return memo.get(x).get
      var ip: Int = 0
      var out: Int = 0
      if (x - kl > a.head) {
        ip = util.Arrays.binarySearch(a, x - kl - 1)
        if (ip < 0)
          ip = -ip - 2
        else
          while (ip + 1 < n && a(ip + 1) == a(ip))
            ip += 1
        out = leftScan(ip + 1)
      }
      if (x + kr < a.last) {
        ip = util.Arrays.binarySearch(a, x + kr + 1)
        if (ip < 0)
          ip = -ip - 1
        else
          while (ip - 1 >= 0 && a(ip - 1) == a(ip))
            ip -= 1
        out += rightScan(ip) - (n - ip) * (x + kr)
      }
      memo += x -> out
      out
    }

    def costInRange(lowIn: Int, highIn: Int): Int = {
      var (low, high, flag, mid, ans) = (lowIn, highIn, true, -1, -1)
      while (flag) {
        if (low == high) {
          ans = cost(low)
          flag = false
        } else if (high - low == 1) {
          ans = Math.min(cost(low), cost(high))
          flag = false
        } else {
          mid = (low + high) / 2
          val (negDel, cur, posDel) = (cost(mid - 1), cost(mid), cost(mid + 1))
          if (negDel <= cur && cur <= posDel)
            high = mid
          else if (negDel >= cur && cur >= posDel)
            low = mid
          else {
            ans = cost(mid)
            flag = false
          }
        }
      }
      ans
    }

    if (n == 1)
      println(0)
    else {

    }
  }

}
