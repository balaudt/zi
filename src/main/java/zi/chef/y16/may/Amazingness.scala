package zi.chef.y16.may

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.Random

/**
  * Created by balaudt on 5/16/16.
  */
object Amazingness extends App {
  val ct = System.currentTimeMillis()
  val MAX = 1e6.toInt
  val running = Array.ofDim[Long](MAX + 1)
  val amazing = Array.ofDim[Int](MAX + 1)
  running(0) = 0
  val P = (1e9 + 7).toInt
  for (i <- 1 to MAX) {
    if (amazing(i) == 0) {
      var num = i
      var revNum = 0
      val buf = ArrayBuffer[Int]()
      val s = mutable.HashSet[Int]()
      var ans = 0
      while (num > 0) {
        val t = num % 10
        num /= 10
        revNum *= 10
        revNum += t
        var value = 0
        buf += t
        for (l <- buf.length - 1 to 0 by -1) {
          value ^= buf(l)
          if (!s.contains(value)) {
            ans += value
            s += value
          }
        }
      }
      running(i) = (running(i - 1) + ans) % P
      amazing(i) = ans
      amazing(revNum) = amazing(i)
    } else {
      running(i) = (running(i - 1) + amazing(i)) % P
    }
  }
//  println(System.currentTimeMillis() - ct)

  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val lr = lineIt.next().split(" ").map(_.toInt)
    val (l, r) = (lr(0), lr(1))
    if (l > MAX || r > MAX)
      throw new UnsupportedOperationException
    var ans = running(r) - running(l - 1)
    if (ans < 0)
      ans += P
    println(ans)
  }
}
