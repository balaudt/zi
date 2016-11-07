package zi.chef.y16.apr

import scala.io.Source

/**
  * Created by balaudt on 4/1/16.
  */
object Color extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val n = lineIt.next().toInt
  for (i <- 1 to n) {
    val cts = Array(0, 0, 0)
    val len = lineIt.next().toInt
    lineIt.next().toCharArray.foreach(x => x match {
      case 'R' => cts(0) += 1
      case 'G' => cts(1) += 1
      case _ => cts(2) += 1
    })
    println(len - cts.max)
  }
}
