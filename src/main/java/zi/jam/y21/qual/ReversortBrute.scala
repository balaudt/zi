package zi.jam.y21.qual

import org.paukov.combinatorics.{Factory, ICombinatoricsVector}

import scala.collection.JavaConverters._
import scala.collection.mutable

object ReversortBrute {
  def main(args: Array[String]): Unit = {
    val n = 5
    val gen = Factory.createPermutationGenerator(Factory.createVector((1 to n).map(Integer.valueOf).toArray))
    gen.asScala
      .map(_.getVector.asScala).toBuffer.sortWith(sortF)
      .map(x => (x.mkString("-"), reverseSort(x)))
      .groupBy(_._2).toSeq.sortBy(_._1)
      .foreach(println)
  }

  def sortF(a1: mutable.Buffer[Integer], a2: mutable.Buffer[Integer]): Boolean = {
    for (i <- a1.indices) {
      if (a1(i) != a2(i))
        return a1(i) < a2(i)
    }
    true
  }

  def reverseSort(v: mutable.Buffer[Integer]): Int = {
    var res = 0
    for (i <- 0 until v.size - 1) {
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
}
