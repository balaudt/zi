package zi.chef.y16.feb

import java.io.{FileReader, BufferedReader, InputStreamReader}

import scala.collection.immutable.IndexedSeq
import scala.collection.mutable
import scala.runtime
import scala.runtime.ScalaRunTime
import scala.util.control.Breaks._

/**
  * Created by balaudt on 2/12/16.
  */
object G extends App {

  class Combination(n: Int, r: Int) {
    val index = new Array[Int](r)
    val result = new Array[Int](r)
    var hasNext = true
    for (i <- 0 until r) index(i) = i

    def next(): Array[Int] = {
      if (!hasNext)
        return null
      for (i <- 0 until r) result(i) = index(i)
      var nextInd = -1
      breakable {
        for (i <- r - 1 to 0 by -1) if (index(i) < n - r + i) {
          nextInd = i
          break
        }
      }
      if (nextInd >= 0) {
        index(nextInd) = index(nextInd) + 1
        for (i <- nextInd + 1 until r) index(i) = index(i - 1) + 1
      } else {
        hasNext = false
      }
      result
    }
  }

  class PersonStat(var p: Int, var hrsLeftInDay: Int, var hrsLeft: Int, var lunchComplete: Boolean) {
    override def hashCode = (p, hrsLeftInDay, hrsLeft, lunchComplete).hashCode()

    override def equals(other: Any) = other match {
      case that: PersonStat => p == that.p && hrsLeftInDay == that.hrsLeftInDay && hrsLeft == that.hrsLeft && lunchComplete == that.lunchComplete
      case _ => false
    }

    override def toString = (p, hrsLeftInDay, hrsLeft, lunchComplete).toString()
  }

  //  val reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/G-sample.in"))
  val reader = new BufferedReader(new InputStreamReader(System.in))
  val MAX = 10
  val t = reader.readLine().toInt
  for (i <- 1 to t) {
    var in = reader.readLine().split(" ").map(_.toInt)
    in.foreach(x => if (x > MAX) throw new UnsupportedOperationException())
    val (p, d, h, n) = (in(0), in(1), in(2), in(3))
    val l = reader.readLine().split(" ").map(_.toInt)
    in = reader.readLine().split(" ").map(_.toInt)
    val (ltBeg, ltEnd) = (in(0) - 1, in(1) - 1)
    val r = new Array[Array[Int]](d)
    for (j <- 0 until d) {
      r(j) = reader.readLine().split(" ").map(_.toInt)
    }
    val runningR = r.flatten.sum

    val f = Array.ofDim[Array[Int]](p, d)
    val lPerDay = Array.ofDim[Int](p, d)
    var isFeasible = true
    for (j <- 0 until p) {
      for (k <- 0 until d) {
        f(j)(k) = reader.readLine().split(" ").map(_.toInt)
        val meetHrs = h - f(j)(k).sum
        //n-meetHrs<0 => input is wrong as it would mean p is attending >n meetings on dth day
        lPerDay(j)(k) = Array(f(j)(k).sum, n - meetHrs, l(j)).min
        if (lPerDay(j)(k) < 0) isFeasible = false
      }
    }


    //Trivial check
    val maxL = Math.min(l.sum, lPerDay.flatten.sum)
    if (!isFeasible || maxL < runningR)
      println("No")
    else {
      for (j <- 0 until d) {
        for (k <- 0 until h) {
          if ((for (m <- 0 until p) yield f(m)(j)(k)).sum < r(j)(k))
            isFeasible = false
        }
      }
      if (!isFeasible)
        println("No")
      else {
        val memo = mutable.HashSet[(Int, IndexedSeq[PersonStat])]()
        def schedule(hr: Int, stats: IndexedSeq[PersonStat]): Boolean = {
          if (memo.contains((hr, stats)))
            return false
          val day = hr / h
          val hrInDay = hr % h
          var avail: IndexedSeq[PersonStat] = stats.filter(x => f(x.p)(day)(hrInDay) == 1 && x.hrsLeftInDay > 0)
          if (hrInDay == ltEnd)
            avail = avail.filter(_.lunchComplete)
          if (r(day)(hrInDay) > avail.length) {
            memo.add((hr, stats))
            return false
          }
          //Should work when r(day)(hrInDay)=0 too
          val comb = new Combination(avail.length, r(day)(hrInDay))
          while (comb.hasNext) {
            val curComb = comb.next().map(x => avail(x).p)
            val nextStats = stats.map(x => new PersonStat(x.p, x.hrsLeftInDay, x.hrsLeft, x.lunchComplete))
            if (hrInDay >= ltBeg && hrInDay <= ltEnd) {
              nextStats.foreach(ns =>
                if (!ns.lunchComplete && f(ns.p)(day)(hrInDay) != 0 && !curComb.contains(ns.p)) ns.lunchComplete = true)
            }
            nextStats.foreach(ns => if (curComb.contains(ns.p)) {
              ns.hrsLeft -= 1
              ns.hrsLeftInDay -= 1
            })
            if (hrInDay == h - 1) {
              var allLunchComplete = true
              nextStats.foreach(x => if (!x.lunchComplete) allLunchComplete = false)
              if (allLunchComplete) {
                if (day == d - 1)
                  return true
                nextStats.foreach(ns => {
                  ns.lunchComplete = false
                  ns.hrsLeftInDay = Math.min(lPerDay(ns.p)(day + 1), ns.hrsLeft)
                })
                if (schedule(hr + 1, nextStats))
                  return true
              }
            } else if (schedule(hr + 1, nextStats))
              return true
          }
          memo.add((hr, stats))
          false
        }
        val initialStats = for (j <- 0 until p) yield new PersonStat(j, lPerDay(j)(0), l(j), false)
        isFeasible = schedule(0, initialStats)
        println(if (isFeasible) "Yes" else "No")

      }
    }
  }
  reader.close()
}