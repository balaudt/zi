package zi.jam.y16.qual

import java.io.PrintStream

/**
  * Created by balaudt on 4/9/16.
  */
object CoinJamLarge extends App {
  var (ct, lastNum) = (0, (1l << 31) + 1)

  @inline def getAFactor(jamCoin: String, base: Int): Int = {
    val num: BigInt = BigInt.apply(jamCoin, base)
    val digits = num.toString().reverse.toCharArray.map(_.toInt)
    if (digits(0) % 2 == 0)
      return 2
    if (digits.sum % 3 == 0)
      return 3
    if (digits(0) == 0 || digits(0) == 5)
      return 5
    var (oddSum, evenSum, pos) = (0, 0, 0)
    for (digit <- digits) {
      if (pos == 0) {
        evenSum += digit
        pos = 1
      } else {
        oddSum += digit
        pos = 0
      }
    }
    if (oddSum == evenSum)
      return 11
    var numCopy = num
    while (numCopy > 0)
      numCopy = (numCopy / 10) - (2 * (numCopy % 10))
    if (numCopy % 7 == 0)
      return 7
    numCopy = num
    while (numCopy > 0)
      numCopy = (numCopy / 10) - (9 * (numCopy % 10))
    if (numCopy % 13 == 0)
      return 13
    -1
  }

  println("Case #1:")
  while (ct < 500) {
    val jamCoin = java.lang.Long.toBinaryString(lastNum)
    var flag = true
    val factors = new Array[Int](9)
    for (base <- 2 to 10 if flag) {
      val factor = getAFactor(jamCoin, base)
      if (factor == -1)
        flag = false
      else
        factors(base - 2) = factor
    }
    if (flag) {
      println(s"$jamCoin ${factors.mkString(" ")}")
      ct += 1
    }
    lastNum += 2
  }
}
