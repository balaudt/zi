package zi.chef.y17.feb.l

import scala.io.Source

/**
  * Created by balamurugan on 2/4/17.
  */
object ApartmentDue extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (_ <- 1 to t) {
    val n = lines.next().toInt
    val a = lines.next().split(" ").map(_.equals("0")).zipWithIndex
    val out = a.count(_._1) * 1000l + (a.find(_._1) match {
      case Some(x) => (n - x._2) * 100l
      case None => 0l
    })
    println(out)
  }
}
