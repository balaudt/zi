package zi.chef.y16.snck.r1B

import java.io.{FileInputStream, PrintStream}

import scala.util.Random
import scala.io

/**
  * Created by balaudt on 6/19/16.
  */
object ChocalateTester extends App {
  def generate = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/choc.in"))
    println(1000)
    for (i <- 1 to 1000) {
      val flag = Random.nextBoolean()
      val n = Random.nextInt(50) + 50
      if (flag) {
        val c = (Random.nextInt(50) + 50) * n
        println(s"$n $c")
      } else {
        val c = Random.nextInt(100000) + 100000
        println(s"$n $c")
      }
    }
  }

  def solve = {
    //    System.setOut(new PrintStream("/Users/balaudt/Temp/choc.c.out"))
    System.setIn(new FileInputStream("/Users/balaudt/Temp/choc-sin.in"))
    val lineIt = io.Source.fromInputStream(System.in).getLines()
    val t = lineIt.next().toInt
    for (i <- 1 to t) {
      val nc = lineIt.next().split(" ").map(_.toInt)
      val (n, c) = (nc(0), nc(1))
      var flag = false
      for (a <- 1 to n if !flag)
        for (d <- 1 to n if !flag)
          if (n * a + n * (n - 1) / 2 * d == c) {
            println((a, d))
            flag = true
          }
      if (flag)
        println("Yes")
      else
        println("No")
    }
  }

  //  generate
  solve
}
