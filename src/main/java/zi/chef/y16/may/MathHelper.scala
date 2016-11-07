package zi.chef.y16.may

import org.paukov.combinatorics.Factory

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by chaba005 on 5/10/2016.
  */
object MathHelper extends App {
  val FIB_LIM = 44
  val fib = Array.ofDim[Int](FIB_LIM)
  fib(0) = 1
  fib(1) = 2
  var ind = 1
  while (fib(ind) < 1e9.toInt) {
    fib(ind + 1) = fib(ind) + fib(ind - 1)
    ind += 1
  }
  //  println(ind)

  val MAX_K = 10
  val g = Array.ofDim[Int](FIB_LIM, MAX_K)
  g(0)(0) = 1
  for (i <- 1 until MAX_K) g(0)(i) = 0
  g(1)(0) = 1
  g(1)(1) = 1
  for (i <- 2 until MAX_K) g(1)(i) = 0
  for (i <- 2 until FIB_LIM) {
    g(i)(0) = 1
    for (j <- 2 to MAX_K) {
      var cur = 0
      for (k <- 1 until j) {
        cur += g(i - 1)(k - 1) * g(i - 2)(j - k - 1)
      }
      g(i)(j - 1) = cur
    }
  }

//  println(g.foreach(x => println(x.mkString(","))))

  def testG(n: Int, m: Int) = {
    //    println(fib(n))
    var ans = 0
    Factory.createMultiCombinationGenerator(Factory.createVector(fib.take(n).toList.asJava), m).asScala.foreach(x => if (x.asScala.sum == fib(n)) {
      ans += 1
//      println(x.asScala.mkString(" "))
    })
    ans
  }

//  testG(4, 4)

    val newG = Array.ofDim[Int](15, MAX_K)
    for (i <- 0 until 15) {
      for (j <- 1 to MAX_K) {
        newG(i)(j - 1) = testG(i + 1, j)
        //        print((g(i)(j - 1) - newG(i)(j - 1)) + " ")
        print(newG(i)(j - 1) + " ")
      }
      println
    }

  /*val partitions = mutable.HashMap[(Int, Int), ListBuffer[Seq[Integer]]]()
  for (i <- 1 to 10) {
    Factory.createPartitionGenerator(i).asScala.foreach(part => {
      val scalPart: Seq[Integer] = part.asScala.toSeq
      partitions.get((i, scalPart.size)) match {
        case Some(x) => x += scalPart
        case None => partitions += (i, scalPart.size) -> ListBuffer(scalPart)
      }
    })
  }
  println(partitions)*/
}
