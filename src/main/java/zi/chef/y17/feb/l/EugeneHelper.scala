package zi.chef.y17.feb.l

import scala.collection.mutable
import scala.util.Random

/**
  * Created by balamurugan on 2/4/17.
  */
object EugeneHelper extends App {
  def brute() = {
    val (a, m) = (Random.nextInt(1000) + 100, Random.nextInt(100) + 1)
    println(s"a,m = $a,$m")
    var ai = a.toString
    val amm = a % m
    var bmm = amm
    val tenElm = Math.pow(10, ai.length).toInt % m
    for (i <- 1 to 6) {
      println(s"brute=${ai.toLong % m}")
      println(s"method=$bmm")
      bmm = bmm * tenElm + amm
      bmm %= m
      ai += a.toString
    }
  }

  def method() = {
    val (a, m) = (Random.nextInt(1e9.toInt) + 10000, Random.nextInt(100000) + 1)
    println(s"a,m = $a,$m")
    val amm = a % m
    var bmm = amm
    val tenElm = Math.pow(10, a.toString.length).toInt % m
    var cycleFound = false
    val visitedModuli = mutable.HashSet[Int]()
    visitedModuli += bmm
    for (i <- 1 to 100 if !cycleFound) {
      bmm = bmm * tenElm + amm
      bmm %= m
      cycleFound = !visitedModuli.add(bmm)
    }
    println(cycleFound)
    println(visitedModuli.size)
  }

  method()
}
