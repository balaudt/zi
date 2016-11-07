package zi.jam.y16.qual

import scala.io.Source

/**
  * Created by balaudt on 4/8/16.
  */
object FractilesSmall extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val kcs = lineIt.next().split(" ").map(_.toInt)
    val (k, c) = (kcs(0), kcs(1))
    var kc = 1l
    for (j <- 1 until c) kc *= k
    val ans = (for (j <- 0 until k) yield kc * j + 1).mkString(" ")
    println(s"Case #$i: $ans")
  }
}
