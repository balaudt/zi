package zi.chef.y17.jan.longg

import scala.util.Random

/**
  * Created by balamurugan on 1/11/17.
  */
object FourTester extends App {
  private def randomSign(): Int = if (Random.nextBoolean()) 1 else -1

  private def gen() = {
    val n = Random.nextInt(100) + 100
    println((0 until n).map(_ => (Random.nextInt(1000).toFloat * randomSign() + 1, Random.nextInt(1000).toFloat * randomSign() + 1)).mkString("\n"))
  }
  gen()
}
