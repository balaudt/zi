package zi.jam.y16.qual

import java.util

import scala.io.Source

/**
  * Created by balaudt on 4/8/16.
  */
object CountingSheep extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val n = lineIt.next().toInt
    if (n == 0)
      println(s"Case #$i: INSOMNIA")
    else {
      var ct = 1
      val flags = new util.BitSet(10)
      while (flags.cardinality() != 10) {
        (ct * n).toString.toCharArray.foreach(x => flags.set(x - '0'))
        ct += 1
      }
      println(s"Case #$i: ${n * (ct - 1)}")
    }
  }
}
