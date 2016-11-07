package zi.chef.y16.may

import java.util

import scala.collection.JavaConverters._
import scala.collection.Searching._
import org.paukov.combinatorics.Factory

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
  * Created by balaudt on 5/12/16.
  */
object Math extends App {
  val ct = System.currentTimeMillis()
  val compositions = mutable.HashMap[(Int, Int), ListBuffer[Seq[Integer]]]()
  for (i <- 1 to 10) {
    Factory.createCompositionGenerator(i).asScala.foreach(part => {
      val scalPart: Seq[Integer] = part.asScala.toSeq
      compositions.get((i, scalPart.size)) match {
        case Some(x) => x += scalPart
        case None => compositions += (i, scalPart.size) -> ListBuffer(scalPart)
      }
    })
  }

  val FIB_LIM = 44
  val fib = Array.ofDim[Int](FIB_LIM)
  fib(0) = 1
  fib(1) = 2
  var ind = 1
  while (fib(ind) < 1e9.toInt) {
    fib(ind + 1) = fib(ind) + fib(ind - 1)
    ind += 1
  }

  val MAX_K = 10
  val g = Array.ofDim[Int](FIB_LIM, MAX_K + 1)

  def generate(n: Int, m: Int) = {
    var ans = 0
    Factory.createMultiCombinationGenerator(Factory.createVector(fib.take(n).toList.asJava), m).asScala.foreach(x => if (x.asScala.sum == fib(n)) {
      ans += 1
    })
    ans
  }

  for (i <- 0 until FIB_LIM) {
    g(i)(1) = 1
    for (j <- 2 to 4) {
      g(i)(j) = generate(i, j)
    }
  }
  for (i <- 0 to 15) {
    for (j <- 5 to 10) {
      g(i)(j) = generate(i, j)
    }
  }

  def getFibSplit(in: Int) = {
    var n = in
    val out = ArrayBuffer[Int]()
    while (n > 0) {
      var ip = util.Arrays.binarySearch(fib, n)
      if (ip >= 0) {
        out += ip
        println(fib(ip))
        n -= fib(ip)
      } else {
        ip = -ip - 2
        n -= fib(ip)
        println(fib(ip))
        out += ip
      }
    }
    out
  }

  val in = 22
  val k = 4
  val nSplit = getFibSplit(in)
  var ans1 = 0l
  g.foreach(x=>println(x.mkString(" ")))
  println(nSplit)
  println(compositions.get((k, nSplit.size)))
  compositions.get((k, nSplit.size)).foreach(comp => {
    comp.foreach((x: Seq[Integer]) => {
      var temp = 1l
      for (y <- 0 until nSplit.size)
        temp *= g(nSplit(y))(x(y))
      ans1 += temp
    })
  })
  println(s"ans1: $ans1")
  var ans2 = 0l
  Factory.createMultiCombinationGenerator(Factory.createVector(fib.take(15).toList.asJava), k).asScala.foreach(x => if (x.asScala.sum == in) {
    println(x.asScala)
    ans2 += 1
  })
  println(s"ans2: $ans2")
  if (true)
    System.exit(0)

  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val q = lineIt.next().toInt
  for (i <- 1 to q) {
    val xk = lineIt.next().split(" ").map(_.toInt)
    val (x, k) = (xk(0), xk(1))
    if (x > 100 && k > 4)
      throw new UnsupportedOperationException
    val fibSplit = getFibSplit(x)
    if (k < fibSplit.size)
      println(0)
    else {
      val parts = compositions.get((k, fibSplit.size))

    }
  }
  println(System.currentTimeMillis() - ct)
}
