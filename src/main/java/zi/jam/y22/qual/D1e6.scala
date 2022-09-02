package zi.jam.y22.qual

import scala.io.Source

object D1e6 {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val n = lines.next().toInt
      var ans = 0
      val s = lines.next().split(" ").map(_.toInt).sorted
      for (j <- 0 until n) {
        if(s(j) > ans)
          ans += 1
      }
      println(s"Case #$i: $ans")
    }
  }
}
