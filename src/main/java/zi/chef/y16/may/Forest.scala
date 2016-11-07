package zi.chef.y16.may

import scala.collection.mutable
import scala.math.BigDecimal.RoundingMode

/**
  * Created by balaudt on 5/7/16.
  */
object Forest extends App {
//  val lineIt = io.Source.fromFile("/Users/balaudt/Temp/may/forest.in").getLines()
    val lineIt = io.Source.fromInputStream(System.in).getLines()
  val nwl = lineIt.next().split(" ")
  val (n, w, l) = (nwl(0).toInt, nwl(1).toLong, nwl(2).toDouble)

  case class Tree(ht: Int, r: Int, m: Long) extends Ordered[Tree] {
    override def compare(that: Tree): Int = that.m compare m
  }

  val queue = mutable.PriorityQueue[Tree]()
  for (i <- 0 until n) {
    val hr = lineIt.next().split(" ").map(_.toInt)
    val m = if (l > hr(0)) java.lang.Math.ceil((l - hr(0)) / hr(1)).toLong else 0
    queue += Tree(hr(0), hr(1), m)
  }

  var (htSum, rtSum) = (BigInt.apply(0), BigInt.apply(0))
  var (curSum, ans) = (BigInt.apply(0), 0l)
  while (curSum < w) {
    if (queue.isEmpty) {
      ans = (BigDecimal.apply(w - htSum) / BigDecimal.apply(rtSum)).setScale(0, RoundingMode.CEILING).toLong
//      ans = Math.ceil((w - htSum).doubleValue() / rtSum.doubleValue()).toLong
      curSum = w
    } else {
      if (rtSum == 0)
        ans = Long.MaxValue
      else
        ans = (BigDecimal.apply(w - htSum) / BigDecimal.apply(rtSum)).setScale(0, RoundingMode.CEILING).toLong

      val m = queue.head.m
      while (!queue.isEmpty && queue.head.m == m) {
        val tree = queue.dequeue()
        htSum += tree.ht
        rtSum += tree.r
      }
      curSum = htSum + rtSum * m
      ans = java.lang.Math.min(ans, m)
    }
  }
  println(ans)
//  println(1234567891234567899l.toDouble)
}
