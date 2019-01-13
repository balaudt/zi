package zi.chef.y16.feb

import edu.uci.ics.jung.algorithms.layout.KKLayout

import scala.collection.mutable.ArrayBuffer
import edu.uci.ics.jung.graph.DirectedSparseGraph
import zi.common.archive.GraphUtil

/**
  * Created by balaudt on 8/26/16.
  */
object EdmondKarp extends App {

  case class Edge(c: Int, var f: Int, u: Int, v: Int) {
    override def toString: String = if (f == 0) c.toString else s"$f/$c"
  }

  case class Vertex(ind: Int, edges: ArrayBuffer[Edge]) {
    override def toString: String = ind.toString
  }

  case class Graph(vertices: Array[Vertex], edges: ArrayBuffer[Edge], s: Int, t: Int)

  val inEdges = ArrayBuffer[Edge](Edge(16, 11, 0, 1), Edge(12, 12, 1, 3),
    Edge(13, 8, 0, 2), Edge(4, 1, 2, 1), Edge(9, 4, 3, 2), Edge(14, 11, 2, 4),
    Edge(7, 7, 4, 3), Edge(4, 4, 4, 5), Edge(20, 15, 3, 5))
  val inVertices = Array.ofDim[Vertex](6)
  for (i <- inVertices.indices) inVertices(i) = Vertex(i, inEdges.filter(_.u == i))
  val inGraph = Graph(inVertices, inEdges, 0, 5)
  //  print(inGraph)
  print(residualNetwork(inGraph))
  println(inGraph)

  def residualNetwork(g: Graph) = {
    val edges = ArrayBuffer[Edge]()
    val vertices = Array.ofDim[Vertex](g.vertices.length)
    for (i <- g.vertices.indices) vertices(i) = g.vertices(i).copy()
    for (edge <- g.edges) {
      if (edge.c > edge.f)
        edges += Edge(edge.c - edge.f, 0, edge.u, edge.v)
      val reverseEdge = Edge(edge.f, 0, edge.v, edge.u)
      edges += reverseEdge
      vertices(edge.v).edges += reverseEdge
    }
    Graph(vertices, edges, g.s, g.t)
  }

  def print(g: Graph) = {
    val jg = new DirectedSparseGraph[Vertex, Edge]()
    g.vertices.foreach(jg.addVertex(_))
    g.edges.foreach(e => jg.addEdge(e, g.vertices(e.u), g.vertices(e.v)))
    GraphUtil.visualize(new KKLayout[Vertex, Edge](jg))
  }
}
