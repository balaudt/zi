package zi.chef.y18.may

import java.io.PrintWriter

import scala.collection.mutable
import scala.util.Random

/**
  * @author balamurugan
  */
object FakeBinarySearchGenerator extends App {
  val writer = new PrintWriter("/Users/balamurugan/Temp/fakebinary.in")
  writer.println("1000")
  for (i <- 0 until 1000) {
    val n = Random.nextInt(50) + 1
    val q = Random.nextInt(n) + 1
    writer.println(s"$n $q")
    val a = mutable.HashSet[Int]()
    while (a.size < n) {
      val r = Random.nextInt(100.toInt)
      if (!a.contains(r)) a += r
    }
    writer.println(a.mkString(" "))
    val b = a.toArray
    for (j <- 0 until q) {
      writer.println(b(Random.nextInt(n)))
    }
  }
  writer.close()
}
