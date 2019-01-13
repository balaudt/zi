package zi.chef.y16.may

import scala.collection.mutable
import scala.io

/**
  * Created by balaudt on 5/9/16.
  */
object BigSoccer extends App {
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 0 until t) {
    val nms = lineIt.next().split(" ").map(_.toInt)
    val (n, m, s) = (nms(0), nms(1), nms(2))
    val a = lineIt.next().split(" ").map(_.toInt)
    var dogsWithBall = mutable.HashMap[Int, Int]()
    dogsWithBall += s -> 1
    var (pass, flag) = (0, true)
    while (pass < m && flag) {
      if (dogsWithBall.isEmpty) flag = false
      else {
        val strength = a(pass)
        val newDogs = mutable.HashMap[Int, Int]()
        for (lastDog <- dogsWithBall) {
          if (lastDog._1 + strength <= n)
            newDogs.get(lastDog._1 + strength) match {
              case Some(x) => newDogs += (lastDog._1 + strength) -> (x + lastDog._2)
              case None => newDogs += (lastDog._1 + strength) -> lastDog._2
            }
          if (lastDog._1 - strength >= 1)
            newDogs.get(lastDog._1 - strength) match {
              case Some(x) => newDogs += (lastDog._1 - strength) -> (x + lastDog._2)
              case None => newDogs += (lastDog._1 - strength) -> lastDog._2
            }
        }
        dogsWithBall = newDogs
      }
      pass += 1
    }
    if (pass < m) {
      println(Array.fill(n)(0).mkString(" "))
    } else {
      for (j <- 1 to n) {
        dogsWithBall.get(j) match {
          case Some(x) => print(x + " ")
          case None => print(0 + " ")
        }
      }
      println()
    }
  }
}
