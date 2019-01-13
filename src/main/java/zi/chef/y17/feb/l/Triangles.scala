package zi.chef.y17.feb.l

import scala.io.Source

/**
  * Created by balamurugan on 2/4/17.
  */
object Triangles extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (_ <- 1 to t) {
    val nlr = lines.next().split(" ")
    val (n, l, r) = (nlr(0).toInt, nlr(1).toLong, nlr(2).toLong)
    val a = lines.next().split(" ").map(_.toLong)
  }
}
