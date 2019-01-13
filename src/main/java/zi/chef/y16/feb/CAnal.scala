package zi.chef.y16.feb

import edu.uci.ics.jung.algorithms.layout.TreeLayout
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance
import edu.uci.ics.jung.graph.{DelegateTree, UndirectedSparseGraph}
import zi.common.archive.GraphUtil

import scala.collection.mutable
import scala.util.control.Breaks._

/**
  * Created by balaudt on 2/8/16.
  */
object CAnal extends App {
  val (n, d) = (6,2)
  val tree = new UndirectedSparseGraph[Int, Int]()
  for (i <- 1 to n)
    tree.addVertex(i)
  var ec = 0
  tree.addEdge(ec,1,2)
  ec+=1
  tree.addEdge(ec,1,3)
  ec+=1
  tree.addEdge(ec,2,4)
  ec+=1
  tree.addEdge(ec,2,5)
  ec+=1
  tree.addEdge(ec,4,6)
  /*for (i <- 0 to d - 1) {
    val (jst, jend) = (1 << i, (1 << (i + 1)) - 1)
    breakable {
      for (j <- jst to jend) {
        tree.addEdge(ec, j, j * 2)
        if (j * 2 + 1 > n)
          break
        ec += 1
        tree.addEdge(ec, j, j * 2 + 1)
        if (j * 2 + 2 > n)
          break
        ec += 1
      }
    }
  }*/
  //  GraphUtil.printTree(tree, new TreeLayout[Int, Int](tree))
  val dijkstraDistance = new DijkstraDistance[Int, Int](tree)
  for (sv <- 1 to n) {
    println(s"s=${sv}")
    val it = dijkstraDistance.getDistanceMap(sv).entrySet().iterator()
    var (maxDist, setS) = (0, mutable.Set[Int]())
    while (it.hasNext) {
      val entry = it.next()
      if (maxDist == entry.getValue)
        setS.add(entry.getKey)
      else {
        maxDist = entry.getValue.intValue()
        setS = mutable.Set[Int](entry.getKey)
      }
    }
    println((maxDist, setS))
  }

}
