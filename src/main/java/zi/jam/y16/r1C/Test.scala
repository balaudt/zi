package zi.jam.y16.r1C

import scala.io
/**
  * Created by balaudt on 5/8/16.
  */
object Test extends App {
  io.Source.fromInputStream(System.in).getLines().foreach(println(_))
}
