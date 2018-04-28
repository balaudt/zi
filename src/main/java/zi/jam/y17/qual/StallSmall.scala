package zi.jam.y17.qual

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * Created by balamurugan on 4/7/17.
  */
object StallSmall extends App {
  def op(cur: Array[Int], lr: Int, b: ArrayBuffer[(Int, Int)]) = {
    if (cur(0) == lr) cur(1) += 1
    else {
      b += Tuple2(cur(0), cur(1))
      cur(0) = lr
      cur(1) = 1
    }
  }

  val n = Random.nextInt(350) + 350
  println(n)
  val maxs = ArrayBuffer[(Int, Int)]()
  val mins = ArrayBuffer[(Int, Int)]()
  var (minCur, maxCur, ct) = (Array(-1, -1), Array(-1, -1), 0)
  val vacants = mutable.HashSet[Int]() ++ (1 to n)
  val filled = new util.TreeSet[Int]()
  filled.add(0)
  filled.add(n + 1)
  for (k <- 1 to n if maxCur(0) != 0 || maxCur(1) != 0) {
    var next = (-1, -1, -1)
    vacants.foreach(vacant => {
      val currentLr = Array(vacant - filled.lower(vacant) - 1, filled.higher(vacant) - vacant - 1)
      val min = currentLr.min
      val max = currentLr.max
      if (min > next._2 || (min == next._2 && (max > next._3 || (max == next._3 && vacant < next._1)))) {
        next = (vacant, min, max)
      }
    })
    filled.add(next._1)
    vacants -= next._1
    op(minCur, next._2, mins)
    op(maxCur, next._3, maxs)
  }
  println(mins.tail)
  println(maxs.tail)
  println(n - mins.tail.map(_._2).sum)
  println(n - maxs.tail.map(_._2).sum)
}
