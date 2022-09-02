package zi.jam.y19.qual

import scala.io.Source

object NonCrossingPath extends App{
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    lines.next()
    val in = lines.next().toCharArray
    val out = in.map(x => if (x == 'S') 'E' else 'S')
    println(s"Case #$i: ${new String(out)}")
  }
}
