package zi.chef.y16.feb

import java.io.{InputStreamReader, FileReader, BufferedReader}
import java.util
import java.util.{Comparator, Collections}


import scala.collection
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.{HashMap, Set}
import scala.util.control.Breaks._

object DS extends App {
  var reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/D-gen.in"))
  val ct = System.currentTimeMillis()
  //  var reader = new BufferedReader(new InputStreamReader(System.in))
  var t = reader.readLine().toInt
  for (i <- 1 to 50) {
    var nm = reader.readLine().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    if (n > 100 || m > 100)
      throw new Exception("Sub task #3 optimization yet to be implemented")
    var rows = HashMap[Int, Counter]()
    var columns = HashMap[Int, Counter]()
    val in = new Array[Array[Int]](n)
    for (j <- 0 to n - 1) {
      in(j) = reader.readLine().split(" ").map(_.toInt)
      rows += (j -> new Counter())
      for (k <- 0 to m - 1) {
        if (j == 0)
          columns += (k -> new Counter())
        rows(j).increment(in(j)(k))
        columns(k).increment(in(j)(k))
      }
    }
    def reverseIndex(row: Counter): mutable.Map[Int, mutable.Set[Int]] = {
      val reversed: Map[Int, mutable.Set[Int]] = row.groupBy(_._2).mapValues(Set[Int]() ++= _.keys)
      var reversedSorted: mutable.Map[Int, mutable.Set[Int]] = new util.TreeMap[Int, Set[Int]](Ordering.Int.reversed()).asScala
      reversedSorted ++= reversed
      reversedSorted
    }
    val rowRev: collection.Map[Int, mutable.Map[Int, mutable.Set[Int]]] = rows.mapValues(reverseIndex)
    val colRev = columns.mapValues(reverseIndex)
    val rowRun: collection.Map[Int, Iterable[Int]] = rowRev.mapValues(frequencyMap => frequencyMap.keys.scanRight(0)(_ + _))
    val colRun = colRev.mapValues(frequencyMap => frequencyMap.keys.scanRight(0)(_ + _))
    //    println(rowRev)
    //    println(colRev)
    //    println(rowRun)
    //    println(colRun)

    var keyByNum: HashMap[Int, NumRes] = null
    var keyByFreq: mutable.Map[Int, mutable.Set[Int]] = null
    var curNum: Int = 0
    def insertNumber(entry: (Int, Set[Int]), isRow: Boolean) = {
      entry._2.foreach(x => {
        keyByNum.get(x) match {
          case None => {
            keyByNum.put(x, NumRes(isRow, !isRow, entry._1))
            keyByFreq.get(entry._1) match {
              case None => keyByFreq.put(entry._1, Set(x))
              case Some(y) => y.add(x)
            }
          }
          case Some(numRes) => {
            val prevCount = numRes.count
            var count: Int = entry._1 + prevCount
            if (curNum == x)
              count -= 1
            keyByNum.put(x, NumRes(true, true, count))
            keyByFreq.get(prevCount).get.remove(x)
            keyByFreq.get(count) match {
              case None => keyByFreq.put(count, Set(x))
              case Some(y) => y.add(x)
            }
          }
        }
      })
    }
    var ans: Int = 0
    for (j <- 0 to n - 1) {
      for (k <- 0 to m - 1) {
        curNum = in(j)(k)
        val (rowIt, colIt, rowRunIt, colRunIt, curNumVisited) =
          (rowRev(j).iterator, colRev(k).iterator, rowRun(j).iterator, colRun(k).iterator, false)
        var curF = 0
        keyByNum = HashMap[Int, NumRes]()
        keyByFreq = new util.TreeMap[Int, Set[Int]](Ordering.Int.reversed()).asScala
        breakable {
          while (true) {
            if (!rowIt.hasNext && !colIt.hasNext)
              break;
            if (rowIt.hasNext)
              insertNumber(rowIt.next(), true)
            if (colIt.hasNext)
              insertNumber(colIt.next(), true)
          }
        }
        curF = keyByFreq.keys.iterator.next()
        //        print(curF + " ")
        if (curF > ans)
          ans = curF
      }
      //      println()
    }
    println(ans)
  }
  println(System.currentTimeMillis() - ct)
  reader.close();

  class Counter extends HashMap[Int, Int] {
    def increment(k: Int): Unit = {
      val v: Option[Int] = get(k);
      v match {
        case None => this += (k -> 1)
        case Some(value) => this += (k -> (value + 1))
      }
    }
  }

  case class NumRes(rowSet: Boolean, colSet: Boolean, count: Int)
}


