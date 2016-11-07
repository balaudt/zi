package zi.chef.y16.feb

import java.util

/**
  * Created by balaudt on 8/13/16.
  */
object Alg1 extends App {
  val in = Array(8, 58, 71, 18, 31, 32, 63, 92,
    43, 3, 91, 93, 25, 80, 28)
  val bm = new util.TreeSet[Int]()
  bm.add(in(in.length - 1))
  in(in.length - 1) = -1
  for (i <- in.length - 2 to 0 by -1) {
    bm.add(in(i))
    var higher = bm.higher(in(i))
    while (higher != null && higher == in(i))
      higher = bm.higher(in(i))
    if (higher != null) {
      in(i) = higher
    } else {
      in(i) = -1
    }
  }
  println(in.mkString(","))
}
