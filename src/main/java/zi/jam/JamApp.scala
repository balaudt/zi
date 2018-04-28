package zi.jam

import scala.io.Source

/**
  * Created by balamurugan on 4/16/17.
  */
trait Utils {

}

trait JamApp[T] extends App with Utils {
  def execute(lines: Iterator[String], i: Int): T

  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val result = execute(lines, i)
    if (!result.isInstanceOf[Unit]) println(s"Case #$i: $result")
  }
}

