package zi.chef.y17.jan.longg

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by balamurugan on 1/10/17.
  */
object Tourists extends App {

  case class Vertex(i: Int, adj: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer[Int]())

  val lines = Source.fromInputStream(System.in).getLines()
  val nm = lines.next().split(" ").map(_.toInt)
  val (n, m) = (nm(0), nm(1))
  val vertices = (0 until n).map(Vertex(_))
  val in = (1 to m).toArray.map(_ => {
    val uv = lines.next().split(" ").map(_.toInt)
    val (u, v) = (uv(0) - 1, uv(1) - 1)
    vertices(u).adj += v
    vertices(v).adj += u
    if (u > v) (v, u) else (u, v)
  })


  private def isConnected: Boolean = {
    val visited = mutable.HashSet[Int]()
    val q = mutable.Queue[Int]()
    q += 0
    while (q.nonEmpty) {
      val u = q.dequeue()
      visited += u
      q ++= vertices(u).adj.filter(!visited.contains(_))
    }
    visited.size == n
  }

  val circuit = ArrayBuffer[Int]()

  private def euler(v: Vertex): Unit = {
    //    println(v.i)
    //    println(vertices.mkString(","))
    while (v.adj.nonEmpty) {
      val u = v.adj.head
      v.adj -= u
      vertices(u).adj -= v.i
      euler(vertices(u))
    }
    circuit += v.i
  }

  private def nonRecursiveEuler(): ArrayBuffer[Int] = {
    val stack = mutable.Stack[Int]()
    stack.push(0)
    val circuit = new ArrayBuffer[Int]()
    while (stack.nonEmpty) {
      val v = stack.pop()
      if (vertices(v).adj.isEmpty) circuit += v
      else {
        val u = vertices(v).adj.head
        vertices(v).adj -= u
        vertices(u).adj -= v
        stack.push(v)
        stack.push(u)
      }
    }
    circuit
  }

  var isValid = true
  for (vertex <- vertices if isValid) if (vertex.adj.size % 2 != 0) isValid = false


  if (!isValid) println("NO")
  else {
    if (!isConnected) println("NO")
    else {
//      euler(vertices(0))
      val circuit = nonRecursiveEuler()
//            println(circuit)
      val dirs = mutable.HashMap[(Int, Int), mutable.ArrayBuffer[Boolean]]()
      for (i <- 0 until m) {
        val uv = (circuit(i), circuit(i + 1))
        val toPersist = if (uv._1 > uv._2) (uv.swap, mutable.ArrayBuffer(false)) else (uv, mutable.ArrayBuffer(true))
        dirs.get(toPersist._1) match {
          case Some(x) => x += toPersist._2.head
          case None => dirs += toPersist
        }
      }
      println("YES")
      in.foreach(uv => {
        val edgeDirs = dirs(uv)
        val oneDir = edgeDirs.head
        if (oneDir) println(s"${uv._1 + 1} ${uv._2 + 1}")
        else println(s"${uv._2 + 1} ${uv._1 + 1}")
        edgeDirs -= oneDir
      })
    }
  }
}
