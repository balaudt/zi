package zi.chef.y16.snck.r1A

import scala.collection.mutable.ArrayBuffer

/**
  * Created by balaudt on 6/5/16.
  */
object LIS extends App {
  val m = Array(2, 3, 5, 7, 11, 13, 17)
  val mprod = m.scanLeft(1)(_ * _)
//  val msum = m.scanLeft(0)(_ + _)
  val c = Array.ofDim[Int](m.length, m.length)
  for (i <- m.indices) {
    for (j <- m.indices) {
      if (i != j) {
        c(i)(j) = BigInt(m(i)).modInverse(BigInt(m(j))).toInt
      }
    }
  }

  def mixedRadix(num: Int): ArrayBuffer[Int] = {
    val u = m.map(num % _)
    val v = ArrayBuffer(u(0))
    var (ans, flag) = (v(0), true)
    for (i <- 1 until m.length if (flag)) {
      var cur = ((u(i) - v(0)) * c(0)(i)) % m(i)
      for (j <- 1 until i) {
        cur = ((cur - v(j)) * c(j)(i)) % m(i)
      }
      if (cur < 0)
        cur += m(i)
      v += cur
      ans += mprod(i) * cur
      if (ans == num)
        flag = false
    }
    v
  }

  def genMiniArray(tk: Int, mult: Int, start: Int, lis: Int) = {
//    println((tk, mult, start, lis))
    val vi = m.take(tk) ++ Array(mult)
    var cur = start
    val out = ArrayBuffer[Int]()
    for (subLen <- vi) {
      out ++= (cur + subLen - 1) to cur by -1
      cur += subLen
    }
    out ++= cur to (cur + lis - vi.length - 1)
    (out, cur + lis - vi.length)
  }

  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val k = lineIt.next().toInt
    val v = mixedRadix(k).reverse
//    println(v)
    val lis = v.length
    var cur = 1
    var ans = ArrayBuffer[Int]()
    var tk = v.length - 1
    v.indices.foreach(j => {
      if (v(j) != 0) {
        val (out, next) = genMiniArray(tk, v(j), cur, lis)
        cur = next
        ans = out ++ ans
      }
      tk -= 1
    })
    println(ans.mkString(" "))
  }
}
