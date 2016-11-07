package zi.jam.y16.r1C

import scala.collection.mutable

/**
  * Created by balaudt on 5/8/16.
  */
object Police extends App {

  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val jpsk = lineIt.next().split(" ").map(_.toInt)
    val (j, p, s, k) = (jpsk(0), jpsk(1), jpsk(2), jpsk(3))
    for(x <- 1 to j){
      for(y <- 1 to p){
        for(z <- 1 to s){

        }
      }
    }
  }
}
