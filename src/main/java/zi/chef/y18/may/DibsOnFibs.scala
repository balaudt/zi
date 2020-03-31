package zi.chef.y18.may

import scala.io.Source

/**
  * @author balamurugan
  */
object DibsOnFibs extends App {
  val p = 1e9.toInt + 7
  val fib = Array.ofDim[Long](1e5.toInt)
  fib(0) = 1
  fib(1) = 1
  for (i <- 2 until 1e5.toInt) {
    fib(i) = fib(i - 1) + fib(i - 2)
    fib(i) %= p
  }
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  var temp = 0L

  for (i <- 0 until t) {
    val mn = lineIt.next().split(" ").map(_.toInt)
    val (m, n) = (mn(0), Integer.max(mn(1), 2))
    val a = lineIt.next().split(" ").map(_.toLong)
    val b = lineIt.next().split(" ").map(_.toLong)
    var ans = 0L
    if (n == 1) {
      for (j <- 0 until m) {
        ans += m * a(j)
        ans %= p
      }
    } else if (n == 2) {
      for (j <- 0 until m) {
        ans += m * b(j)
        ans %= p
      }
    } else {
      for (j <- 0 until m) {
        temp = fib(n - 3) * a(j)
        temp %= p
        temp *= m
        temp %= p
        ans += temp
        ans %= p

        temp = fib(n - 2) * b(j)
        temp %= p
        temp *= m
        temp %= p
        ans += temp
        ans %= p
      }
    }
    println(ans)
  }
}
