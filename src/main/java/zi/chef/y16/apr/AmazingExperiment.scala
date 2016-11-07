package zi.chef.y16.apr

import java.io.PrintStream

import scala.collection.mutable._
import scala.io.Source
import scala.collection.JavaConverters._

/**
  *
  * Created by balaudt on 4/5/16.
  *
  */
object AmazingExperiment extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  //  val lineIt = Source.fromFile("/Users/balaudt/Temp/apr/exp.in").getLines
  //  System.setOut(new PrintStream("/Users/balaudt/Temp/apr/exp.out"))
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val n = lineIt.next().toInt
    if (n > 100)
      throw new UnsupportedOperationException()
    val nodes = new Array[Node](n)
    for (j <- 0 until n)
      nodes(j) = new Node(j)
    for (j <- 1 until n) {
      val uvw = lineIt.next().split(" ").map(_.toInt)
      val (u, v, w) = (uvw(0) - 1, uvw(1) - 1, uvw(2))
      nodes(u).children += v -> w
      nodes(v).children += u -> w
    }
    val bfsQueue = Queue[Int]()
    bfsQueue += 0
    val nodesByDepth = new HashMap[Int, Set[Node]] with MultiMap[Int, Node]
    nodes(0).depth = 1
    var treeDepth = 1
    while (!bfsQueue.isEmpty) {
      val cur = bfsQueue.dequeue()
      val curDepth = nodes(cur).depth
      treeDepth = curDepth
      nodesByDepth.addBinding(curDepth, nodes(cur))
      nodes(cur).children.foreach(x => {
        if (nodes(x._1).depth == 0) {
          nodes(x._1).children.remove(cur)
          nodes(x._1).depth = curDepth + 1
          bfsQueue += x._1
        }
      })
    }
    //nodesByDepth.foreach(x => { println(s"${x._1}\t${x._2.map(_.ind).mkString(",")}") })
    for (j <- treeDepth to 1 by -1) {
      nodesByDepth.get(j).get.foreach(root => {
        root.root = root.ind
        if (!root.children.isEmpty) {
          if (root.children.size == 1) {
            val (child, curWgt) = root.children.head
            root.curStratCost = curWgt + nodes(child).curStratCost
            root.subTreeStratCost = root.curStratCost
            val bfsQueue = new java.util.ArrayDeque[Int]()
            bfsQueue.add(child)
            while (!bfsQueue.isEmpty()) {
              val childNode = nodes(bfsQueue.poll())
              childNode.distToCurRoot += curWgt
              childNode.root = root.ind
              if (childNode.distToCurRoot > childNode.curStratCost)
                childNode.curStratCost = childNode.distToCurRoot;
              if (childNode.curStratCost < root.subTreeStratCost)
                root.subTreeStratCost = childNode.curStratCost
              bfsQueue.addAll(childNode.children.keySet.asJava)
            }
          } else {
            val twoFarSubTreeLeaves = new PriorityQueue[X]()(Ordering[X].reverse)
            val childIt = root.children.iterator
            var child = childIt.next()
            twoFarSubTreeLeaves += X(child._1, nodes(child._1).curStratCost + child._2)
            child = childIt.next()
            twoFarSubTreeLeaves += X(child._1, nodes(child._1).curStratCost + child._2)
            var wgt: Int = 0
            while (childIt.hasNext) {
              child = childIt.next()
              wgt = nodes(child._1).curStratCost + child._2
              if (twoFarSubTreeLeaves.head.wgt < wgt) {
                twoFarSubTreeLeaves.dequeue()
                twoFarSubTreeLeaves += X(child._1, wgt)
              }
            }
            root.curStratCost = twoFarSubTreeLeaves.max.wgt
            root.subTreeStratCost = root.curStratCost
            val topSubTree = twoFarSubTreeLeaves.toArray
            val bfsQueue = new java.util.ArrayDeque[Int]()
            bfsQueue.addAll(root.children.keySet.asJava)
            while (!bfsQueue.isEmpty()) {
              val childNode = nodes(bfsQueue.poll())
              childNode.distToCurRoot += root.children.get(childNode.root).get
              if (childNode.root == topSubTree(0).ind)
                wgt = topSubTree(1).wgt
              else if (childNode.root == topSubTree(1).ind)
                wgt = topSubTree(0).wgt
              else
                wgt = root.curStratCost
              childNode.root = root.ind
              if (childNode.curStratCost < wgt + childNode.distToCurRoot)
                childNode.curStratCost = wgt + childNode.distToCurRoot
              if (childNode.curStratCost < root.subTreeStratCost)
                root.subTreeStratCost = childNode.curStratCost
              bfsQueue.addAll(childNode.children.keySet.asJava)
            }
          }
        }
        //        println(s"root=${root.ind}\n${nodes.mkString("\n")}\n________________")
      })
    }
    for (j <- 0 until n) println(nodes(j).subTreeStratCost)
  }

  case class X(val ind: Int, val wgt: Int) extends Ordered[X] {
    def compare(that: X) = this.wgt - that.wgt
  }

  class Node(val ind: Int) {
    val children = HashMap[Int, Int]()
    var (depth, curStratCost, subTreeStratCost, distToCurRoot, root) = (0, 0, 0, 0, 0)

    override def toString() = {
      s"#${ind} [d=${depth} csc=${curStratCost} sTSC=${subTreeStratCost} dTR=${distToCurRoot} r=${root}]"
    }
  }

}
