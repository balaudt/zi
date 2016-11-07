package zi.hrank.algo.dyna

import java.util

import scala.collection.mutable
import scala.io.Source
import scala.collection.Searching._

/**
  * Created by balaudt on 3/6/16.
  */
object CoinChange extends App {

  val reader = Source.fromInputStream(System.in).getLines()
  val line = reader.next().split(" ").map(_.toInt)
  val (n, m) = (line(0), line(1))
  val c = reader.next().split(" ").map(_.toInt).sorted

  val lkup = mutable.HashMap[Int, Int]()

  println(getWays(n))

  def getWays(n: Int): Int = {
    lkup.get(n) match {
      case Some(x) => return x
      case _ => ;
    }
    if (n <= 0)
      return 0

    var (i, ans) = (0, 0)
    if (util.Arrays.binarySearch(c, n) >= 0) {
      ans = 1
    }
    while (i < m && c(i) < n) {
      ans += getWays(n - c(i))
      i += 1
    }
    lkup.put(n, ans)
    ans
  }
}
