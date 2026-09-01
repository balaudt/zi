package zi.chef.y16.nov

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balaudt on 11/10/16.
  */
object ChefSquare extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val k = lines.next().toInt
    val out = Array.ofDim[Int](k, k)
    val cols = Array.fill(k)(mutable.HashSet[Int]())
    for (i <- 0 until k) {
      out(k / 2)(i) = i + 1
      cols(i) += (i + 1)
    }
    var (dir, flag) = (false, true)
    var temp = mutable.HashSet[Int]()
    for (i <- 1 to k / 2) {
      var row = mutable.PriorityQueue[Int]()(Ordering[Int].reverse) ++ (1 to k)
      for (j <- if (dir) 0 until k else k - 1 to 0 by -1) {
        flag = false
        while (!flag) {
          val cell = row.dequeue()
          if (cols(j).contains(cell)) temp += cell
          else {
            flag = true
            row ++= temp
            temp.clear()
            cols(j) += cell
            out(k / 2 + i)(j) = cell
          }
        }
      }
      row = mutable.PriorityQueue[Int]()(Ordering[Int].reverse) ++ (1 to k)
      for (j <- if (!dir) 0 until k else k - 1 to 0 by -1) {
        flag = false
        while (!flag) {
          val cell = row.dequeue()
          if (cols(j).contains(cell)) temp += cell
          else {
            flag = true
            row ++= temp
            temp.clear()
            cols(j) += cell
            out(k / 2 - i)(j) = cell
          }
        }
      }
      dir = !dir
      debug(out)
    }
  }

  def debug(in: Array[Array[Int]]) = in.foreach(x => println(x.mkString(" ")))
}
