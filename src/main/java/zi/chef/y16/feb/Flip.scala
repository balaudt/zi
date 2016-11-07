package zi.chef.y16.feb

import scala.io.Source

/**
  * Created by balaudt on 8/28/16.
  */
object Flip extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val q = lines.next().toInt
  for (i <- 1 to q) {
    val n = 2 * lines.next().toInt
    val a = Array.ofDim[Array[Int]](n)
    for (j <- 0 until n)
      a(j) = lines.next().split(" ").map(_.toInt)
    var maxSum = 0
    for (j <- 0 until n / 2) {
      for (k <- 0 until n / 2) {
        maxSum += Array(a(j)(k), a(n - 1 - j)(n - 1 - k), a(n - j - 1)(k), a(j)(n - k - 1)).max
      }
    }
    println(maxSum)
  }
}
