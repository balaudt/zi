package zi.chef.y16.apr

import java.util

import org.apache.commons.math3.util.{ArithmeticUtils, CombinatoricsUtils}

import scala.collection.mutable._
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Random


/**
  * Created by balaudt on 4/7/16.
  */
object BigMatrixAnalysis extends App {
  def baseCount = {
    for (n <- 5 to 15) {
      for (m <- 5 to 15) {
        val f = Array.ofDim[Int](n, m)
        for (i <- 0 until n) {
          f(i)(0) = 1
        }
        for (i <- 0 until m) {
          f(0)(i) = 1
        }
        for (i <- 1 until n) {
          for (j <- 1 until m) {
            f(i)(j) = f(i - 1)(j) + f(i)(j - 1)
          }
        }
        println(s"${n} ${m} ${f(n - 1)(m - 1)}")
      }
    }
  }

  def allPaths(n: Int, m: Int): HashSet[Buffer[Int]] = {
    val result = HashSet[Buffer[Int]]()
    visit(new ListBuffer[Int](), 0, 0)
    def visit(path: Buffer[Int], y: Int, x: Int): Unit = {
      if (y == n - 1 && x == m - 1) {
        path.trimEnd(1)
        result += path
        return
      }
      if (y != n - 1) {
        val newPath = new ListBuffer[Int]()
        path.copyToBuffer(newPath)
        newPath += (y + 1) * m + x
        visit(newPath, y + 1, x)
      }
      if (x != m - 1) {
        val newPath = new ListBuffer[Int]()
        path.copyToBuffer(newPath)
        newPath += y * m + x + 1
        visit(newPath, y, x + 1)
      }
    }
    //    println(result.mkString("\n"))
    result
  }

  def crossPathAnalysis(n: Int, m: Int) = {
    val cellCts = new util.TreeMap[Int, Int]().asScala
    for (i <- 1 until n * m) {
      cellCts += i -> 0
    }
    val paths = allPaths(n, m)
    paths.foreach(_.foreach(cell => cellCts += (cell -> (1 + cellCts.get(cell).get))))
    //    println(cellCts)
    val crossPaths = HashSet[Buffer[Int]]()
    for (bunny1 <- paths) {
      for (bunny2 <- paths) {
        if (bunny1.intersect(bunny2).isEmpty) {
          crossPaths += bunny1 ++ bunny2
        }
      }
    }
    //    println(crossPaths.size)
    crossPaths
  }

  def tests = {
    for (n <- 5 to 7) {
      for (m <- 5 to 10) {
        val crossPathSize = crossPathAnalysis(n, m).size
        val allPathSize = allPaths(n, m).size
        println(s"${(n, m)} ${allPathSize} ${crossPathSize} ${crossPathSize / allPathSize} ${crossPathSize - allPathSize}")
      }
    }
    /*for (i <- 1 to 10) {
      val (n, m) = (5 + Random.nextInt(10), 5 + Random.nextInt(10))
      println(s"${(n, m)} ${allPaths(n, m).size}")
    }*/
    /*for (i <- 5 to 10) {
      println(allPaths(i, i).size)
    }*/
  }

  def ncr = {
    var (n, m) = (Random.nextInt(10) + 5, Random.nextInt(10) + 5)
    var (num, den) = (1l, 1l)
    if (n < m) {
      val t = m
      m = n
      n = t
    }
    for (i <- 0 until m) {
      num *= n - i
      den *= m - i
    }
    println(s"${(n, m)} ${num / den}")
  }

  case class Point(x: Int, y: Int)

  //    tests
  //  crossPathAnalysis(5,6).foreach(x=>println(x.sorted))
  def dynaApproach(n: Int, m: Int, c: Int, mod: Int, cl: mutable.HashSet[Point]) = {
    val memo = mutable.HashMap[Tuple3[Point, Point, Int], Int]()
    def traverse(bunny1: Point, bunny2: Point, eatenCarrots: Int): Int = {
      if (memo.contains((bunny1, bunny2, eatenCarrots)))
        return memo.get((bunny1, bunny2, eatenCarrots)).get
      if (bunny1.x == m && bunny2.y == n)
        return 1
      var ans = 0l
      val (b1r, b2r, b1d, b2d) = (Point(bunny1.x + 1, bunny1.y), Point(bunny2.x + 1, bunny2.y), Point(bunny1.x, bunny1.y + 1), Point(bunny2.x, bunny2.y + 1))
      val (b1rC, b2rC, b1dC, b2dC) = (if (cl.contains(b1r)) 1 else 0, if (cl.contains(b2r)) 1 else 0, if (cl.contains(b1d)) 1 else 0, if (cl.contains(b2d)) 1 else 0)
      //Case 00
      if (bunny1.x != m && eatenCarrots + b1rC + b2rC <= c)
        ans += traverse(b1r, b2r, eatenCarrots + b1rC + b2rC)
      //Case 11
      if (bunny2.y != n && eatenCarrots + b1dC + b2dC <= c)
        ans += traverse(b1d, b2d, eatenCarrots + b1dC + b2dC)
      ans %= mod
      //Case 01
      if (bunny1.x != m && bunny2.y != n && eatenCarrots + b1rC + b2dC <= c)
        ans += traverse(b1r, b2d, eatenCarrots + b1rC + b2dC)
      ans %= mod
      //Case 10
      if (!(bunny1.y + 1 == bunny2.y && bunny2.x + 1 == bunny1.x) && eatenCarrots + b1dC + b2rC <= c)
        ans += traverse(b1d, b2r, eatenCarrots + b1dC + b2rC)
      ans %= mod
      memo += (bunny1, bunny2, eatenCarrots) -> ans.toInt
      ans.toInt
    }
    traverse(Point(2, 1), Point(1, 2), 0)
  }

  for (n <- 2 to 10) {
    for (m <- 2 to 10) {
      println((n, m, dynaApproach(n, m, 0, 1e9.toInt, mutable.HashSet[Point]())))
    }
  }
  for (n <- 2 to 10) {
    for (m <- 0 to 10) {
      println((n, m, simpleFormula(n, m)))
    }
  }


  def simpleFormula(ni: Int, mi: Int): Long = {
    var (n, m) = (0, 0)
    if (ni < mi) {
      m = ni
      n = mi
    } else {
      n = ni
      m = mi
    }

    CombinatoricsUtils.binomialCoefficient(n + m - 1, n - 1) * CombinatoricsUtils.binomialCoefficient(n + m - 1, n - 2) / (m + n - 1)
  }

  def formula(n: Int, k: Int, mod: Int): Int = {
    var (l, r) = (0l, 0l)
    val nm = n + k - 1
    for (i <- 0 until k) {
      val den = BigInt.apply(k - i).modInverse(mod).toLong
      var num = nm - i
      var t = num * den
      t %= mod
      l += t
      l %= mod
      num -= 1
      t = num * den
      t %= mod
      r += t
      r %= mod
    }
    var ans = l * r
    ans %= mod
    ans *= BigInt.apply(n + k - 1).modInverse(mod).toInt
    ans %= mod
    ans.toInt
  }

  val smallPrimes = Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
    79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
    181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317,
    331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419,
    421, 431, 433, 439, 443, 449)

  def getFactors(num: Int, res: mutable.HashMap[Int, Int]) = {
    var n = num
    for (prime <- smallPrimes) {
      while (n % prime == 0) {
        n /= prime
        res.get(prime) match {
          case None => res += prime -> 1
          case Some(x) => res += prime -> (x + 1)
        }
      }
    }
    if (n != 1)
      res += n -> 1
  }

  def binomCoeff(n: Int, k: Int, mod: Int): Int = {
    val numMap = mutable.HashMap[Int, Int]()
    val denMap = mutable.HashMap[Int, Int]()
    for (i <- 0 until k) {
      getFactors(n - i, numMap)
      getFactors(k - i, denMap)
    }
    denMap.foreach(x => {
      numMap += x._1 -> (numMap.get(x._1).get - x._2)
    })
    var res = 1l
    numMap.filter(_._2 > 0).foreach(x => {
      for (i <- 1 to x._2) {
        res *= x._1
        res %= mod
      }
    })
    res.toInt
  }

  def closedForm(ni: Int, mi: Int, mod: Int): Int = {
    var (n, m) = (0, 0)
    if (ni < mi) {
      n = mi
      m = ni - 2
    }
    else {
      n = ni
      m = mi - 2
    }
    val numMap = mutable.HashMap[Int, Int]()
    val denMap = mutable.HashMap[Int, Int]()
    for (i <- 0 until m) {
      getFactors(n + m - 1 - i, numMap)
      getFactors(m - i, denMap)

      getFactors(n + m - 1 - i, numMap)
      getFactors(m + 1 - i, denMap)
    }
    getFactors(n - 1, numMap)
    getFactors(n + m - 1, denMap)
    denMap.foreach(x => {
      numMap += x._1 -> (numMap.get(x._1).get - x._2)
    })
    var res = 1l
    numMap.filter(_._2 > 0).foreach(x => {
      for (i <- 1 to x._2) {
        res *= x._1
        res %= mod
      }
    })
    res.toInt
  }

  println(closedForm(9, 8, 1e9.toInt))
  println(closedForm(2, 4, 1e9.toInt))
}
