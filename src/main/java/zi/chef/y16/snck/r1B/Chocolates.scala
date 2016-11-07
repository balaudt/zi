package zi.chef.y16.snck.r1B

import java.io.{FileInputStream, PrintStream}

/**
  * Created by balaudt on 6/19/16.
  */
object Chocolates extends App {
  System.setOut(new PrintStream("/Users/balaudt/Temp/choc.out"))
  System.setIn(new FileInputStream("/Users/balaudt/Temp/choc.in"))
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  //  println(extendedEuclid(4, 6))
  for (i <- 1 to t) {
    val nc = lineIt.next().split(" ")
    val (n, c) = (nc(0).toInt, BigInt(nc(1)))
    if (n == 1)
      println("Yes")
    if (c % n != 0)
      println("No")
    else {
      var a: Int = 0
      var b: Int = 0
      if ((n - 1) % 2 == 0) {
        a = 1
        b = (n - 1) / 2
      }
      else {
        a = 2
        b = n - 1
      }

      val (gcd, x0, y0) = extendedEuclid(a, b)
      var left = -x0.toDouble / b
      var right = y0.toDouble / a
      if (left < 0)
        left = left.floor
      else
        left = left.ceil
      if (right < 0)
        right = right.floor
      else
        right = right.ceil
      if (right - left >= 1)
        println("Yes")
      else
        println("No")
    }
  }

  def extendedEuclid(a: Int, b: Int): (Int, Int, Int) = {
    var q: Int = 0
    if (b == 0) {
      return (a, 1, 0)
    } else {
      q = a / b
      val ans = extendedEuclid(b, a % b)
      val temp = ans._2 - ans._3 * q
      return (ans._1, ans._3, temp)
    }
  }
}
