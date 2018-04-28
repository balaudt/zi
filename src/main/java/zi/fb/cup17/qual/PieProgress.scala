package zi.fb.cup17.qual

import scala.io

/**
  * Created by balaudt on 1/7/17.
  */
object PieProgress extends App {
  val lines = io.Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  val (r, ang) = (50, 2 * Math.PI * 3.6 / 360)
  for (i <- 1 to t) {
    val pxy = lines.next().split(" ").map(_.toInt)
    val (p, x, y) = (pxy(0), pxy(1) - 50, pxy(2) - 50)
    val d = Math.sqrt(x * x + y * y)
    var isBlack = false
    if (p > 0 && d <= r) {
      if (p == 100) isBlack = true
      else {
        val mArc = Math.tan(Math.PI / 2 - ang * p)
        val mLine = y.toDouble / x
        //      println((mArc, mLine))
        if (p >= 50) {
          if (x >= 0 || mLine > mArc) isBlack = true
        }
        else if (x >= 0 && mLine > mArc) isBlack = true
      }
    }
    val color = if (isBlack) "black" else "white"
    println(s"Case #$i: $color")
  }
}
