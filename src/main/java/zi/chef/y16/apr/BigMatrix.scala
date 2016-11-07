package zi.chef.y16.apr

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import java.util
import scala.collection.JavaConverters._

/**
  * Created by balaudt on 4/8/16.
  */
object BigMatrix extends App {

  val smallPrimes = Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
    79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
    181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283,
    293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419,
    421, 431, 433, 439, 443, 449)

  val memo = mutable.HashMap[Int, mutable.HashMap[Int, Int]]()

  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    var in = lineIt.next().split(" ").map(_.toInt)
    val (n, m, c, d, mod) = (in(0), in(1), in(2), in(3), in(4))
    val carrots = mutable.HashSet[Int]()
    for (j <- 1 to c) {
      in = lineIt.next().split(" ").map(_.toInt)
      carrots += (in(0) - 1) * m + in(1) - 1
    }
    if (n <= 5 & m <= 5) {
      val paths = crossPathAnalysis(n, m)
      var ans = 0
      for (path <- paths) {
        var eatenCarrots = 0
        for (cell <- path if eatenCarrots <= d) if (carrots.contains(cell)) eatenCarrots += 1
        if (eatenCarrots <= d) ans += 1
      }
      ans /= 2
      ans %= mod
      println(ans)
    } else if (c == 0) {
      println(closedForm(n, m, mod))
    } else throw new UnsupportedOperationException
  }

  case class Point(x: Int, y: Int)

  def solve(n: Int, m: Int, c: Int, mod: Int, cl: mutable.HashSet[Point]): Int = {
    val memo = mutable.HashMap[(Point, Point, Int), Int]()
    def traverse(bunny1: Point, bunny2: Point, eatenCarrots: Int): Int = {
      if (memo.contains((bunny1, bunny2, eatenCarrots)))
        return memo.get((bunny1, bunny2, eatenCarrots)).get
      if (bunny1.x == m && bunny1.y == n - 1 && bunny2.x == m - 1 && bunny2.y == n)
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

    var initCarrots = 0
    if (cl.contains(Point(2, 1))) initCarrots += 1
    if (cl.contains(Point(1, 2))) initCarrots += 1
    if (initCarrots > c)
      return 0
    traverse(Point(2, 1), Point(1, 2), initCarrots)
  }

  def allPaths(n: Int, m: Int): mutable.HashSet[mutable.Buffer[Int]] = {
    val result = mutable.HashSet[mutable.Buffer[Int]]()
    visit(new ListBuffer[Int](), 0, 0)
    def visit(path: mutable.Buffer[Int], y: Int, x: Int): Unit = {
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

  def crossPathAnalysis(n: Int, m: Int): mutable.HashSet[mutable.Buffer[Int]] = {
    val cellCts = new util.TreeMap[Int, Int]().asScala
    for (i <- 1 until n * m) {
      cellCts += i -> 0
    }
    val paths = allPaths(n, m)
    paths.foreach(_.foreach(cell => cellCts += (cell -> (1 + cellCts.get(cell).get))))
    //    println(cellCts)
    val crossPaths = mutable.HashSet[mutable.Buffer[Int]]()
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


  def getFactors(num: Int, res: mutable.HashMap[Int, Int]): Unit = {
    var numMap = mutable.HashMap[Int, Int]()
    if (memo.contains(num)) {
      numMap = memo.get(num).get
    } else {
      var n = num
      for (prime <- smallPrimes) {
        while (n % prime == 0) {
          n /= prime
          numMap.get(prime) match {
            case None => numMap += prime -> 1
            case Some(x) => numMap += prime -> (x + 1)
          }
        }
      }
      if (n != 1)
        numMap += n -> 1
      memo += num -> numMap
    }
    for (elem <- numMap) {
      res.get(elem._1) match {
        case None => res += elem
        case Some(x) => res += elem._1 -> (x + elem._2)
      }
    }
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

}
