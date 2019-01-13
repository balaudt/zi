package zi.chef.y17.jan.longg

import java.util

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by balamurugan on 1/10/17.
  */
object TouristsHelper extends App {

  case class Vertex(i: Int, adjs: util.HashSet[Int] = new util.HashSet[Int]())

  //  val lines = Source.fromInputStream(System.in).getLines()
  val lines = Source.fromFile("/Users/balamurugan/Temp/eul7c.e").getLines()
  val nm = lines.next().split(" ").map(_.toInt)
  val (n, m) = (nm(0), nm(1))
  val vertices = (0 to n).toArray.map(Vertex(_))
  lines.next().split("  ").foreach(edStr => {
    val uv = edStr.split(" ").map(_.toInt)
    val (u, v) = (uv(0), uv(1))
    vertices(u).adjs.add(v)
    vertices(v).adjs.add(u)
  })
  val circuit = ArrayBuffer[Int]()

  private def euler(v: Vertex): Unit = {
    //    println(v.i)
    //    println(vertices.mkString(","))
    while (!v.adjs.isEmpty) {
      val u = v.adjs.iterator().next()
      v.adjs.remove(u)
      vertices(u).adjs.remove(v.i)
      euler(vertices(u))
    }
    circuit += v.i
  }

  euler(vertices(0))
  println()
  println(circuit)
}
