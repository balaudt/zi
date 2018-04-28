package zi.fb.cup17.qual

import java.io.PrintStream

import scala.util.Random

/**
  * Created by balamurugan on 1/8/17.
  */
object ZombieTester extends App {
  private def gen() = {
    val t = Random.nextInt(1000) + 1000
    println(t)
    val ys = Array(4, 6, 8, 10, 12, 20)
    for (_ <- 1 to t) {
      val s = Random.nextInt(9) + 2
      val h = Random.nextInt(10000) + 1
      println(s"$h $s")
      val spells = Array.ofDim[String](s)
      for (i <- 0 until s) {
        val x = Random.nextInt(20) + 1
        val y = ys(Random.nextInt(ys.length))
        val z = if (h <= y * x + x) 1 else Random.nextInt(h - x * y - x) + h - x
        spells(i) = s"${x}d${y}+${z}"
      }
      println(spells.mkString(" "))
    }
  }

  val ps = new PrintStream("/Users/balamurugan/Temp/zombie.in")
  System.setOut(ps)
  gen()
  ps.close()
}
