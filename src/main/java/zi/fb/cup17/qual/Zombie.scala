package zi.fb.cup17.qual

import java.util.regex.Pattern

import org.apfloat.{Apfloat, Apint}

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 1/8/17.
  */
object Zombie extends App {
  val memo = mutable.HashMap[Int, Array[Array[Apint]]]()

  private def init() = {
    def runningSum(in: Array[Apint]) = {
      val out = Array.ofDim[Apint](in.length)
      var sum = new Apint(0)
      for (i <- in.indices) {
        sum = sum.add(in(i))
        out(i) = sum
      }
      out.reverse
    }

    Array(4, 6, 8, 10, 12, 20).foreach(y => {
      val out = Array.ofDim[Array[Apint]](20)
      memo += y -> out
      var ct = Array.fill[Apint](y)(new Apint(1))
      out(0) = runningSum(ct)
      for (i <- 1 until 20) {
        val temp = Array.ofDim[Array[Apint]](y)
        for (j <- 0 until y) {
          temp(j) = Array.fill[Apint](ct.length + y - 1)(new Apint(0))
          System.arraycopy(ct, 0, temp(j), j, ct.length)
        }
        val next = Array.fill[Apint](ct.length + y - 1)(new Apint(0))
        for (j <- 0 until ct.length + y - 1) {
          for (k <- 0 until y) {
            next(j) = next(j).add(temp(k)(j))
          }
        }
        ct = next
        out(i) = runningSum(ct)
      }
    })
  }

  init()
  //  println(memo(6)(5).mkString(","))
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  val pattern = Pattern.compile("(\\d*)d(\\d*)(\\+?\\-?\\d*)")
  for (i <- 1 to t) {
    val hs = lines.next().split(" ").map(_.toInt)
    val h = hs(0)
    var maxProb = 0.0
    val spells = lines.next().split(" ").map(spell => {
      val matcher = pattern.matcher(spell)
      matcher.find()
      val x = matcher.group(1).toInt
      val y = matcher.group(2).toInt
      var z = 0
      if (matcher.group(3).nonEmpty) z = matcher.group(3).toInt
      (x, y, z)
    })
    val one = new Apfloat(1.0)
    for (spell <- spells if maxProb < 1.0) {
      val (x, y, z) = spell
      val minSum = x + z
      if (h <= minSum) {
        maxProb = 1.0
      } else {
        val maxSum = x * y + z
        if (h <= maxSum) {
          val posSums = memo(y)(x - 1)
          val ind = h - minSum
          val prob = one.multiply(posSums(ind)).divide(posSums(0)).doubleValue()
          if (maxProb < prob) maxProb = prob
        }
      }
    }
    println(s"Case #$i: $maxProb")
  }
}
