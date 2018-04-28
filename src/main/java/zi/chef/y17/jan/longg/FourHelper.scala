package zi.chef.y17.jan.longg

import java.util

import org.apache.commons.math3.primes.Primes

import scala.util.Random


/**
  * Created by balamurugan on 1/11/17.
  */
object FourHelper extends App {
  private def junk() = {
    val limit = 75
    val out = new util.TreeMap[Int, (Int, Int, Int, Int)]()
    for (i <- 0 until limit) {
      for (j <- 0 until limit) {
        for (k <- 0 until limit) {
          for (l <- 0 until limit) {
            out.put(i * i + j * j + k * k + l * l, (i, j, k, l))
          }
        }
      }
    }
    val it = out.entrySet().iterator()
    while (it.hasNext) {
      val next = it.next()
      println((next.getKey, next.getValue))
    }
  }

  private def junk2() = {
    var num = Primes.nextPrime(32423)//Random.nextInt(1e9.toInt)
    println(num)
    while (num > 0) {
      val t = Math.floor(Math.sqrt(num)).toInt
      num -= t * t
      println(t)
    }
  }

  junk2()
}
