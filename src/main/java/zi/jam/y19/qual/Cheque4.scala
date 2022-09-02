package zi.jam.y19.qual

import scala.io.Source

object Cheque4 extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val in = lines.next().toCharArray
    val out = new Array[Char](in.length)
    var ind = -1
    for (j <- in.length - 1 to 0 by -1) {
      if (in(j) == '4') {
        in(j) = '3'
        out(j) = '1'
        ind = j
      } else {
        out(j) = '0'
      }
    }
    println(s"Case #$i: ${new String(in)} ${new String(out).substring(ind)}")
  }
}
