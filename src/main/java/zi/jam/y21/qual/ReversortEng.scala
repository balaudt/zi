package zi.jam.y21.qual

import scala.collection.mutable
import scala.io.Source

object ReversortEng {
  private val out = mutable.Map[(Int, Int), Array[Integer]]()

  def genPos(n: Int): Unit = {
    backtrack((1 to n).toArray, Array.ofDim[Boolean](n), mutable.Buffer[Integer]())

    def backtrack(in: Array[Int], used: Array[Boolean], gen: mutable.Buffer[Integer]): Unit = {
      if (gen.size == n) {
        val cost = reverseSort(gen.toArray)
        if (!out.contains((n, cost)))
          out.put((n, cost), gen.toArray)
        return
      }
      for (i <- in.indices) {
        if (!used(i)) {
          used(i) = true
          gen += in(i)
          backtrack(in, used, gen)
          used(i) = false
          gen -= in(i)
        }
      }
    }
  }

  def reverseSort(v: Array[Integer]): Int = {
    var res = 0
    for (i <- 0 until v.length - 1) {
      val ind = v.indexOf(i + 1)
      for (j <- 0 to (ind - i) / 2) {
        val t = v(i + j)
        v(i + j) = v(ind - j)
        v(ind - j) = t
      }
      res += ind - i + 1
    }
    res
  }

  def main(args: Array[String]): Unit = {
    for(i <- 2 to 7)
      genPos(i)
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val nc = lines.next().split(" ").map(_.toInt)
      val (n, c) = (nc(0), nc(1))
      if(n > 7)
        throw new UnsupportedOperationException()
      val res = out.get((n, c))
      if(res.isEmpty)
        println(s"Case #$i: IMPOSSIBLE")
      else {
        val out = res.get.mkString(" ")
        println(s"Case #$i: $out")
      }
    }
  }
}
