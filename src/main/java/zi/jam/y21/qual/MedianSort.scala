package zi.jam.y21.qual

import scala.collection.mutable
import scala.io.Source

object MedianSort {
  val lines = Source.fromInputStream(System.in).getLines()

  def insert(b: mutable.ArrayBuffer[Int], z: Int, st: Int): Unit = {
    val (x, y) = (b(st), b(st + 1))
    println(s"$x $y $z")
    val res = lines.next().toInt
    if (res == -1)
      System.exit(1)
    if (res == y)
      b.insert(st + 2, z)
    else if (res == z)
      b.insert(st + 1, z)
    else {
      if (st == 0)
        b.insert(0, z)
      else
        insert(b, z, st - 1)
    }
  }

  def main(args: Array[String]): Unit = {
    val tnq = lines.next().split(" ").map(_.toInt)
    val (t, n) = (tnq(0), tnq(1))
    for (_ <- 1 to t) {
      val out = mutable.ArrayBuffer[Int]()
      println("1 2 3")
      val res = lines.next().toInt
      res match {
        case 1 => out ++= Array(2, 1, 3)
        case 2 => out ++= Array(1, 2, 3)
        case 3 => out ++= Array(1, 3, 2)
      }
      for (j <- 4 to n)
        insert(out, j, j - 3)
      println(out.mkString(" "))
      if (lines.next.toInt == -1)
        System.exit(1)
    }
  }
}
