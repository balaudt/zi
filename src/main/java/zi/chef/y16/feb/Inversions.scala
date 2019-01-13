package zi.chef.y16.feb

import scala.io.Source

/**
  * Created by balaudt on 8/8/16.
  */
object Inversions extends App {
  //    val in = Array[Int](2, 4, 1, 3, 5)
  //  val in = Array[Int](1, 20, 6, 4, 5)
  val in = Source.fromFile("/Users/balaudt/Temp/inv.in").getLines().map(_.toInt).toArray
  var (j: Int, t: Int, ic: Long) = (0, 0, 0l)
  for (i <- 1 until in.length) {
    j = i
    while (j > 0 && in(j) < in(j - 1)) {
      t = in(j)
      in(j) = in(j - 1)
      in(j - 1) = t
      ic += 1
      j -= 1
    }
  }
  //  println(in.mkString(","))
  println(ic)
}
