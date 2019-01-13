package zi.chef.y17.jan.longg

import java.util

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 1/12/17.
  */
object Digits extends App {

  val ct = System.currentTimeMillis()

  private def init() = {
    val smallPrimes = Array(2,
      3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
      79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
      181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283,
      293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419,
      421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547,
      557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661,
      673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811,
      821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947,
      953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087,
      1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229,
      1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381,
      1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511, 1523,
      1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657, 1663,
      1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811, 1823,
      1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889, 1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987, 1993,
      1997, 1999, 2003, 2011, 2017, 2027, 2029, 2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129, 2131,
      2137, 2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287, 2293,
      2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423, 2437,
      2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531, 2539, 2543, 2549, 2551, 2557, 2579, 2591, 2593, 2609, 2617, 2621,
      2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687, 2689, 2693, 2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741, 2749,
      2753, 2767, 2777, 2789, 2791, 2797, 2801, 2803, 2819, 2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903, 2909,
      2917, 2927, 2939, 2953, 2957, 2963, 2969, 2971, 2999, 3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079, 3083,
      3089, 3109, 3119, 3121, 3137)
    val pUndSq1e5 = util.Arrays.binarySearch(smallPrimes, 313)
    val primeBuffer = smallPrimes.toBuffer
    for (num <- 3139 until 1e5.toInt) {
      var isPrime = true
      for (i <- 0 to pUndSq1e5 if isPrime)
        if (num % smallPrimes(i) == 0) isPrime = false
      if (isPrime) primeBuffer += num
    }
    primeBuffer.toArray
  }

  val primes = init()
  val factorMemo = mutable.HashMap[Long, mutable.HashMap[Long, Int]]()
  val zeroFactor = mutable.HashMap[Long, Int](0l -> 1)
  val oneFactor = mutable.HashMap[Long, Int](1l -> 1)
  //  println(primes.length)
  val mp = Array(3, 7, 31, 97, 313, 997, 3137, 9973, 31607, 99991).map(x => util.Arrays.binarySearch(primes, x))
  //  println(mp.length)

  private def factorise(num: Long): mutable.Map[Long, Int] = {
    if (num == 1) return oneFactor
    else if (num == 0) return zeroFactor
    else if (factorMemo.contains(num)) return factorMemo(num)
    val noDigits = num.toString.length
    val smallPrimes = primes.take(mp(noDigits - 1))
    var in = num
    val out = mutable.HashMap[Long, Int]()
    for (prime <- smallPrimes if in > 1) {
      if (in % prime == 0) {
        var ct = 0
        while (in % prime == 0) {
          ct += 1
          in /= prime
        }
        out += prime.toLong -> ct
      }
    }
    if (in > 1) out += in -> 1
    factorMemo += num -> out
    out
  }

  private def factorize(str: String, m: Int) = {
    val digits = str.toCharArray.map(_ - '0').map(_.toLong)
    val out = mutable.HashMap[(Int, Int), mutable.Map[Long, Int]]()
    for (i <- digits.indices) {
      var (j, num) = (i, 0l)
      while (j - i <= m && j < digits.length) {
        num *= 10
        num += digits(j)
        out += (i, j) -> factorise(num)
        j += 1
      }
    }
    out
  }

  private def gcd(a: mutable.Map[Long, Int], b: mutable.Map[Long, Int]): mutable.Map[Long, Int] = {
    if (a.equals(zeroFactor)) return b
    else if (b.equals(zeroFactor)) return a
    else if (a.equals(oneFactor) || b.equals(oneFactor)) return oneFactor
    val out = mutable.HashMap[Long, Int]()
    for (af <- a) if (b.contains(af._1)) out += af._1 -> Math.min(b(af._1), af._2)
    if (out.isEmpty) return oneFactor
    out
  }

  private def compare(a: mutable.Map[Long, Int], b: mutable.Map[Long, Int]): Boolean = {
    if (b == null) return true
    var (al, bl) = (1l, 1l)
    for (af <- a) al *= Math.pow(af._1, af._2).toLong
    for (bf <- b) bl *= Math.pow(bf._1, bf._2).toLong
    bl.compareTo(al) < 1
  }

  //  println(factorize("32675120", 3))
  //  println(System.currentTimeMillis() - ct)

  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (_ <- 1 to t) {
    val n = lines.next().toInt
    val s = lines.next()
    val mxy = lines.next().split(" ").map(_.toInt)
    val (m, x, y) = (mxy(0), mxy(1), mxy(2))
    val in = factorize(s, m)

    val memo = mutable.HashMap[(Int, Int, Int), mutable.Map[Long, Int]]()

    def f(st: Int, x: Int, y: Int): mutable.Map[Long, Int] = {
      if (memo.contains((st, x, y))) return memo((st, x, y))
      if (st >= n && x == 0) return zeroFactor
      if (y == 0) return in((st, s.length - 1))
      val minChars = Math.max(n - st - y * m, 1)
      val maxChars = Math.min(m, n - st - x)
      var maxGcd: mutable.Map[Long, Int] = null
      for (chars <- minChars to maxChars) {
        val next = f(st + chars, Math.max(x - 1, 0), y - 1)
        if (next != null) {
          val cur = in((st, st + chars - 1))
          val div = gcd(cur, next)
          if (compare(div, maxGcd)) maxGcd = div
        }
      }
      memo += (st, x, y) -> maxGcd
      maxGcd
    }

    val out = f(0, x, y)
    var al = 1l
    for (af <- out) al *= Math.pow(af._1, af._2).toLong
    println(al)
  }
}
