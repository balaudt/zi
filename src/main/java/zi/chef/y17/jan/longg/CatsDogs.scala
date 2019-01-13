package zi.chef.y17.jan.longg

import scala.io.Source

/**
  * Created by balamurugan on 1/8/17.
  */
object CatsDogs extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (_ <- 1 to t) {
    val cdl = lines.next().split(" ").map(_.toInt)
    val (c, d, l) = (cdl(0), cdl(1), cdl(2))
    val max = 4l * (c + d)
    var min = d.toLong
    if (c > 2 * d)
      min += c - 2 * d
    min *= 4
    if (l % 4 == 0 && l >= min && l <= max) println("yes")
    else println("no")
  }
}
