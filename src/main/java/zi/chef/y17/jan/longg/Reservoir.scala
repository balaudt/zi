package zi.chef.y17.jan.longg

import scala.io.Source

/**
  * Created by balamurugan on 1/9/17.
  */
object Reservoir extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (_ <- 1 to t) {
    val nm = lines.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val r = (0 until n).map(_ => lines.next().toCharArray)
    var isStable = true
    for (i <- 0 until n if isStable) {
      for (j <- 0 until m if isStable) {
        if (r(i)(j) == 'B') {
          if (i != n - 1 && r(i + 1)(j) != 'B') isStable = false
        } else if (r(i)(j) == 'W') {
          if (j == 0 || j == m - 1) isStable = false
          else if (r(i)(j - 1) == 'A' || r(i)(j + 1) == 'A') isStable = false
          else if (i != n - 1 && r(i + 1)(j) == 'A') isStable = false
        }
      }
    }
    println(if (isStable) "yes" else "no")
  }
}
