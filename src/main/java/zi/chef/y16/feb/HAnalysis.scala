package zi.chef.y16.feb

import java.io.PrintStream

import org.apache.commons.math3.primes.Primes
import org.apache.commons.math3.util.ArithmeticUtils
import scala.collection.JavaConverters._

/**
  * Created by balaudt on 2/13/16.
  */
object HAnalysis extends App {
  def getFactors(n: Int): Map[Integer, Int] = {
    Primes.primeFactors(n).asScala.groupBy(x => x).mapValues(_.length)
  }

  def funcF(n: Int): Int = {
    val factors = getFactors(n)
    val gcd = factors.values.reduce((x, y) => ArithmeticUtils.gcd(x, y))
    factors.map(factor => Math.pow(factor._1.toDouble, factor._2 / gcd).toInt).product
  }

  def bruteAnalysis() = {
    val fs = (for (i <- 2 to 1024) yield funcF(i)).scan(0)(_ + _).diff(List(0))
    fs.foreach(fun => println(fun))
    var last = 0
    fs.foreach(fun => {
      print(s"${fun - last}, ")
      last = fun
    })
  }

  def diffAnalysis() = {
    var (addSum, subSum) = (0l, 0l)
    Range(2, (1e3 + 1).toInt).map(x => (x, getFactors(x))).filter(x => {
      val groupedPows = x._2.values.groupBy(y => y)
      groupedPows.size == 1 && groupedPows.head._1 != 1
    }).foreach(x => {
      addSum += x._2.keys.reduce(_ * _)
      subSum += x._1
      println((x._2.keys.reduce(_ * _), x._1))
    })
    println((addSum, subSum))
  }

  def gen() = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/feb/H-gen.in"))
    println(1000)
    Range(5, 1025).foreach(println(_))
  }
  gen()
}
