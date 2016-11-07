package zi.jam.y16.r1B

import scala.io.Source

/**
  * Created by balaudt on 4/30/16.
  */
object Match extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    var in: Array[Array[Char]] = lineIt.next().split(" ").map(_.toCharArray)
    var st = 0
    val l = in(0).length
    for (j <- 0 until l) {
      if (st == 0) {
        if (in(0)(j) != '?' && in(1)(j) != '?') {
          if (in(0)(j) != in(1)(j))
            st = if (in(0)(j) > in(1)(j)) 1 else 2
        } else if (in(0)(j) != '?' && in(1)(j) == '?') {
          in(1)(j) = in(0)(j)
        } else if (in(0)(j) == '?' && in(1)(j) != '?') {
          in(0)(j) = in(1)(j)
        } else {
          in(0)(j) = '0'
          in(1)(j) = '0'
        }
      } else if (st == 1) {
        if (in(0)(j) != '?' && in(1)(j) != '?') {
          //No Op
        } else if (in(0)(j) != '?' && in(1)(j) == '?') {
          in(1)(j) = '9'
        } else if (in(0)(j) == '?' && in(0)(j) != '?') {
          in(0)(j) = '0'
        } else {
          in(0)(j) = '0'
          in(1)(j) = '9'
        }
      } else {
        if (in(0)(j) != '?' && in(1)(j) != '?') {
          //No Op
        } else if (in(0)(j) != '?' && in(1)(j) == '?') {
          in(1)(j) = '0'
        } else if (in(0)(j) == '?' && in(0)(j) != '?') {
          in(0)(j) = '9'
        } else {
          in(0)(j) = '9'
          in(1)(j) = '0'
        }
      }
    }
    println(s"Case #$i: ${new String(in(0))} ${new String(in(1))}")
  }
}
