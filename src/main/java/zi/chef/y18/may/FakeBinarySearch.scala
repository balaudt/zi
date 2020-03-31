package zi.chef.y18.may

import java.util

import scala.io.Source

/**
  * @author balamurugan
  */
object FakeBinarySearch extends App {
  val lineIt = Source.fromFile("/Users/balamurugan/Temp/fakebinary.in").getLines()
  //    val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().trim.toInt
  for (i <- 0 until t) {
    val nq = lineIt.next().trim.split(" ").map(_.toInt)
    val (n, q) = (nq(0), nq(1))
    val a = lineIt.next().trim.split(" ").map(_.toInt)
    val aSorted = a.sorted
    val ainv = a.zipWithIndex.toMap
    val b = 0 until n
    for (j <- 0 until q) {
      val x = lineIt.next().toInt
      val xpos = ainv(x)
      var (low, high, mid, found) = (0, n - 1, -1, false)
      val xSortPos = util.Arrays.binarySearch(aSorted, x)
      var (lowIndGreatVal, greatIndLowVal, lowerCt, greaterCt) = (0, 0, xSortPos, n - xSortPos - 1)
      while (!found) {
        mid = (low + high) / 2
        if (mid == xpos) found = true
        else if (mid < xpos) {
          low = mid + 1
          if (a(mid) > x) lowIndGreatVal += 1
          else lowerCt -= 1
        }
        else {
          high = mid - 1
          if (a(mid) < x) greatIndLowVal += 1
          else greaterCt -= 1
        }
      }
      if (lowIndGreatVal == greatIndLowVal) println(lowIndGreatVal)
      else if (lowIndGreatVal < greatIndLowVal) {
        if (greaterCt >= (greatIndLowVal - lowIndGreatVal)) println(greatIndLowVal)
        else println(-1)
      } else {
        if (lowerCt >= (lowIndGreatVal - greatIndLowVal)) println(lowIndGreatVal)
        else println(-1)
      }
    }
  }
}

