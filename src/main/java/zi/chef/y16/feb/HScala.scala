package zi.chef.y16.feb

import scala.collection.JavaConverters._
import scala.util.control.Breaks
import scala.util.control.Breaks._

/**
  * Created by balaudt on 2/14/16.
  */
object HScala extends App {
  val P = 998244353
  val MAX_N = 1e18.toLong

  def powerSum(n: BigInt, pow: Int): BigInt = {
    var ans: BigInt = 0
    pow match {
      case 1 => ans = n * (n + 1) / 2
      case 2 => ans = (2 * n * n * n + 3 * n * n + n) / 6
      case 3 => {
        val nsq = n * n
        ans = (nsq * nsq + 2 * nsq * n + nsq) / 4
      }
      case 4 => {
        val nsq = n * n
        ans = (6 * nsq * nsq * n + 15 * nsq * nsq + 10 * nsq * n - n) / 30
      }
      case 6 => {
        val nsq = n * n
        val ncub = nsq * n
        ans = (6 * ncub * ncub * n + 21 * ncub * ncub + 21 * ncub * nsq - 7 * ncub + n) / 42
      }
    }
    ans - 1

  }

  def nthRoot(n: Int, a: Double): Double = {
    def loop(x0: Double): Double = {
      val x1 = (1.0d / n * ((n - 1) * x0 + a / math.pow(x0, n - 1)))
      if (x0 <= x1) x0
      else loop(x1)
    }

    return loop(a / 2)
  }

  var base=2
  var perNumToLowRoot=Map[Int,Int]()
  breakable{

  }
}
