package zi.chef.y16.may

import java.io.{FileInputStream, PrintStream}

import org.apache.commons.math3.util.{ArithmeticUtils, CombinatoricsUtils}
import org.paukov.combinatorics.Factory

import scala.collection.JavaConverters._
import scala.util.Random

/**
  * Created by balaudt on 5/15/16.
  */
object GcdHelper extends App {
  def facts = {
    val ct = System.currentTimeMillis()
    val N = 1e5.toInt
    val factorials = Array.ofDim[Int](N + 1)
    val inverseFactorials = Array.ofDim[Int](N + 1)
    var fact = 1l
    val P = (1e9 + 7).toInt
    val PBI = BigInt.apply(P)
    factorials(0) = 1
    inverseFactorials(0) = -1
    for (i <- 1 to N) {
      fact *= i
      fact %= P
      factorials(i) = fact.toInt
      inverseFactorials(i) = BigInt.apply(fact).modInverse(PBI).toInt
      val t = (1l * factorials(i) * inverseFactorials(i)) % P
      if (t != 1)
        println(i)
    }
    println(System.currentTimeMillis() - ct)
    println(BigInt.apply(1).modInverse(PBI).toInt)
  }

  def generate = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/may/gcd.in"))
    val t = 10
    println(t)
    for (i <- 1 to t) {
      println(s"${90 + i} ${90 + Random.nextInt(10)}")
    }
  }

  def solve = {
    System.setIn(new FileInputStream("/Users/balaudt/Temp/may/gcd.in"))
    val lineIt = io.Source.fromInputStream(System.in).getLines()
    val t = lineIt.next().toInt
    for (i <- 1 to t) {
      val nm = lineIt.next().split(" ").map(_.toInt)
      val (n, m) = (nm(0), nm(1))
      var ct = 0l
      Factory.createPermutationWithRepetitionGenerator(Factory.createVector((1 to m).toList.asJava), n).asScala.foreach(comb => {
        var flag = true
        for (cur <- Factory.createSimpleCombinationGenerator(Factory.createVector(comb), 2).asScala if (flag)) {
          if (ArithmeticUtils.gcd(cur.getValue(0), cur.getValue(1)) != 1) flag = false
        }
        if (flag) {
          ct += 1
          println(comb)
        }
      })
      println(ct)
    }
  }

  generate
}
