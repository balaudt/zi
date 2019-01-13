package zi.jam.y17.qual

import edu.uci.ics.jung.graph.{DelegateTree, Tree}

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balamurugan on 4/7/17.
  */
object PancakeSmall extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val sk = lines.next().split(" ")
    val (s, k) = (Integer.parseInt(new String(sk(0).toCharArray.map(x => if (x == '-') '0' else '1')), 2), sk(1).toInt)
    val n = sk(0).length
    val dest = (1 << n) - 1
    if (s == dest) {
      println(s"Case #$i: 0")
    }
    else {
      val flipper = (1 << k) - 1
      var q = mutable.Queue[Int]()
      q += s
      var (found, flips) = (false, 0)
      val visited = mutable.HashSet[Int]()
      visited += s
      val tree = new DelegateTree[Int, Int]()
      tree.addVertex(s)
      var ec = 0
      while (!found && q.nonEmpty) {
        //        println(q)
        val next = mutable.Queue[Int]()
        for (num <- q if !found) {
          for (st <- 0 to n - k) {
            val flipped = num ^ (flipper << st)
            if (!visited.contains(flipped)) {
              tree.addEdge(ec, num, flipped)
              ec += 1
              if (flipped == dest) {
                found = true
                printPath(tree, n, k, dest)
              } else {
                visited += flipped
                next += flipped
              }
            }
          }
        }
        q = next
        flips += 1
      }
      if (found) println(s"Case #$i: $flips")
      else println(s"Case #$i: IMPOSSIBLE")
    }
  }

  def printPath(t: Tree[Int, Int], n: Int, k: Int, dest: Int) = {
    println(k)

    def lead0(num: Int): String = {
      val s = Integer.toBinaryString(num)
      if (s.length == n) return s
      new String(Array.fill(n - s.length)('0')) + s
    }

    val source = t.getRoot
    var node = dest
    while (node != source) {
      print(s"${lead0(node)} <- ")
      node = t.getParent(node)
    }
    println(lead0(source))
  }
}
