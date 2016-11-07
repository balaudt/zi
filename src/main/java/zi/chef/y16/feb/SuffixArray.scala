package zi.chef.y16.feb

import java.util
import java.util.Comparator

/**
  * Created by balaudt on 8/18/16.
  */
object SuffixArray extends App {
  suffixArray("mississippi".toCharArray)

  class Suffix(var sortIndex: Int, var nextSortIndex: Int, var suffixIndex: Int)

  def suffixArray(c: Array[Char]) = {
    val n = c.length
    var (log2n, len) = (0, n)
    while (len > 0) {
      len >>= 1
      log2n += 1
    }
    val p = Array.ofDim[Int](log2n + 1, n)
    val l = Array.ofDim[Suffix](n)
    (0 until n).foreach(i => l(i) = new Suffix(0, 0, 0))
    for (i <- c.indices) p(0)(i) = c(i) - 'a'
    var (st, cnt, flag) = (1, 1, true)
    val comp = new Comparator[Suffix] {
      override def compare(o1: Suffix, o2: Suffix): Int = {
        if (o1.sortIndex == o2.sortIndex)
        /*if (o1.nextSortIndex == o2.nextSortIndex)
          o2.suffixIndex - o1.suffixIndex
        else*/
          o1.nextSortIndex - o2.nextSortIndex
        else
          o1.sortIndex - o2.sortIndex
      }
    }

    val str = new String(c)
    while (flag) {
      for (i <- 0 until n) {
        l(i).sortIndex = p(st - 1)(i)
        l(i).nextSortIndex = if (i + cnt < n) p(st - 1)(i + cnt) else -1
        l(i).suffixIndex = i
      }
      util.Arrays.sort(l, comp)
      for (i <- 0 until n) p(st)(l(i).suffixIndex) = if (i > 0 && l(i).sortIndex == l(i - 1).sortIndex && l(i).nextSortIndex == l(i - 1).nextSortIndex) p(st)(l(i - 1).suffixIndex) else i
      cnt *= 2
      if (cnt >= n)
        flag = false
      println(s"---$st---")
      l.foreach(suffix => println((str.substring(suffix.suffixIndex), suffix.sortIndex, suffix.nextSortIndex, suffix.suffixIndex)))
      st += 1
    }

  }
}
