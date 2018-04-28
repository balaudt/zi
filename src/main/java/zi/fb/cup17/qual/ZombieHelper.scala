package zi.fb.cup17.qual

import java.io.PrintStream
import java.util.regex.Pattern

import org.apfloat.Apint

import scala.collection.mutable

/**
  * Created by balaudt on 1/7/17.
  */
object ZombieHelper extends App {
  private def brute() = {
    val y = 8
    for (x <- 1 to 8) {
      val dist = mutable.HashMap[Int, Int]()
      for (i <- 0 until Math.pow(y, x).toInt) {
        val sum = Integer.toString(i, y).toCharArray.map(_ - '0').sum + x
        dist.get(sum) match {
          case Some(z: Int) => dist += sum -> (z + 1)
          case None => dist += sum -> 1
        }
      }
      println(dist.toSeq.sortBy(_._1).map(_._2).mkString(","))
    }
  }

  private def simplified() = {
    val (x, y) = (8, 8)
    var ct = mutable.HashMap[Int, Int]()
    for (i <- 1 to y) ct += i -> 1
    for (i <- 1 to x) {
      val t = mutable.HashMap[Int, Int]()
      for (j <- 1 to y) {
        for (prev <- ct) {
          val nextKey = prev._1 + j
          t.get(nextKey) match {
            case Some(z: Int) => t += nextKey -> (z + prev._2 * i)
            case None => t += nextKey -> prev._2 * i
          }
        }
      }
      println(ct.toSeq.sortBy(_._1).map(_._2).mkString(","))
      ct = t
    }
  }

  private def closed() = {
    val (x, y) = (8, 8)
    var ct = Array.fill[Int](y)(1)
    for (i <- 1 to x) {
      val temp = Array.ofDim[Array[Int]](y)
      for (j <- 0 until y) {
        temp(j) = Array.ofDim[Int](ct.length + y - 1)
        System.arraycopy(ct, 0, temp(j), j, ct.length)
      }
      val next = Array.ofDim[Int](ct.length + y - 1)
      for (j <- 0 until ct.length + y - 1) {
        for (k <- 0 until y) {
          next(j) += temp(k)(j)
        }
      }
      ct = next
      println(ct.mkString(","))
    }
  }

  private def closedForApInt() = {
    val ctime = System.currentTimeMillis()
    val ps = new PrintStream("/Users/balamurugan/Temp/zombie-new.txt")
    val (x, y) = (20, 20)
    var ct = Array.fill[Apint](y)(new Apint(1))
    for (i <- 1 to x) {
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
    }
    ps.println(ct.mkString(","))
    ps.close()
    println(System.currentTimeMillis() - ctime)
  }
  val pattern = Pattern.compile("(\\d*)d(\\d*)(\\+?\\-?\\d*)")
  val matcher = pattern.matcher("1d10")
  matcher.find()
  println(matcher.group(3))
}
