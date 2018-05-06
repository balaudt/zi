package zi.chef.y16.may

import java.io.{File, PrintStream}
import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by balaudt on 5/13/16.
  */
object AnotherMath extends App {
  val FIB_LIM = 44
  val fib = Array.ofDim[Int](FIB_LIM)
  fib(0) = 1
  fib(1) = 2
  var ind = 1
  while (fib(ind) < 1e9.toInt) {
    fib(ind + 1) = fib(ind) + fib(ind - 1)
    ind += 1
  }

  def getFibSplit(in: Int) = {
    var (n, upper, size) = (in, -1, 0)
    while (n > 0) {
      var ip = util.Arrays.binarySearch(fib, n)
      if (ip < 0) ip = -ip - 2
      size += 1
      if (upper < 0) upper = ip
      n -= fib(ip)
    }
    (upper, size)
  }

  class MultiCombination(n: Int, k: Int) extends Iterator[Array[Int]] {
    val bitVector = Array.fill(k)(0)
    var doNext = true

    override def hasNext: Boolean = doNext

    override def next(): Array[Int] = {
      val out = bitVector.clone()
      bitVector(k - 1) += 1
      if (bitVector(k - 1) > n - 1) {
        var index = -1
        for (i <- 1 to k if index == -1) if (bitVector(k - i) < n - 1) index = k - i
        if (index == -1) doNext = false
        else {
          bitVector(index) += 1
          for (i <- 1 until k - index) bitVector(index + i) = bitVector(index)
        }
      }
      out
    }
  }

  val lineIt = Source.fromInputStream(System.in).getLines()
//      val lineIt = io.Source.fromFile(new File("/Users/balaudt/Temp/may/Math.in")).getLines()
//    System.setOut(new PrintStream("/Users/balaudt/Temp/may/math.out"))
  val q = lineIt.next().toInt
  for (i <- 1 to q) {
    val xk = lineIt.next().split(" ").map(_.toInt)
    val (x, k) = (xk(0), xk(1))
    if (x > 100 && k > 5)
      throw new UnsupportedOperationException
    val upper = getFibSplit(x)._1
    //    if (k < size)
    //      println(0)
    if (k == 1)
      println(if (util.Arrays.binarySearch(fib, x) >= 0) 1 else 0)
    else {
      var ans = 0
      val scanned = mutable.HashSet[ArrayBuffer[Int]]()
      new MultiCombination(upper + 1, k - 1).foreach(perm => {
        val permSum = perm.foldLeft(0)((sum, ind) => sum + fib(ind))
        val ip = util.Arrays.binarySearch(fib, x - permSum)
        if (ip >= 0) {
          var toScan = new ArrayBuffer[Int](k)
          perm.copyToBuffer(toScan)
          toScan += ip
          toScan = toScan.sorted
          if (!scanned.contains(toScan)) {
            ans += 1
            scanned += toScan
          }
        }
      })

      println(ans)
    }
  }
}
