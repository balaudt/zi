package zi.chef.y16.snck.r1A

/**
  * Created by balaudt on 6/4/16.
  */
object Candies extends App {
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  private val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val abcd = lineIt.next().split(" ").map(_.toLong)
    val (a, b, c, d) = (abcd(0), abcd(1), abcd(2), abcd(3))
    if (a == b)
      println(0)
    else {
      val gcd = BigInt.apply(c).gcd(BigInt.apply(d)).toLong
      if (gcd == 1)
        println(0)
      else {
        var e = Math.abs(a - b)
        if (e > gcd) {
          e -= gcd * (e / gcd)
        }
        if (gcd - e < e)
          e = gcd - e
        println(e)
      }
    }
  }

}
