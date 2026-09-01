package zi.chef.y16.dec

import scala.io.Source

/**
  * Created by balaudt on 12/3/16.
  */
object TrainPartner extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (_ <- 0 until t) {
    val n = lines.next().toInt
    if (n % 8 == 0)
      println(s"${n - 1}SL")
    else {
      val b = (n / 8) * 8
      println(n % 8 match {
        case 1 => s"${b + 4}LB"
        case 2 => s"${b + 5}MB"
        case 3 => s"${b + 6}UB"
        case 4 => s"${b + 1}LB"
        case 5 => s"${b + 2}MB"
        case 6 => s"${b + 3}UB"
        case 7 => s"${b + 8}SU"
      })
    }
  }
}
