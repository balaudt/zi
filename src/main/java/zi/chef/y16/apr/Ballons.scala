package zi.chef.y16.apr

import scala.io.Source

/**
  * Created by balaudt on 4/1/16.
  */
object Ballons extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val rgb = lineIt.next().split(" ").map(_.toLong)
    val k = lineIt.next().toInt
    var ans = 1L
    rgb.foreach(x => if (x >= k - 1) ans += k - 1 else ans += x)
    println(ans)
  }
}
