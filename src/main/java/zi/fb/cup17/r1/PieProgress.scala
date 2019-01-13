package zi.fb.cup17.r1

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 1/14/17.
  */
object PieProgress extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nm = lines.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val c = (0 until n).map(_ => lines.next().split(" ").map(_.toInt).toSeq.sorted)
    val memo = mutable.HashMap[(Int, Int), Int]()
    println(s"Case #$i: ${cost(0, 0)}")

    def cost(day: Int, piesBought: Int): Int = {
      if (piesBought == n) return 0
      if (memo.contains((day, piesBought))) return memo((day, piesBought))
      val maxPiesToday = Math.min(n - piesBought, m)
      var minRemCost = Int.MaxValue
      for (j <- 1 to maxPiesToday) {
        val currentCost = c(day).take(j).sum + j * j + cost(day + 1, piesBought + j)
        if (currentCost < minRemCost) minRemCost = currentCost
      }
      memo += (day, piesBought) -> minRemCost
      minRemCost
    }
  }
}
