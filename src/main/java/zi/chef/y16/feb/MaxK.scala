package zi.chef.y16.feb

import java.util

/**
  * Created by balaudt on 8/13/16.
  */
object MaxK extends App {
  val in = Array(6, 7, 9, 8, 10)
  util.Arrays.sort(in)
  var found = false
  for (i <- in.length - 1 to 0 by -1 if (!found))
    if (in.length - i >= in(i)) {
      println(in(i))
      found = true
    }
  if (!found)
    println(-1)
}
