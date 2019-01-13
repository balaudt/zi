package zi.chef.y17.jan.longg

import java.util
import java.util.Collections

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 1/8/17.
  */
object CapitalMovement extends App {

  case class Node(ind: Int, pop: Int) extends Comparable[Node] {
    val adjs: mutable.PriorityQueue[Int] = mutable.PriorityQueue[Int]()
    adjs += pop

    override def compareTo(o: Node): Int = pop.compareTo(o.pop)

    override def hashCode(): Int = pop.hashCode()

    override def equals(obj: scala.Any): Boolean = obj.isInstanceOf[Node] && obj.asInstanceOf[Node].pop == pop
  }

  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (_ <- 1 to t) {
    val n = lines.next().toInt
    val p = lines.next().split(" ").zipWithIndex.map(x => Node(x._2 + 1, x._1.toInt))
    val q = new util.TreeSet[Node](Collections.reverseOrder[Node]())
    p.foreach(x => q.add(x))
    for (_ <- 1 until n) {
      val vu = lines.next().split(" ").map(_.toInt - 1)
      val (v, u) = (vu(0), vu(1))
      p(v).adjs += p(u).pop
      p(u).adjs += p(v).pop
    }
    val out = Array.ofDim[Int](n)
    for (i <- 0 until n) {
      val qit = q.iterator()
      var outInd = 0
      while (outInd == 0 && p(i).adjs.nonEmpty) {
        val j = p(i).adjs.dequeue()
        val pos = qit.next()
        if (pos.pop != j) outInd = pos.ind
      }
      if (outInd == 0 && qit.hasNext) outInd = qit.next().ind
      out(i) = outInd
    }
    println(out.mkString(" "))
  }
}
