package zi.jam.y16.r1A

import scala.io.Source

/**
  * Created by balaudt on 4/15/16.
  */
object LastWord extends App {
  val lineIt = Source.fromInputStream(System.in).getLines();
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val ans = new StringBuilder
    val s = lineIt.next().toCharArray
    ans.append(s(0))
    s.tail.foreach(x => {
      if (x < ans.charAt(0))
        ans.append(x)
      else
        ans.insert(0, x)
    })
    println(s"Case #$i: $ans")
  }
}
