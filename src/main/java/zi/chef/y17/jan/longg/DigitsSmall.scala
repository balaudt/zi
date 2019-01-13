package zi.chef.y17.jan.longg

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 1/12/17.
  */
object DigitsSmall extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt

  def gcd(a: Int, b: Int): Int = if (b > a) gcd(b, a) else if (b == 0) a else gcd(b, a % b)

  for (_ <- 1 to t) {
    val n = lines.next().toInt
    val s = lines.next()
    val mxy = lines.next().split(" ").map(_.toInt)
    val (m, x, y) = (mxy(0), mxy(1), mxy(2))
//    if (m > 2) throw new UnsupportedOperationException
    val memo = mutable.HashMap[(Int, Int, Int), Int]()

    def f(st: Int, x: Int, y: Int): Int = {
      if (memo.contains((st, x, y))) return memo((st, x, y))
      if (st >= n && x == 0) return 0
      if (y == 0) {
        val out = Integer.parseInt(s.substring(st))
        memo += (st, x, y) -> out
        return out
      }
      val minChars = Math.max(n - st - y * m, 1)
      val maxChars = Math.min(m, n - st - x)
      var maxGcd = -1
      for (chars <- minChars to maxChars) {
        val next = f(st + chars, Math.max(x - 1, 0), y - 1)
        if (next != -1) {
          val cur = Integer.parseInt(s.substring(st, st + chars))
          val div = gcd(cur, next)
          if (div > maxGcd) maxGcd = div
        }
      }
      memo += (st, x, y) -> maxGcd
      maxGcd
    }

    println(f(0, x, y))
//    println(memo.toSeq.sortBy(_._1).mkString("\n"))

  }


}
