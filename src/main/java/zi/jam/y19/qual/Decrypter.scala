package zi.jam.y19.qual

import scala.io.Source

object Decrypter extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nl = lines.next().split(" ").map(_.toInt)
    val (n, l) = (nl(0), nl(1))
    val in = lines.next().split(" ").map(x => BigInt(x))
    val out = new Array[BigInt](l + 1)
    var p = gcd(in(0), in(1))
    out(0) = in(0) / p
    out(1) = p
    out(2) = in(1) / out(1)
    for (j <- 2 until l) {
      out(j + 1) = in(j) / out(j)
    }
    val lookup = out.toSet.toList.sorted.zipWithIndex.toMap
    val outStr = new String(out.map(x => ('A' + lookup(x)).toChar))
    println(s"Case #$i: $outStr")
  }

  def gcd(a1: BigInt, b1: BigInt): BigInt = {
    if (a1 < b1)
      return gcd(b1, a1)
    var t: BigInt = null
    var (a, b) = (a1, b1)
    while (b != 0) {
      t = b
      b = a % b
      a = t
    }
    return a
  }
}
