package zi.fb.cup17.qual

import scala.util.Random
import scala.io

/**
  * Created by balaudt on 1/7/17.
  */
object PieProgressTester extends App {
  val radius = 50

  case class Point(x: Double, y: Double)

  private def areClockwise(v1: Point, v2: Point) = {
    val res = -v1.x * v2.y + v1.y * v2.x > 0
    //        println((v1, v2, res))
    res
  }

  private def isWithinRadius(v: Point) = v.x * v.x + v.y * v.y <= radius * radius

  private def isInsideSector(p: Point, sectSt: Point, sectEnd: Point) = {
    areClockwise(sectSt, p) && !areClockwise(sectEnd, p) && isWithinRadius(p)
  }

  private def test() = {
    val lines = io.Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    val ang = 2 * Math.PI * 3.6 / 360
    for (i <- 1 to t) {
      val pxy = lines.next().split(" ").map(_.toInt)
      val (p, x, y) = (pxy(0), pxy(1) - 50, pxy(2) - 50)
      val sectSt = if (p >= 50) Point(0, -50) else Point(0, 50)
      val theta = Math.PI / 2 - ang * p
      val sectEnd = Point(radius * Math.cos(theta), radius * Math.sin(theta))
      var color = if (isInsideSector(Point(x, y), sectSt, sectEnd)) "black" else "white"
      if (p >= 50 && x >= 0 && isWithinRadius(Point(x, y))) color = "black"
      println(s"Case #$i: $color")
    }
  }

  private def gen() = {
    val t = Random.nextInt(500) + 500
    println(t)
    for (_ <- 1 to t) {
      val a = (1 to 3).map(_ => {
        var i = 50
        while (i == 50) i = Random.nextInt(100) + 1
        i
      })
      println(a.mkString(" "))
    }
  }

  test()
}
