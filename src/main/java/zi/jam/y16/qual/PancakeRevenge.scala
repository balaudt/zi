package zi.jam.y16.qual

import scala.io.Source

/**
  * Created by balaudt on 4/8/16.
  */
object PancakeRevenge extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val s = lineIt.next().toCharArray
    var c = s(0)
    var fc = 0
    for (i <- 1 until s.length) {
      if (s(i) != c) {
        fc += 1
        c = s(i)
      }
    }
    if (c == '-') fc += 1
    println(s"Case #$i: $fc")
  }
}
