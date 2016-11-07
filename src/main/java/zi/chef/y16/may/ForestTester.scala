package zi.chef.y16.may

import java.io.{FileWriter, PrintStream}

import scala.util.Random

/**
  * Created by balaudt on 5/7/16.
  */
object ForestTester extends App {
  def test = {
    val lineIt = io.Source.fromFile("/Users/balaudt/Temp/may/forest.in").getLines()
    val nwl = lineIt.next().split(" ")
    val (n, w, l) = (nwl(0).toInt, nwl(1).toLong, nwl(2).toDouble)

    case class Tree(ht: Int, r: Int, m: Long) extends Ordered[Tree] {
      override def compare(that: Tree): Int = that.m compare m
    }

    val trees = Array.ofDim[Tree](n)
    for (i <- 0 until n) {
      val hr = lineIt.next().split(" ").map(_.toInt)
      trees(i) = Tree(hr(0), hr(1), 0)
    }

    var (ans, flag) = (-1, false)
    while (!flag) {
      ans += 1
      var sum = 0l
      for (i <- 0 until n)
        if (trees(i).ht + trees(i).r * ans >= l)
          sum += trees(i).ht + trees(i).r * ans
      if (sum >= w)
        flag = true
    }
    println(ans)
  }

  def gen = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/may/forest.in"))
    val n = Random.nextInt(100) + 10
    val w = Random.nextInt(10000) + 10
    val l = Random.nextInt(10000) + 10
    println(s"$n $w $l")
    for (i <- 0 until n) {
      val (h, r) = (Random.nextInt(1000) + 1, Random.nextInt(1000) + 1)
      println(s"$h $r")
    }
  }

  test
}
