package zi.chef.y16.snck.qual

import java.io.PrintStream

import scala.util.Random
import scala.io

/**
  * Created by balaudt on 5/28/16.
  */
object MaximalSumTest extends App {
  def gen = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/snck.qual/max.in"))
    val t = 10 + Random.nextInt(10)
    println(t)
    for (i <- 1 to t) {
      val n = 20 + Random.nextInt(20)
      println(n)
      val a = Array.ofDim[Int](n)
      for (j <- 0 until n) a(j) = Random.nextInt(1000) * (if (Random.nextBoolean()) 1 else -1)
      println(a.mkString(" "))
    }
  }

  def solve = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/snck.qual/max-cor.out"))
    val lineIt = io.Source.fromFile("/Users/balaudt/Temp/snck.qual/max.in").getLines()
    val t = lineIt.next().toInt
    for (i <- 1 to t) {
      val n = lineIt.next().toInt
      val a = lineIt.next().split(" ").map(_.toLong)
      var maxOut = a.max
      for (j <- 0 until n) {
        for (start <- 0 until n - 1) {
          for (end <- start + 1 until n) {
            val cur = a.slice(start, end + 1).filter(_ != a(j)).sum
            if (cur > maxOut) {
              maxOut = cur
              //              println((maxOut, start, end, j))
            }
          }
        }
      }
      println(maxOut)
    }
  }

  var c = 'a'
  println((c + 1).toChar)
}
