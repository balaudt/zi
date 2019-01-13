package zi.fb.cup17.qual

import java.util.PriorityQueue

import scala.io.Source

/**
  * Created by balamurugan on 1/8/17.
  */
object LazyLoading extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  val carry = 50.0
  for (i <- 1 to t) {
    val n = lines.next().toInt
    val (minQ, maxQ) = (new PriorityQueue[Int](), new PriorityQueue[Int](Ordering[Int].reverse))
    for (_ <- 1 to n) {
      val w = lines.next().toInt
      maxQ.add(w)
      minQ.add(w)
    }
    var trip = 0
    while (!maxQ.isEmpty) {
      trip += 1
      val curMax = maxQ.poll()
      minQ.remove(curMax)
      if (curMax < carry) {
        val itCt = Math.ceil(carry / curMax).toInt
        for (_ <- 1 to itCt if !maxQ.isEmpty) maxQ.remove(minQ.poll())
      }
    }
    println(s"Case #$i: $trip")
  }

}
