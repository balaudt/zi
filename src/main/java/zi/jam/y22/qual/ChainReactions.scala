package zi.jam.y22.qual

import java.util
import scala.collection.mutable
import scala.io.Source

object ChainReactions {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val n = lines.next().toInt
      val f = lines.next().split(" ").map(_.toInt)
      val p = lines.next().split(" ").map(_.toInt - 1)
      val nodeChildMap = p.zipWithIndex.groupBy(_._1).mapValues(_.map(_._2))

      def traverse(node: Int): Long = {
        if (!nodeChildMap.contains(node)) {
          return f(node)
        }
        val childFuncs = nodeChildMap(node).map(traverse)
        var result = childFuncs.sum
        if(childFuncs.min < f(node))
          result = result - childFuncs.min + f(node)
        result
      }

      val result = nodeChildMap(-1).map(traverse).sum
      println(s"Case #$i: $result")
    }
  }

}
