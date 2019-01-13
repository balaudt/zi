package zi.jam.y17.qual

import java.util

import scala.io.Source

/**
  * Created by balamurugan on 4/8/17.
  */
object PancakeLarge extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val sk = lines.next().split(" ")
    val s = new util.BitSet(sk(0).length)
    sk(0).toCharArray.zipWithIndex.foreach(x => if (x._1 == '+') s.set(x._2))
    val k = sk(1).toInt
    val n = sk(0).length
    if (s.cardinality() == n) println(s"Case #$i: 0")
    else {
      var (last, ct) = (0, 0)
      while (s.cardinality() < n) {
        val st = s.nextClearBit(last)
        s.flip(st, st + k)
        last = st + 1
        ct += 1
      }
      if (s.cardinality() > n || s.nextClearBit(0) < n) println(s"Case #$i: IMPOSSIBLE")
      else println(s"Case #$i: $ct")
    }
  }
}
