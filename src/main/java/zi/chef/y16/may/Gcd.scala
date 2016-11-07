package zi.chef.y16.may

import java.util

import org.apache.commons.math3.util.ArithmeticUtils
import org.paukov.combinatorics.Factory

import scala.collection.immutable.HashSet
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by balaudt on 5/14/16.
  */
object Gcd extends App {
  def prim = {
    val primField = Class.forName("org.apache.commons.math3.primes.SmallPrimes").getField("PRIMES")
    primField.setAccessible(true)
    var ct = 0
    for (prime <- primField.get(null).asInstanceOf[Array[Int]] if (prime < 100)) ct += 1
    println(ct)

    var cur = ArrayBuffer[Array[Int]]()
    (2 to 100).combinations(2).foreach(x => if (ArithmeticUtils.gcd(x(0), x(1)) == 1) cur += Array(x(0), x(1)))
    println(cur.size)
    for (k <- 3 to 10) {
      val curSize = cur(0).size
      val next = ArrayBuffer[Array[Int]]()
      for (i <- 2 to 100) {
        for (mut <- cur) {
          var flag = true
          for (num <- mut if (flag))
            if (num % i != 0 && i % num != 0) flag = false
          if (flag) {
            val newNext = Array.ofDim[Int](curSize + 1)
            mut.copyToArray(newNext)
            newNext(curSize) = i
            next += newNext
          }
        }
      }
      println(next.size)
      cur = next
    }
  }

  val primes = Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
    79, 83, 89, 97)
  val primeSet = primes.toSet
  val composites = (2 to 100).toSet.diff(primeSet)
  val factors = Array.ofDim[mutable.BitSet](99)
  for (i <- 2 to 100) {
    var num = i
    val factor = mutable.BitSet()
    for (prime <- 0 until primes.length if (num > 1)) {
      if (num % primes(prime) == 0) {
        factor += prime
        while (num % primes(prime) == 0) num /= primes(prime)
      }
    }
    factors(i - 2) = factor
  }

  for (k <- 2 to 4) {
    var ct = 0
    for (comb <- composites.subsets(k)) {
      var tempOr = factors(comb.head - 2).clone()
      var tempAnd = mutable.BitSet()
      for (comp <- comb.tail if (tempAnd.size == 0)) {
        tempAnd = tempOr & factors(comp - 2)
        tempOr |= factors(comp - 2)
      }
      if (tempAnd.size == 0)
        ct += 1
    }
    println(ct)
  }
}
