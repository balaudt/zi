package zi.chef.y16.feb

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balaudt on 8/25/16.
  */
object Kruskal extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val nm = lines.next().split(" ").map(_.toInt)
  val edges = mutable.HashMap[(Int, Int), Int]()
  for (i <- 1 to nm(1)) {
    val edge = lines.next().split(" ").map(_.toInt)
    edges.put((edge(0), edge(1)), edge(2))
  }
  val sortedEdges = edges.toSeq.sortBy(_._2)
  val disjointSet = new SimpleDisjointSet[Int]()
  for (i <- 1 to nm(0)) disjointSet.makeSet(i)
  var (ans, flag) = (0, false)
  for (edge <- sortedEdges) {
    if (disjointSet.find(edge._1._1) != disjointSet.find(edge._1._2)) {
      disjointSet.union(edge._1._1, edge._1._2)
      ans += edge._2
    }
    if (disjointSet.lookup.size == nm(0)) flag = true
  }
  println(ans)
}
