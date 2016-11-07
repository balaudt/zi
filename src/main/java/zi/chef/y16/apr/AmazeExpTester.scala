package zi.chef.y16.apr

import java.io.{BufferedWriter, FileWriter, PrintStream}

import edu.princeton.cs.algorithms.GraphGenerator
import edu.uci.ics.jung.algorithms.layout.TreeLayout
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance
import edu.uci.ics.jung.graph.util.EdgeType
import edu.uci.ics.jung.graph.{DelegateTree, UndirectedSparseGraph}
import org.apache.commons.collections15.Transformer
import zi.common.GraphUtil

import scala.Iterable
import scala.collection.mutable._
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.io.Source
import scala.util.Random

/**
  * Created by balaudt on 4/5/16.
  */
object AmazeExpTester extends App {

  def gen = {
    val graph = GraphGenerator.tree(64 + Random.nextInt(64))
    //    println(graph)
    val writer = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/apr/exp.in"))
    writer.append("1\n")
    writer.append(graph.V() + "\n")
    val visited = Set[Int]()
    var nextVertices = Set[Int]()
    graph.adj(0).asScala.foreach(x => {
      nextVertices += x
      writer.append(s"1 ${x + 1} ${Random.nextInt(16) + 1}\n")
    })
    visited.add(0)
    while (!nextVertices.isEmpty) {
      val newNext = Set[Int]()
      nextVertices.foreach(u => {
        graph.adj(u).asScala.foreach(v => {
          if (!visited.contains(v)) {
            newNext += v
            writer.append(s"${u + 1} ${v + 1} ${Random.nextInt(16) + 1}\n")
          }
          visited += u
        })
      })
      nextVertices = newNext
    }

    writer.close()
  }

  def brute = {
    val lineIt = Source.fromFile("/Users/balaudt/Temp/apr/exp.in").getLines
    System.setOut(new PrintStream("/Users/balaudt/Temp/apr/exp-act.out"))
    val t = lineIt.next().toInt
    for (i <- 1 to t) {
      val n = lineIt.next().toInt
      val tree = new DelegateTree[Int, Tuple2[Int, Int]]()
      tree.setRoot(0)
      for (j <- 1 until n) {
        val uvw = lineIt.next().split(" ").map(_.toInt)
        val (u, v, w) = (uvw(0) - 1, uvw(1) - 1, uvw(2))
        tree.addChild((j, w), u, v)
      }
      //      GraphUtil.printTree(tree, new TreeLayout[Int, Tuple2[Int, Int]](tree))
//      GraphUtil.visualize(new TreeLayout[Int, Tuple2[Int, Int]](tree), null)

      implicit def ordering[A <: Number]: Ordering[Number] = Ordering.by(_.intValue())
      val nodesByDepth = new HashMap[Int, Set[Int]] with MultiMap[Int, Int]
      tree.getVertices.asScala.foreach(x => nodesByDepth.addBinding(tree.getDepth(x), x))
      val graph = new UndirectedSparseGraph[Int, Tuple2[Int, Int]]()
      val ans = new Array[Int](n)
      val edgeTransformer = new Transformer[Tuple2[Int, Int], Integer] {
        override def transform(i: (Int, Int)): Integer = i._2
      }
      for (j <- nodesByDepth.keySet.max to 0 by -1) {
        for (u <- nodesByDepth.get(j).get) {
          graph.addVertex(u)
          tree.getChildEdges(u).asScala.foreach(v => {
            graph.addEdge(v, u, tree.getOpposite(u, v))
          })

          val dijkstra = new DijkstraDistance[Int, Tuple2[Int, Int]](graph, edgeTransformer)
          var stratCost = dijkstra.getDistanceMap(u).values().asScala.max.intValue()
          graph.getVertices.asScala.filter(tree.getPath(_).contains(u)).foreach(subNode => {
            val cost = dijkstra.getDistanceMap(subNode).values().asScala.max.intValue()
            if (cost < stratCost)
              stratCost = cost
          })
          ans(u) = stratCost
        }
      }
      println(ans.mkString("\n"))
    }
  }

  gen
  brute
}
