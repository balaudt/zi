package zi.chef.y16.may

import java.util
import scala.collection.mutable

/**
  * Created by balaudt on 5/15/16.
  */
object MainGcd extends App {
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

  val smallPrimes = Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
    79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
    181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283,
    293, 307, 311, 313).toSet
  val smallFactorials = Array(1, 2, 6, 24)
  val factorizationMemo = mutable.HashMap[Int, mutable.HashMap[Int, Int]]()
  val bcMemo = mutable.HashMap[(Int, Int), Int]()

  def factorize(num: Int, res: mutable.HashMap[Int, Int]): Unit = {
    if (factorizationMemo.contains(num)) {
      res ++= factorizationMemo.get(num).get
      return
    }
    var n = num
    val curRes = mutable.HashMap[Int, Int]()
    for (prime <- smallPrimes if n != 1 && !smallPrimes.contains(n)) {
      if (n % prime == 0) {
        var ct = 0
        while (n % prime == 0) {
          n /= prime
          ct += 1
        }
        curRes += prime -> ct
      }
    }
    if (n != 1)
      curRes += n -> 1
    factorizationMemo += num -> curRes
    res ++= curRes
  }

  def binonCoeff(n: Int, k: Int): Int = {
    if (bcMemo.contains((n, k)))
      return bcMemo.get((n, k)).get
    val numMap = mutable.HashMap[Int, Int]()
    val denMap = mutable.HashMap[Int, Int]()
    for (i <- 0 until k) {
      factorize(n - i, numMap)
      factorize(k - i, denMap)
    }
    denMap.foreach(x => {
      numMap += x._1 -> (numMap.get(x._1).get - x._2)
    })
    var res = 1l
    numMap.filter(_._2 > 0).foreach(x => {
      for (i <- 1 to x._2) {
        res *= x._1
        res %= P
      }
    })
    bcMemo += (n, k) -> res.toInt
    res.toInt
  }

  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val nm = lineIt.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    var ip = util.Arrays.binarySearch(mainPrimes, m)
    if (ip < 0) ip = -ip - 2
    val primes = mainPrimes.slice(0, ip + 1).toSet
    val composites = (2 to m).toSet.diff(primes)
    val primeBitSet = mutable.BitSet()
    for (prime <- 0 to primes.size) primeBitSet += prime

    val ps = primes.size + 1
    var ans = 0l
    ans += binonCoeff(n + ps - 1, ps - 1)
    for (j <- 1 to 4 if composites.nonEmpty && j <= m) {
      composites.subsets(j).foreach(comb => {
        var tempOr = factors(comb.head - 2).clone()
        var tempAnd = mutable.BitSet()
        for (comp <- comb.tail if tempAnd.isEmpty) {
          tempAnd = tempOr & factors(comp - 2)
          tempOr |= factors(comp - 2)
        }
        if (tempAnd.isEmpty) {
          if (j == m) {
            ans += 1
            ans %= P
          } else {
            val mutualPrimeCt = (tempOr ^ primeBitSet).size + 1
            var t = 1l
            t *= smallFactorials(j)
            t *= binonCoeff(mutualPrimeCt + n - 1, mutualPrimeCt + j - 1)
            t %= P
            ans += t
            ans %= P
          }
        }
      })
    }
    println(ans)
  }
}
