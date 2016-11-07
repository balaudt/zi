package zi.chef.y16.apr

import java.util

import org.paukov.combinatorics.Factory

import scala.util.Random
import collection.JavaConverters._
import scala.collection.mutable

/**
  * Created by balaudt on 4/2/16.
  */
object FibonacciQuery extends App {
  val fibL = 82
  val fib = new Array[Long](fibL)
  fib(0) = 0
  fib(1) = 1
  for (i <- 2 until fibL) fib(i) = fib(i - 1) + fib(i - 2)

  def byml = {
    val (y, n) = (12, 6)
    val a = new util.ArrayList[Int](n)
    for (i <- 0 until n) a.add(Random.nextInt(y) + 1)
    //    reset(a)
    println(a)
    var (fibSum, fibProd) = (0l, 1l)
    a.asScala.foreach(x => {
      fibSum += fib(x)
      fibProd *= fib(x)
      print(fib(x) + " ")
    })
    println()
    println(fibSum)
    println(fibProd)
    val avec = Factory.createVector(a)
    var ans = 0L
    var ansBySize = new util.TreeMap[Int, Long]().asScala
    var numsumBySize = new util.TreeMap[Int, Long]().asScala
    for (i <- 0 to n) {
      ansBySize.put(i, 0l)
      numsumBySize.put(i, 0l)
    }
    Factory.createSubSetGenerator(avec, false).asScala.foreach(subset => {
      val sum = subset.asScala.sum
      ans += fib(sum)
      ansBySize.put(subset.getSize, ansBySize.get(subset.getSize).get + fib(sum))
      numsumBySize.put(subset.getSize, numsumBySize.get(subset.getSize).get + sum)
    })
    println(ansBySize)
    println(numsumBySize)
    println(ans)
  }

  def reset(a: util.ArrayList[Int]) = {
    a.clear()
    a.add(2)
    a.add(2)
    a.add(3)
  }

  def regDataGen = {
    val (fibL, y, n) = (82, 12, 6)
    val a = new util.ArrayList[Int](n)
    for (i <- 0 until n) a.add(Random.nextInt(y) + 1)
    var ans = 0L
    Factory.createSubSetGenerator(Factory.createVector(a), false).asScala.foreach(subset => {
      ans += fib(subset.asScala.sum)
    })
    print(s"${ans},")
    for (i <- 0 until n - 1) print(s"${fib(a.get(i))} ")
    println(s"${fib(a.get(n - 1))}")
  }

  def actualCompute(a: util.List[Int]) = {
    var ans = 0L
    Factory.createSubSetGenerator(Factory.createVector(a), false).asScala.foreach(subset => {
      ans += fib(subset.asScala.sum)
    })
    ans
  }

  def tryAssociation = {
    for (i <- 1 to 128) {
      val a = new Array[Int](4)
      var fibSum = 0l
      var fibProd = 1l
      for (j <- 0 until 4) {
        a(j) = Random.nextInt(11) + 1
        fibSum += fib(a(j))
        fibProd *= fib(a(j))
      }
      val ab = actualCompute(List(a(0), a(1)).asJava)
      val cd = actualCompute(List(a(2), a(3)).asJava)
      val abcd = actualCompute(List(a(0), a(1), a(2), a(3)).asJava)
      println(s"${abcd},${fibSum} ${fibProd} ${ab} ${cd} ${ab * cd} ${ab + cd} ${fib(a(0))} ${fib(a(1))} ${fib(a(2))} ${fib(a(3))} ${fib(a(0)) + fib(a(1))} ${fib(a(2)) + fib(a(3))} ${fib(a(0)) * fib(a(1))} ${fib(a(2)) * fib(a(3))}")
    }
  }

  //H(a,b,c)=(1+phi^c)*H(a,b)
  def assertRecursion = {
    val (y, n) = (12, 4)
    val a = new util.ArrayList[Int](n)
    for (i <- 0 until n) {
      a.add(Random.nextInt(y) + 10)
      println(a.get(i))
      println(actualCompute(a))
      println(a.asScala.sum)
      println(fib(a.asScala.sum))
      println("______")
    }
  }

  def goldRatioPows = {
    val phi = (1 + Math.sqrt(5)) / 2
    val phiCap = (1 - Math.sqrt(5)) / 2
    for (i <- 2 to 16) {
      var ans: Double = 0
      for (j <- 0 until i) {
        ans += Math.pow(phi, j) + Math.pow(phiCap, i - 1 - j)
      }
      println(ans)
    }
  }

  //  goldRatioPows
  //  assertRecursion
  def phiPows = {
    val phi = (1 + Math.sqrt(5)) / 2
    val root5 = Math.sqrt(5)
    for (i <- 1 to 64) {
      println(Math.pow(phi, i) / root5 + "\t" + fib(i))
    }
  }

  def phi = {
    val P = (1e9 + 9).toInt
    for (i <- 1 to 1e7.toInt) {
      val t = i.toLong
      if ((t * t) % P == 5)
        println(t)
    }
  }


  //    println(Arrays.toString(fib))
  def brute(a: Array[Int]) = {
    for (i <- 1 to a.length) {
      var ans = 0l
      Factory.createSubSetGenerator(Factory.createVector(a.toList.take(i).asJava), false).asScala.foreach(x => {
        ans += fib(x.asScala.sum)
      })
      println(ans)
    }
  }

  val a = Array(10, 8, 7, 5, 3)
  brute(a)

  def formula(a: Array[Int]) = {
    val (phi, phicap) = ((1 + Math.sqrt(5)) / 2, (1 - Math.sqrt(5)) / 2)
    var h = fib(a(0)).toDouble
    var g = Math.pow(phicap, a(0))
    println(h)
    for (i <- 1 until a.length) {
      val x = a(i)
      h = (Math.pow(phi, x) + 1) * h + g * (fib(x) + fib(x + 2) - 1)
      g += Math.pow(phicap, x) * (1 + g)
      println(h)
    }
  }

  def formulaWithMod(a: Array[Int]) = {
    val P = (1e9 + 9).toInt
    val sqrtFive = 383008016
    val half = BigInt.apply(2).modInverse(P).toLong
    val (phiT, phicapT) = ((1 + sqrtFive) * half, (1 - sqrtFive) * half)
    var (phi, phicap) = ((phiT % P).toInt, (phicapT % P).toInt)
    phicap += P
    var h = fib(a(0))
    var g = multByExp(phicap, a(0), P).toLong
    println(h)
    for (i <- 1 until a.length) {
      val x = a(i)
      var t = multByExp(phi, x, P) + 1l
      t %= P
      h *= t
      h %= P
      t = g * (fib(x) + fib(x + 2) - 1)
      t %= P
      h += t
      h %= P

      t = (1 + g) * multByExp(phicap, x, P)
      t %= P
      g += t
      g %= P
      println(h)
    }
  }

  def multByExp(base: Int, exp: Int, mod: Int): Int = {
    var (t, ans) = (base.toLong, 1l)
    Integer.toBinaryString(exp).reverse.foreach(x => {
      if (x == '1') {
        ans *= t
        ans %= mod
      }
      t *= t
      t %= mod
    })
    return ans.toInt
  }

  println("----")
  formula(a)
  println("-----")
  formulaWithMod(a)
}
