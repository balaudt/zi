package zi.chef.y17.jan.longg

import java.io.PrintStream

import scala.util.Random

/**
  * Created by balamurugan on 1/12/17.
  */
object DigitHelper extends App {
  private def smallGen() = {
    System.setOut(new PrintStream("/Users/balamurugan/Temp/dec/digits.in"))
    println(10)
    for (_ <- 1 to 10) {
      val n = Random.nextInt(18) + 2
      println(n)
      for (_ <- 1 to n) {
        print(Random.nextInt(10))
      }
      println()
      val x = Random.nextInt(n - 1) + 1
      val ylim = n - 1 - x + 1
      val y = Random.nextInt(ylim) + x
      println(s"${Random.nextInt(1) + 1} $x $y")
    }
  }

  private def smallBrute() = {

  }
}
