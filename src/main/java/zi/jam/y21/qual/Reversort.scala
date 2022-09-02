package zi.jam.y21.qual

import scala.io.Source

object Reversort {

  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val n = lines.next().toInt
      val arr = lines.next().split(" ").map(_.toInt)
      println(s"Case #$i: ${reverseSort(arr)}")
    }
  }

  def reverseSort(v: Array[Int]): Int = {
    var res = 0
    for (i <- 0 until v.length - 1) {
      val ind = v.indexOf(i + 1)
      for (j <- 0 to (ind - i) / 2) {
        val t = v(i + j)
        v(i + j) = v(ind - j)
        v(ind - j) = t
      }
      res += ind - i + 1
    }
    res
  }
}
