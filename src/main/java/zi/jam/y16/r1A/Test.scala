package zi.jam.y16.r1A

import scala.io.Source

/**
  * Created by balaudt on 4/14/16.
  */
object Test extends App {
  Source.fromInputStream(System.in).getLines().foreach(println(_))
}
