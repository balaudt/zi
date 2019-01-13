package zi.chef.y16.may

import java.util
import scala.io
import java.io.{BufferedWriter, File, PrintStream}

import org.paukov.combinatorics.Factory
import scala.collection.JavaConverters._

import scala.util.Random

/**
  * Created by balaudt on 5/13/16.
  */
object MathTester extends App {
  val FIB_LIM = 44
  val fib = Array.ofDim[Int](FIB_LIM)
  fib(0) = 1
  fib(1) = 2
  var ind = 1
  while (fib(ind) < 1e9.toInt) {
    fib(ind + 1) = fib(ind) + fib(ind - 1)
    ind += 1
  }

  def generate = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/may/Math.in"))
    println(100)
    var num = 0l
    val P = 1e9.toInt
    var size = 1
    for (t <- 1 to 100) {
      do {
        num = 0
        size = Random.nextInt(4) + 1
        for (i <- 1 to size) num = (num + fib(Random.nextInt(FIB_LIM))) % P
      } while (num < 0)
      println(s"${num} 5")
    }
  }


  def solve = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/may/math-cor.out"))

    def getFibSplit(in: Int) = {
      var (n, upper, size) = (in, -1, 0)
      while (n > 0) {
        var ip = util.Arrays.binarySearch(fib, n)
        if (ip < 0) {
          ip = -ip - 2
        }
        size += 1
        if (upper < 0)
          upper = ip
        n -= fib(ip)
      }
      (upper, size)
    }

    val lineIt = io.Source.fromFile(new File("/Users/balaudt/Temp/may/Math.in")).getLines()
    val q = lineIt.next().toInt
    for (i <- 1 to q) {
      val xk = lineIt.next().split(" ").map(_.toInt)
      val (x, k) = (xk(0), xk(1))
      var ans = 0
      Factory.createMultiCombinationGenerator(Factory.createVector(fib.take(getFibSplit(x)._1 + 1).toList.asJava), k).asScala.foreach(perm => {
        if (perm.asScala.sum == x) ans += 1
      })
      println(ans)
    }
  }

  solve
}
