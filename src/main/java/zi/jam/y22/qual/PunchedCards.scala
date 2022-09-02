package zi.jam.y22.qual

import scala.io.Source

object PunchedCards {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val in = lines.next().split(" ").map(_.toInt)
      val R = in(0)
      val C = in(1)
      println(s"Case #$i:")
      for (r <- 0 to 2*R) {
        val line = (if(r % 2 ==0) ("+-" * C) + "+" else ("|." * C) + "|").toCharArray
        if(r == 0 || r == 1) {
          line(0) = '.'
          line(1) = '.'
        }
        println(new String(line))
      }
    }
  }
}
