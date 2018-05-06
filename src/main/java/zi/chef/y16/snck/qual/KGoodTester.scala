package zi.chef.y16.snck.qual

import java.io.PrintStream

import org.apache.commons.lang3.RandomStringUtils

import scala.collection.mutable
import scala.io
import scala.util.Random

/**
  * Created by balaudt on 5/28/16.
  */
object KGoodTester extends App {

  def gen = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/snck.qual/kgood.in"))
    val t = 10 + Random.nextInt(10)
    println(t)
    val alphs = "abcdefghijklmnopqrstuvwxyz"
    for (i <- 1 to t) {
      val n = Random.nextInt(100) + 100
      val s = RandomStringUtils.random(n, alphs)
      val k = Random.nextInt(5)
      println(s"${s} ${k}")
    }
  }

  def solve = {
    val lineIt = io.Source.fromFile("/Users/balaudt/Temp/snck.qual/kgood-sin.in").getLines()
//    System.setOut(new PrintStream("/Users/balaudt/Temp/snck.qual/kgood-cor.out"))
    val t = lineIt.next().toInt
    for (i <- 1 to t) {
      val line = lineIt.next().split(" ")
      val (s, k) = (line(0), line(1).toInt)
      val freqMap = mutable.HashMap[Char, Int]()
      for (j <- 0 to 26) freqMap += ('a' + j).toChar -> 0
      s.foreach(x => freqMap += x -> (freqMap.get(x).get + 1))
      val a = freqMap.values.filter(_ != 0).toArray.sorted
      println(a.groupBy(x => x).map(x => (x._1, x._2.size)))
      val (kl, kr) = (k / 2, if (k % 2 == 0) k / 2 else k / 2 + 1)

      def cost(x: Int) = {
        var ans = 0
        for (y <- a) {
          if (y < x - kl)
            ans += y
          else if (y > x + kr)
            ans += y - (x + kr)
        }
        ans
      }

      var ans = Int.MaxValue
      for (j <- a.head to a.last) {
        val cur = cost(j)
        if (cur < ans) ans = cur
        println((j, cur))
      }
      println(ans)
    }
  }

  solve
}
