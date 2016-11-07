package zi.chef.y16.feb

/**
  * Created by balaudt on 8/8/16.
  */
object DP1 extends App {
  val n = 37
  val p = Array.ofDim[Int](n)
  p(0) = 1
  p(1) = 2
  p(2) = 4
  for (i <- 4 to n)
    p(i - 1) = p(i - 2) + p(i - 3) + p(i - 4)
  println(p(n - 1))
}
