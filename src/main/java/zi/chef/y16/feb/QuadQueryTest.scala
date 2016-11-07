package zi.chef.y16.feb

import java.io.PrintStream

import scala.util.Random

/**
  * Created by balaudt on 9/10/16.
  */
object QuadQueryTest extends App {
  def gen(num: Int) = {
    val stream = new PrintStream(s"/Users/balaudt/Temp/qq-gen$num.in")
//    System.setOut(stream)
    val (n, m) = (10 + Random.nextInt(10), 10 + Random.nextInt(10))
    stream.println(n)
    for (i <- 0 until n)
      stream.println(s"${(Random.nextInt(1000) + 1) * Math.pow(-1, Random.nextInt(2)).toInt} ${(Random.nextInt(1000) + 1) * Math.pow(-1, Random.nextInt(2)).toInt}")
    stream.println(m)
    for (i <- 1 to m) {
      val c = Random.nextInt(3) match {
        case 0 => 'X'
        case 1 => 'Y'
        case 2 => 'C'
      }
      val l = Random.nextInt(n) + 1
      val r = if (l == n) n else Random.nextInt(n - l) + l
      stream.println(s"$c $l $r")
    }
    stream.close()
  }

  def solve(num: Int) = {
    case class Point(var x: Int, var y: Int){
      override def toString: String = s"($x,$y)"
    }

    def quad(p: Point) =
      if (p.x > 0 && p.y > 0) 1
      else if (p.x > 0) 4
      else if (p.y > 0) 2
      else 3

    val lines = scala.io.Source.fromFile(s"/Users/balaudt/Temp/qq-gen${num}.in").getLines()
    val stream = new PrintStream(s"/Users/balaudt/Temp/qq-gen${num}e.out")
    System.setOut(stream)
    val n = lines.next().toInt
    val pts = Array.ofDim[Point](n)
    for (i <- 0 until n) {
      pts(i) = Point(0, 0)
      val pt = lines.next().split(" ").map(_.toInt)
      pts(i).x = pt(0)
      pts(i).y = pt(1)
    }
    val m = lines.next().toInt
    for (i <- 0 until m) {
      val tlr = lines.next().split(" ")
      val (op, l, r) = (tlr(0).toCharArray()(0), tlr(1).toInt - 1, tlr(2).toInt - 1)
      op match {
        case 'C' =>
          val out = Array.ofDim[Int](4)
          for (j <- l to r) out(quad(pts(j)) - 1) += 1
          stream.println(out.mkString(" "))
        case 'X' => for (j <- l to r) pts(j).y = -pts(j).y
        case 'Y' => for (j <- l to r) pts(j).x = -pts(j).x
      }
//      println(pts.mkString(" "))
    }
    stream.close()
  }

//  for(i<-1 to 100) gen(i)
    for(i<-1 to 100) solve(i)
//  solve(9)
}
