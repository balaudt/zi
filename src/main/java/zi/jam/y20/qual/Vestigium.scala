package zi.jam.y20.qual

import scala.collection.mutable
import scala.io.Source

object Vestigium extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val n = lines.next().toInt
    val in = new Array[IndexedSeq[Int]](n)
    var (trace, repRow, repCol) = (0, 0, 0)
    for (j <- 0 until n) {
      in(j) = lines.next().split(" ").map(_.toInt)
      if (in(j).toSet.size < n)
        repRow += 1
      trace += in(j)(j)
    }
    for (j <- 0 until n) {
      val col = new mutable.HashSet[Int]()
      var isRep = false
      for (k <- 0 until n) {
        if (col.contains(in(k)(j)))
          isRep = true
        col += in(k)(j)
      }
      if (isRep)
        repCol += 1
    }
    println(s"Case #$i: $trace $repRow $repCol")
  }
}
