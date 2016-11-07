package zi.jam.y16.qual

import scala.collection.mutable._

/**
  * Created by balaudt on 4/8/16.
  */
object CoinJam extends App {
  var (ct, lastNum) = (0, (1 << 15) + 1)
  val primeField = Class.forName("org.apache.commons.math3.primes.SmallPrimes").getField("PRIMES")
  primeField.setAccessible(true)
  var smallPrimes = primeField.get(null).asInstanceOf[Array[Int]]
  val primes = ArrayBuffer[Int]()
  primes ++= smallPrimes
  //2642246 - smallest integer greater than cube root of Long.MAX
  for (num <- smallPrimes(smallPrimes.length - 1) + 2 to 2642246 by 2) {
    var isPrime = true
    for (smallPrime <- smallPrimes if isPrime)
      if (num % smallPrime == 0)
        isPrime = false
    if (isPrime)
      primes += num
  }

  def getAFactor(num: Long): Int = {
    for (prime <- primes)
      if (num == prime)
        return -1
      else if (num % prime == 0)
        return prime
    -1
  }

  val factors = new Array[Int](9)
  println("Case #1:")
  while (ct < 50) {
    val jamCoin = java.lang.Long.toBinaryString(lastNum)
    var flag = true
    try {
      //2 is always a factor if base>2 and base%2==0
      for (base <- 2 to 10 if flag) {
        val factor = getAFactor(java.lang.Long.parseLong(jamCoin, base))
        if (factor == -1)
          flag = false
        else
          factors(base - 2) = factor
      }
    } catch {
      case e: NumberFormatException => flag = false
    }
    if (flag) {
      println(s"$jamCoin ${factors.mkString(" ")}")
      ct += 1
    }
    lastNum += 2
  }
}


