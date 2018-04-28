package zi.fb.cup17.r1

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 1/14/17.
  */
object Zombie extends App {

  case class Pt(x: Long, y: Long)

  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nr = lines.next().split(" ").map(_.toInt)
    val (n, r) = (nr(0), nr(1))
    val pts = (1 to n).map(_ => lines.next().split(" ").map(_.toLong)).map(p => Pt(p(0), p(1)))
    val xs = Set[Long]() ++ pts.map(_.x)
    val ys = Set[Long]() ++ pts.map(_.y)
    val ptToSet = mutable.HashMap[Pt, mutable.HashSet[Pt]]()

    //tl: topLeft
    def ptsInRect(tl: Pt) = {
      val out = mutable.HashSet[Pt]()
      for (p <- pts) {
        if (p.x >= tl.x && p.x <= tl.x + r && p.y >= tl.y && p.y <= tl.y + r) out += p
      }
      tl -> out
    }

    for (x <- xs) {
      for (y <- ys) {
        for (dir <- 0 until 4) {
          val rectX = if ((dir & 1) == 1) x else x - r
          val rectY = if ((dir & 2) == 1) y else y - r
          ptToSet += ptsInRect(Pt(rectX, rectY))
        }
      }
    }
    println(ptToSet.toSeq.sortBy(-_._2.size).head)

  }
}
