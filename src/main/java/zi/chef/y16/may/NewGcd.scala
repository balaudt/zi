package zi.chef.y16.may

import java.io.FileInputStream

import scala.collection.mutable
import scala.io
import java.util

/**
  * Created by balaudt on 5/15/16.
  */
object NewGcd extends App {
//  val ct = System.currentTimeMillis()
  val mainPrimes = Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
    79, 83, 89, 97)
  val factors = Array.ofDim[mutable.BitSet](99)
  for (i <- 2 to 100) {
    var num = i
    val factor = mutable.BitSet()
    for (prime <- mainPrimes.indices if num > 1) {
      if (num % mainPrimes(prime) == 0) {
        factor += prime
        while (num % mainPrimes(prime) == 0) num /= mainPrimes(prime)
      }
    }
    factors(i - 2) = factor
  }
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
  val smallFactorials = Array(1, 1, 2, 6, 24)

  val fMemo = mutable.HashMap[(Int, Int), Int]()

  def f(p: Int, m: Int): Int = {
    if (fMemo.contains((p, m)))
      return fMemo.get((p, m)).get
    var ans = 1l
    val pm = (factorials(p).toLong * factorials(m)) % P
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
    fMemo += (p, m) -> ans.toInt
    ans.toInt
  }

  val masterCompositeSet = mutable.HashSet[(Set[Int], mutable.BitSet)]()
  val mainComposites = (2 to 100).toSet.diff(mainPrimes.toSet)
  for (j <- 1 to 4) {
    mainComposites.subsets(j).foreach(comb => {
      var tempOr = factors(comb.head - 2).clone()
      var tempAnd = mutable.BitSet()
      for (comp <- comb.tail if tempAnd.isEmpty) {
        tempAnd = tempOr & factors(comp - 2)
        tempOr |= factors(comp - 2)
      }
      if (tempAnd.isEmpty) {
        masterCompositeSet += Tuple2(comb, tempOr)
      }
    })
  }

//  System.setIn(new FileInputStream("/Users/balaudt/Temp/may/gcd.in"))
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val nm = lineIt.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    var ip = util.Arrays.binarySearch(mainPrimes, m)
    if (ip < 0) ip = -ip - 2
    val primes = mainPrimes.slice(0, ip + 1).toSet
    val primeBitSet = mutable.BitSet()
    for (prime <- 0 until primes.size) primeBitSet += prime

    val ps = primes.size
    var ans = f(ps, n).toLong
    def curFilter(x: (Set[Int], mutable.BitSet)): Boolean = {
      if (x._1.size > n)
        return false
      var flag = true
      for (num <- x._1) if (num > m) flag = false
      flag
    }
    masterCompositeSet.filter(curFilter).foreach(x => {
      val j = x._1.size
      val tempOr = x._2
      if (j == m) {
        ans += smallFactorials(j)
        ans %= P
      } else {
        //mutualPrimeCt
        val mpc = (tempOr ^ primeBitSet).size
        var t = smallFactorials(j).toLong
        t *= f(mpc, n - j)
        t %= P
        t *= factorials(n)
        t %= P
        t *= inverseFactorials(j)
        t %= P
        t *= inverseFactorials(n - j)
        t %= P
        ans += t
        ans %= P
      }
    })
    println(ans)
  }
//  println(System.currentTimeMillis() - ct)
}
