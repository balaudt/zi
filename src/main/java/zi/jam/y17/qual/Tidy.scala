package zi.jam.y17.qual

import scala.io.Source

/**
  * Created by balamurugan on 4/7/17.
  */
object Tidy extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (it <- 1 to t) {
    val s = lines.next().toCharArray
    val n = s.length
    var i = 0
    while (i < n - 1 && s(i) <= s(i + 1)) i += 1
    if (i == n - 1) println(s"Case #$it: ${new String(s)}")
    else {
      for (j <- i + 1 until n) s(j) = '9'
      s(i) = (s(i) - 1).toChar
      while (i > 0 && s(i) < s(i - 1)) {
        s(i) = '9'
        s(i - 1) = (s(i - 1) - 1).toChar
        i -= 1
      }
      println(s"Case #$it: ${new String(s).toLong}")
    }
  }

}
