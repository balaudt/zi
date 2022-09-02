package zi.jam.y22.qual

import scala.io.Source

object Printing3D {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    val target = 1e6.toInt
    for (i <- 1 to t) {
      val cartridgeCaps = Array.ofDim[Int](4, 3)
      for (j <- 0 until 3) {
        val line = lines.next().split(" ").map(_.toInt)
        for (k <- 0 until 4) {
          cartridgeCaps(k)(j) = line(k)
        }
      }
      val maxPos = Array.ofDim[Int](4)
      for (j <- 0 until 4) {
        maxPos(j) = Math.min(cartridgeCaps(j).min, target - maxPos.sum)
      }
      if(maxPos.sum == target) {
        val result = maxPos.mkString(" ")
        println(s"Case #$i: $result")
      } else {
        println(s"Case #$i: IMPOSSIBLE")
      }
    }
  }

}
