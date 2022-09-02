package zi.fb.cup20.r1

import scala.io.Source

object Perimetric {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    val P = 1e9.toInt + 7
    for (tc <- 1 to t) {
      val nkw = lines.next().split(" ").map(_.toInt)
      val (n, k, w) = (nkw(0), nkw(1), nkw(2))
      val l = lines.next().split(" ").map(_.toInt).toBuffer
      val abcdL = lines.next().split(" ").map(_.toLong)
      val (aL, bL, cL, dL) = (abcdL(0), abcdL(1), abcdL(2), abcdL(3))
      val h = lines.next().split(" ").map(_.toInt).toBuffer
      val abcdH = lines.next().split(" ").map(_.toLong)
      val (aH, bH, cH, dH) = (abcdH(0), abcdH(1), abcdH(2), abcdH(3))
      for (i <- k until n) {
        l += ((aL * l(i - 2) + bL * l(i - 1) + cL) % dL).toInt
        h += ((aH * h(i - 2) + bH * h(i - 1) + cH) % dH).toInt
      }
      var p = ((l.head + h.head) * 2L) % P
      var prod = p
      for (i <- 1 until n) {
        if (l(i) == l(i - 1)) {
          if (h(i) > h(i - 1)) {
            p += 2 * (h(i) - h(i - 1))
            p %= P
          }
        } else if (l(i) > l(i - 1) + w) {
          p += 2 * (h(i) + w)
          p %= P
        } else {
          if (h(i) > h(i - 1)) {
            p += h(i) - h(i - 1)
            p %= P
          }

        }
        prod *= p
        prod %= P
      }
    }
  }
}
