package zi.chef.y16.snck.r1A

import java.io.PrintStream

import org.apache.commons.collections4.ListUtils
import org.apache.commons.math3.primes.Primes
import org.paukov.combinatorics.{Factory, ICombinatoricsVector}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * Created by balaudt on 6/4/16.
  */
object LISAnalysis extends App {
  //  System.setOut(new PrintStream("/Users/balaudt/Temp/LIS.txt"))

  def longest(in: List[Int]): ArrayBuffer[List[Int]] = {
    var out = ArrayBuffer[List[Int]]()
    var len = Int.MinValue
    for (y <- Factory.createSubSetGenerator(Factory.createVector(in.asJava)).asScala) {
      val x = y.asScala.toList
      if (!x.isEmpty) {
        var (flag, last) = (true, x.head)
        for (elem <- x.tail if (flag)) if (elem < last) flag = false else last = elem
        if (flag)
          if (x.size > len) {
            len = x.size
            out = ArrayBuffer(x)
          } else if (x.size == len)
            out += x
      }
    }
    out
  }

  def countLongest(in: List[Int], lis: Int): Int = {
    var len = Int.MinValue
    var ct = 0
    for (y <- Factory.createSimpleCombinationGenerator(Factory.createVector(in.asJava), lis).asScala) {
      val x = y.asScala.toList
      var (flag, last) = (true, x.head)
      for (elem <- x.tail if (flag)) if (elem < last) flag = false else last = elem
      if (flag)
        ct += 1
    }
    ct
  }

  //  println(longest(List(21, 22, 23, 24, 25, 2, 1, 5, 4, 7, 6, 9, 8, 11, 10)).length)
  //  println(countLongest(List(21, 22, 23, 24, 25, 2, 1, 5, 4, 7, 6, 9, 8, 11, 10), 5))
  println(countLongest(List(55,56,57,58,59,50,49,51,52,53,54,41,40,44,43,42,46,45,47,48,28,27,31,30,29,36,35,34,33,32,38,37,39,2,1,5,4,3,10,9,8,7,6,17,16,15,14,13,12,11,26,25,24,23,22,21,20,19,18), 5))

  println(longest(List(2, 1, 5, 4, 3, 10, 9, 8, 7, 6, 13, 12, 11)).length)

  def dummy = {
    val some = 65535
    for (i <- 2 to 17 if (Primes.isPrime(i))) {
      var (ct, bt) = (0, 1)
      val str = Integer.toString(some, i)
      str.reverse.foreach(x => {
        ct += bt * (x - '0')
        bt += 1
      })
      println((i, ct, str))
    }
  }

  def gen = {
    var list = ArrayBuffer[List[Int]](List(1))
    for (j <- 2 to 9) {
      var curPerms = ArrayBuffer[List[Int]]()
      for (lastPerm <- list) {
        for (k <- 0 to lastPerm.size) {
          val curPerm = lastPerm.take(k) ++ List(j) ++ lastPerm.drop(k)
          val longs = longest(curPerm)
          if (longs.length > curPerm.size) {
            println("PERM: " + curPerm.mkString(" ") + " l " + longs.length)
            for (elem <- longs) print(elem.mkString(" ") + ",")
            println()
          }
          curPerms += curPerm
        }
      }
      list = curPerms
    }
  }

  //  gen

  def mixedRadix = {
    val m = Array(2, 3, 5, 7, 11, 13, 17)
    val mprod = m.scanLeft(1)(_ * _)
    val c = Array.ofDim[Int](m.length, m.length)
    for (i <- m.indices) {
      for (j <- m.indices) {
        if (i != j) {
          c(i)(j) = BigInt(m(i)).modInverse(BigInt(m(j))).toInt
        }
      }
    }
    //    c.foreach(x => println(x.mkString(" ")))

    val num = Random.nextInt(50) + 50
    println(num)
    val u = m.map(num % _)
    val v = ArrayBuffer(u(0))
    var (ans, flag) = (v(0), true)
    for (i <- 1 until m.length if (flag)) {
      var cur = ((u(i) - v(0)) * c(0)(i)) % m(i)
      for (j <- 1 until i) {
        cur = ((cur - v(j)) * c(j)(i)) % m(i)
      }
      if (cur < 0)
        cur += m(i)
      v += cur
      ans += mprod(i) * cur
      if (ans == num)
        flag = false
    }
    println(v)
  }

}
