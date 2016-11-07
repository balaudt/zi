package zi.chef.y16.may

import scala.io.Source

/**
  * Created by balaudt on 5/7/16.
  */
object Laddu extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 0 until t) {
    val actOr = lineIt.next().split(" ").map(_.trim())
    val act = actOr(0).toInt
    val isInd = actOr(1).startsWith("IND")
    var laddus = 0
    for (j <- 0 until act) {
      val activity = lineIt.next().split(" ")
      activity(0) match {
        case "CONTEST_WON" => laddus += (if (activity(1).toInt > 20) 300 else 300 + (20 - activity(1).toInt))
        case "TOP_CONTRIBUTOR" => laddus += 300
        case "BUG_FOUND" => laddus += activity(1).toInt
        case "CONTEST_HOSTED" => laddus += 50
      }
    }
    println(if (isInd) laddus / 200 else laddus / 400)
  }
}
