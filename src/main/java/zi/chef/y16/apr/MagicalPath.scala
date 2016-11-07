package zi.chef.y16.apr

import scala.io.Source

/**
  * Created by balaudt on 4/1/16.
  */
object MagicalPath extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val nm = lineIt.next().split(" ").map(_.toLong)
    val (n, m) = (nm(0), nm(1))
    if (n == 1 && m == 1)
      println("No")
    else if (n == 1 && m == 2)
      println("Yes")
    else if (n == 2 && m == 1)
      println("Yes")
    else if (n == 1 || m == 1)
      println("No")
    else {
      if (n % 2 == 0 || m % 2 == 0)
        println("Yes")
      else
        println("No")
    }
  }
}
