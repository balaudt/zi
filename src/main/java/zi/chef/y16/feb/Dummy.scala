package zi.chef.y16.feb

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

/**
  * Created by balaudt on 2/13/16.
  */
object Dummy extends App {

  class Combination(n: Int, r: Int) {
    val index = new Array[Int](r)
    val result = new Array[Int](r)
    var hasNext = true
    for (i <- 0 until r) index(i) = i

    def next(): Array[Int] = {
      if (!hasNext)
        return null
      for (i <- 0 until r) result(i) = index(i)
      var nextInd = -1
      breakable {
        for (i <- r - 1 to 0 by -1) if (index(i) < n - r + i) {
          nextInd = i
          break
        }
      }
      if (nextInd >= 0) {
        index(nextInd) = index(nextInd) + 1
        for (i <- nextInd + 1 until r) index(i) = index(i - 1) + 1
      } else {
        hasNext = false
      }
      result
    }
  }

  def temp1 = {
    var tst = new Combination(5, 0)
    var ct = 0
    while (tst.hasNext) {
      println(tst.next().length)
      ct += 1
    }
    println(ct)
  }

  def temp2 = {
    val a = mutable.BitSet()
    a += 3
    a += 5
    val b = mutable.BitSet()
    for (i <- 0 to 25) b += i
    println(a ^ b)
  }

  def temp3 = {
    val P = (1e9 + 7).toInt
    val N = 1e5.toInt
    val factorials = Array.ofDim[Int](N + 1)
    val inverseFactorials = Array.ofDim[Int](N + 1)
    var fact = 1l
    val PBI = BigInt.apply(P)
    factorials(0) = 1
    inverseFactorials(0) = 1
    for (i <- 1 to N) {
      fact *= i
      fact %= P
      factorials(i) = fact.toInt
      inverseFactorials(i) = BigInt.apply(fact).modInverse(PBI).toInt
    }
    def f(p: Int, m: Int): Int = {
      var ans = 1l
      var pm = ((factorials(p).toLong * factorials(m)) % P).toLong
      for (i <- 1 to java.lang.Math.min(p, m)) {
        //pPi*mCi
        var t = pm * inverseFactorials(p - i)
        t %= P
        t *= inverseFactorials(m - i)
        t %= P
        t *= inverseFactorials(i)
        t %= P
        ans += t
        ans %= P
      }
      ans.toInt
    }
  }

  Array[Double](1.0,3.0).sum
  val s = new mutable.StringBuilder("AA");
  var i = 0;
  while (s.length > 0 && i < s.length) {
    val c = s.charAt(i)
    var j = i
    while (j < s.length && s.charAt(j) == c) j += 1
    if (j > i + 1) {
      s.delete(i, j)
      if (i > 0) i -= 1
    }
    else {
      i += 1
    }
  }
  println(if (s.length > 0) s.toString() else "-1")
}