package zi.chef.y16.feb

import scala.io.Source
import scala.collection.Searching._

/**
  * Created by balaudt on 8/27/16.
  */
object Bonestrousle extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  val P = 1e5.toLong
  val triangles = (1l to P).scan(0l)(_ + _).tail
  for (i <- 1 to t) {
    val nkb = lines.next().split(" ")
    val (n, k, b) = (nkb(0).toLong, nkb(1).toLong, nkb(2).toInt)
    val ip = triangles.search(n) match {
      case Found(x) => x
      case InsertionPoint(x) => x
    }
    if (k < ip)
      println(-1)
    else {

    }
  }
}
