package zi.jam.y16.qual

import java.util

/**
  * Created by balaudt on 4/8/16.
  */
object CountTest extends App {
  var (mn, maxct) = (-1, 1)
  for (n <- 1 to 1e6.toInt) {
    var ct = 1
    var flags = new util.BitSet(10)
    while (flags.cardinality() != 10) {
      (ct * n).toString.toCharArray.foreach(x => flags.set(x - '0'))
      ct += 1
    }
    if (ct > maxct) {
      maxct = ct
      mn = n
    }
  }
  println((mn, maxct))
}
