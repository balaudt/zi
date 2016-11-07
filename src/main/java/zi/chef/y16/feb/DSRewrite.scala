package zi.chef.y16.feb

import java.io.{InputStreamReader, FileReader, BufferedReader}

import scala.collection
import scala.collection.mutable.{HashMap, Set}
import scala.collection.mutable
import java.util
import scala.collection.JavaConverters._
import scala.util.control.Breaks
import scala.util.control.Breaks._

/**
  * Created by balaudt on 2/7/16.
  */
object DSRewrite extends App {

//  var reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/D-sample.in"))
    var reader = new BufferedReader(new InputStreamReader(System.in))
  val ct = System.currentTimeMillis()
  var t = reader.readLine().toInt
  for (i <- 1 to t) {
    val nm = reader.readLine().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val in = new Array[Array[Int]](n)
    for (j <- 0 until n) {
      in(j) = reader.readLine().split(" ").map(_.toInt)
    }
    def mergeFrequencies(counter: Counter, map: mutable.HashMap[Int, Int], maxs: mutable.HashMap[Int, Set[Int]], ind: Int) = {
      counter.foreach(x => map.get(x._1) match {
        case None => {
          map += (x._1 -> x._2)
          maxs += (x._1 -> Set(ind))
        }
        case Some(y) => {
          map += (x._1 -> Math.max(x._2, y))
          if (x._2 > y)
            maxs.put(x._1, Set(ind))
          else
            maxs.get(x._1).get.add(ind)
        }
      });
    }
    val rows = HashMap[Int, Int]()
    val columns = HashMap[Int, Int]()
    val rowNumMaxs = HashMap[Int, Set[Int]]()
    val colNumMaxs = HashMap[Int, Set[Int]]()
    for (j <- 0 until n) {
      val row = new Counter()
      in(j).foreach(row.increment(_))
      mergeFrequencies(row, rows, rowNumMaxs, j)
    }
    for (j <- 0 until m) {
      val column = new Counter()
      for (k <- 0 until n) {
        column.increment(in(k)(j))
      }
      mergeFrequencies(column, columns, colNumMaxs, j)
    }
    def isInter(num: Int): Boolean = {
      var out = true
      breakable {
        rowNumMaxs.get(num).get.foreach(r => {
          colNumMaxs.get(num).get.foreach(c => {
            if (in(r)(c) != num) {
              out = false
              break
            }
          })
        })
      }
      out
    }

    def reverseIndex(row: HashMap[Int, Int]): mutable.Map[Int, mutable.Set[Int]] = {
      val reversed: Map[Int, mutable.Set[Int]] = row.groupBy(_._2).mapValues(Set[Int]() ++= _.keys)
      var reversedSorted: mutable.Map[Int, mutable.Set[Int]] = new util.TreeMap[Int, Set[Int]](Ordering.Int.reversed()).asScala
      reversedSorted ++= reversed
      reversedSorted
    }
    val rowRev = reverseIndex(rows)
    val colRev = reverseIndex(columns)
    //    val rowRun = rowRev.keys.scanRight(0)(_ + _)
    //    val colRun = colRev.keys.scanRight(0)(_ + _)

    val keyByNum = mutable.HashMap[Int, NumRes]()
    val keyByFreq = new util.TreeMap[Int, Set[Int]](Ordering.Int.reversed()).asScala
    keyByFreq.put(0, Set(0))
    var ans = 0
    def insertNumber(entry: (Int, Set[Int]), isRow: Boolean) = {
      entry._2.foreach(x => {
        keyByNum.get(x) match {
          case None => {
            keyByNum.put(x, NumRes(isRow, !isRow, entry._1))
            keyByFreq.get(entry._1) match {
              case None => keyByFreq.put(entry._1, mutable.Set(x))
              case Some(y) => y.add(x)
            }
          }
          case Some(numRes) => {
            val prevCount = numRes.count
            var count = entry._1 + prevCount
            if (isInter(x))
              count -= 1
            if (count > ans)
              ans = count
            val set = keyByFreq.get(prevCount).get
            set.remove(x)
            if (set.isEmpty)
              keyByFreq.remove(prevCount)
          }
        }
      })
    }

    var (rowIt, colIt) = (rowRev.iterator, colRev.iterator)
    breakable {
      while (true) {
        if (!rowIt.hasNext && !colIt.hasNext)
          break
        if (rowIt.hasNext) {
          val tuple: (Int, mutable.Set[Int]) = rowIt.next()
          if (tuple._1 + keyByFreq.keys.head < ans)
            rowIt = collection.Iterator.empty
          else
            insertNumber(tuple, true)
        }
        if (colIt.hasNext) {
          val tuple: (Int, mutable.Set[Int]) = colIt.next()
          if (tuple._1 + keyByFreq.keys.head < ans)
            colIt = collection.Iterator.empty
          else
            insertNumber(tuple, false)
        }
      }
    }
    println(ans)
  }

  //    println(System.currentTimeMillis() - ct)

  class Counter extends HashMap[Int, Int] {
    def increment(k: Int): Unit = {
      val v: Option[Int] = get(k)
      v match {
        case None => this += (k -> 1)
        case Some(value) => this += (k -> (value + 1))
      }
    }
  }

  case class NumRes(rowSet: Boolean, colSet: Boolean, count: Int)

}
