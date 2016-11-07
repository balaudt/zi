package zi.jam.y16.qual

import scala.io.Source

/**
  * Created by balaudt on 4/8/16.
  */
object CoinJamHelper extends App {
  println(Integer.parseInt("12345",6))
  val lineIt = Source.fromFile("/Users/balaudt/Dev/zi/io/16/qual/C-large.out").getLines()
  assert(lineIt.next().equals("Case #1:"))
  var (ct, flag) = (0, true)
  for (line <- lineIt if (flag)) {
    if (line.isEmpty)
      flag = false
    else {
      val jamCoin = line.split(" ").head
      val factors = line.split(" ").tail.map((_.toInt))
      for (base <- 2 to 10) {
        assert(BigInt.apply(jamCoin, base).mod(factors(base - 2)) == 0)
      }
      ct += 1
    }
  }
  assert(ct == 500)
}
