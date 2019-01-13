package zi.jam.y17.qual

import scala.io.Source

/**
  * Created by balamurugan on 4/8/17.
  */
object Pancake extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val sk = lines.next().split(" ")
    val (s, k) = (Integer.parseInt(new String(sk(0).toCharArray.map(x => if (x == '-') '0' else '1')), 2), sk(1).toInt)
    val n = sk(0).length
    val dest = (1 << n) - 1
    if (s == dest) {
      println(s"Case #$i: 0")
    }
    else {
      val flipper = (1 << k) - 1
      var (num, ct) = (s, 0)
      while (num < dest) {
        var (t, st) = (1, 0)
        while ((num & t) != 0) {
          t <<= 1
          st += 1
        }
        num ^= flipper << st
        ct += 1
      }
      if (num == dest) println(s"Case #$i: $ct")
      else println(s"Case #$i: IMPOSSIBLE")
    }
  }
}
