package zi.chef.y16.feb

import scala.io.Source

/**
  * Created by balaudt on 8/27/16.
  */
object CombinationLock extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val current = lines.next().split(" ").map(_.toByte)
  val desired = lines.next().split(" ").map(_.toByte)
  var noOfRot = 0
  for (i <- 0 until 5) noOfRot += Math.min(Math.abs(current(i) - desired(i)), 10 - Math.abs(current(i) - desired(i)))
  println(noOfRot)
}
