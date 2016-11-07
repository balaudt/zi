package zi.chef.y16.apr

import scala.io.Source

/**
  * Created by balaudt on 4/1/16.
  */
object SaveWatson extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  val P = (1e9 + 7).toInt
  for (i <- 1 to t) {
    val nk = lineIt.next().split(" ").map(_.toInt)
    val (n, k) = (nk(0), nk(1))
    var ans: Long = k
    var pow: Long = k - 1
    Integer.toBinaryString(n - 1).reverse.foreach(x => {
      if (x == '1') {
        ans *= pow
        ans %= P
      }
      pow *= pow
      pow %= P
    })
    println(ans)
  }
}
