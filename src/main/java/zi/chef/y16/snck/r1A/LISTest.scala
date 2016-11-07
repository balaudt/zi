package zi.chef.y16.snck.r1A

import scala.collection.mutable.ArrayBuffer


/**
  * Created by balaudt on 6/5/16.
  */
object LISTest extends App {
  val ms1 = Array(Array(2, 3, 5, 7, 11, 13, 17),
    Array(3, 5, 7, 11, 13, 17, 19),
    Array(2, 5, 7, 11, 13, 17, 19),
    Array(2, 3, 7, 11, 13, 17, 19),
    Array(2, 3, 5, 11, 13, 17, 19),
    Array(2, 3, 5, 7, 13, 17, 19),
    Array(2, 3, 5, 7, 11, 17, 19),
    Array(2, 3, 5, 7, 11, 13, 19))
  val ms = Array(Array(2, 3, 5, 7, 11, 13, 17).reverse,
    Array(2, 3, 5, 7, 11, 13, 17))
  val mprods = ms.map(m => m.scanLeft(1)(_ * _))
  val cs = Array.ofDim[Int](2, 7, 7)
  for (k <- 0 until 2)
    for (i <- 0 until 7)
      for (j <- 0 until 7)
        if (i != j) {
          cs(k)(i)(j) = BigInt(ms(k)(i)).modInverse(BigInt(ms(k)(j))).toInt
        }

  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (k <- 1 to 1e5.toInt) {
    var mainFlag = true
    for (z <- 0 until 2 if (mainFlag)) {
      val m = ms(z)
      val c = cs(z)
      val mprod = mprods(z)

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
        //        println((tk, mult, start, lis))
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
      val v = mixedRadix(k).reverse
      //      println(v)
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
      if (ans.length <= 100) {
        //        println(ans.mkString(" "))
        mainFlag = false
      }
    }
    if (mainFlag) {
      println(k)
      System.exit(0)
    }
  }
}
