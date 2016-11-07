package zi.chef.y16.feb

import scala.io.Source

/**
  * Created by balaudt on 8/27/16.
  */
object BonAppetit extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val nk = lines.next().split(" ").map(_.toInt)
  val (n, k) = (nk(0), nk(1))
  val c = lines.next().split(" ").map(_.toInt)
  var sum = 0l
  for (i <- c.indices) if (i != k) sum += c(i)
  val (paid, actual) = (lines.next().toInt, sum / 2)
  println(if (paid == actual) "Bon Appetit" else paid - actual)
}
