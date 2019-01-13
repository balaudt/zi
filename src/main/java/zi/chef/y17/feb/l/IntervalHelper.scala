package zi.chef.y17.feb.l

import java.util

/**
  * Created by balamurugan on 2/8/17.
  */
object IntervalHelper extends App {
  val in = Array(12, 1, 78, 90, 57, 89, 56)
  val k = 3
  val q = new util.ArrayDeque[Int](k)
  for (i <- 0 until k) {
    while (!q.isEmpty && in(i) >= in(q.getLast)) q.removeLast()
    q.addLast(i)
  }
  for (i <- k until in.length) {
    print(s"${in(q.getFirst)} ")
    while (!q.isEmpty && q.getFirst <= i - k) q.removeFirst()
    while (!q.isEmpty && in(i) >= in(q.getLast)) q.removeLast()
    q.addLast(i)
  }
  print(s"${in(q.getFirst)}")
}
