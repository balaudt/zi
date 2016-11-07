package zi.jam.y16.qual

import org.paukov.combinatorics.Factory

import scala.collection.mutable
import collection.JavaConverters._

/**
  * Created by balaudt on 4/9/16.
  */
object FractilesLargeHelper extends App {
  def printBitSet(bs: mutable.BitSet, len: Int) = {
    //    println(bs)
    for (i <- len - 1 to 0 by -1) if (bs(i)) print(1) else print(0)
    println()
  }

  def min(k: Int, c: Int): Unit = {
    println((k, c))
    val noOfInits = Math.pow(2, k).toInt
    var lastFracts = new Array[mutable.BitSet](noOfInits)
    val origSeq = new Array[mutable.BitSet](noOfInits)
    for (i <- 0 until noOfInits) {
      lastFracts(i) = new mutable.BitSet(k)
      origSeq(i) = new mutable.BitSet(k)
      val bs = Integer.toBinaryString(i).toCharArray.reverse
      for (j <- bs.indices)
        if (bs(j) == '1') {
          lastFracts(i) += j
          origSeq(i) += j
        }
    }
    for (cIt <- 1 until c) {
      val nextFracts = new Array[mutable.BitSet](noOfInits)
      val kPowCIt = Math.pow(k, cIt).toInt
      for (j <- 0 until noOfInits) {
        nextFracts(j) = new mutable.BitSet(kPowCIt * k)
        lastFracts(j).foreach(lead => {
          origSeq(j).foreach(nextFracts(j) += lead * k + _)
        })
      }
      lastFracts = nextFracts
    }
    val finBitCt = Math.pow(k, c).toInt
    val oneBitSet = new mutable.BitSet(finBitCt)
    for (i <- 0 until finBitCt) oneBitSet += i
    lastFracts = lastFracts.map(_ ^ oneBitSet)
//    lastFracts.foreach(printBitSet(_, finBitCt))

    var minBtsReqd = 1
    val vector = Factory.createVector(Range(0, finBitCt).toList.asJava)
    while (true) {
      if (minBtsReqd == k) {
        println("K!!!")
        return
      }
      Factory.createSimpleCombinationGenerator(vector, minBtsReqd).asScala.foreach(xs => {
        var flag = true
        for (lastFract <- lastFracts if (flag && lastFract.size > 0)) {
          var isAnyOn = false
          for (x <- xs.asScala if !isAnyOn) isAnyOn |= lastFract(x)
          if (!isAnyOn)
            flag = false
        }
        if (flag) {
          println(xs)
          println("_____")
          return
        }
      })
      minBtsReqd += 1
    }
  }

  for (k <- 2 to 8) {
    for (c <- 2 to 6) {
      min(k, c)
    }
  }
}
