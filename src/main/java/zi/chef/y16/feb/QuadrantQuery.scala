package zi.chef.y16.feb

import java.io.PrintStream

import edu.uci.ics.jung.algorithms.layout.TreeLayout
import edu.uci.ics.jung.graph.DelegateTree
import zi.common.GraphUtil

import scala.collection.mutable

/**
  * Created by balaudt on 9/10/16.
  */
object QuadrantQuery extends App {

  class LazySegmentTree[L, I, U](initData: Array[L], map: L => I, reduce: (I, I) => I, updater: (L, U) => L, unit: I) {
    val size = initData.length

    sealed trait Data {
      def getValue = {
        this match {
          case x: Leaf => map(x.data)
          case x: Internal => x.data
        }
      }

      override def toString: String = this match {
        case x: Leaf => x.data.toString
        case x: Internal =>
          val dataStr = x.data match {
            case y: Array[_] => y.mkString(" ")
            case y => y.toString
          }
          s"${x.l}:${x.r}"
      }
    }

    class Leaf(val p: Int, var data: L) extends Data

    class Internal(val l: Int, val r: Int, var data: I, val pendingUpdates: mutable.HashSet[U] = mutable.HashSet[U](),
                   val descendantPendingUpdates: mutable.HashSet[U] = mutable.HashSet[U]()) extends Data

    class Node[D](var data: D, val left: Node[D] = null, val right: Node[D] = null, var parent: Node[D] = null) {
      override def toString: String = data.toString
    }

    //Key: update to be propagated; Value: All nodes for which this update has to be propagated completely
    val pendingUpdatesWithNodes = mutable.HashMap[U, mutable.HashSet[Node[Data]]]()
    val consistencyCheck = mutable.HashSet[(Node[Data], U)]()

    def construct(data: Array[L], st: Int, end: Int): Node[Data] = {
      if (end - st == 1)
        return new Node[Data](new Leaf(st, data(st)))
      val mid = calcMid(st, end)
      val left = construct(data, st, mid)
      val right = construct(data, mid, end)
      val node = new Node[Data](new Internal(st, end, reduce(left.data.getValue, right.data.getValue)), left, right)
      left.parent = node
      right.parent = node
      node
    }

    def calcMid(st: Int, end: Int) = (st + end) / 2

    val root = construct(initData, 0, size)

    def lazyUpdate(st: Int, end: Int, update: U) = {
      pendingUpdatesWithNodes += update -> mutable.HashSet[Node[Data]]()
      lazyUpdateRecurse(root, 0, size, st, end, update)
      if (pendingUpdatesWithNodes(update).isEmpty) pendingUpdatesWithNodes -= update
      else {
        pendingUpdatesWithNodes(update).foreach(n => {
          var (p, flag) = (n.parent, true)
          while (p != null) {
            val internal = p.data.asInstanceOf[Internal]
            if (internal.descendantPendingUpdates.contains(update)) flag = false
            else internal.descendantPendingUpdates += update
            p = p.parent
          }
        })
      }
//            println(pendingUpdatesWithNodes)
//                  printPending(root)
      //      println(isConsistent(root))
      //      println(pendingUpdatesConsistent())
      //      println(dpuConsistent())
//                  println("_________")
    }

    def lazyUpdateRecurse(node: Node[Data], nodeSt: Int, nodeEnd: Int, st: Int, end: Int, updateData: U): Unit = {
      node.data match {
        case x: Leaf =>
          if (consistencyCheck.contains((node, updateData))) {
            println(s"Duplicate update $nodeSt $updateData")
            throw new Exception("Duplicate update")
          }
          x.data = updater(x.data, updateData)
          val p = node.parent.data.asInstanceOf[Internal]
          p.data = reduce(node.parent.left.data.getValue,node.parent.right.data.getValue)
          consistencyCheck += Tuple2(node, updateData)
//                          println(s"Direct update ${nodeSt}")
        case x: Internal =>
          if (st == nodeSt && nodeEnd == end) {
            x.pendingUpdates += updateData
            pendingUpdatesWithNodes(updateData) += node
            return
          }
          val mid = calcMid(nodeSt, nodeEnd)
          if (st < mid)
            lazyUpdateRecurse(node.left, nodeSt, mid, st, Math.min(mid, end), updateData)
          if (mid < end)
            lazyUpdateRecurse(node.right, mid, nodeEnd, Math.max(mid, st), end, updateData)
      }
    }

    def query(st: Int, end: Int): I = {
      val out = queryRecurse(root, 0, size, st, end)
//            println(pendingUpdatesWithNodes)
//                  printPending(root)
      //      println(isConsistent(root))
      //      println(pendingUpdatesConsistent())
      //      println(dpuConsistent())
//                  println("_________")
      out
    }

    def queryRecurse(node: Node[Data], nodeSt: Int, nodeEnd: Int, st: Int, end: Int): I = {
//            println((nodeSt, nodeEnd, st, end))
      if (st == nodeSt && end == nodeEnd) {
        node.data match {
          case x: Leaf =>
//                        println(map(x.data).asInstanceOf[Array[Int]].mkString(" "))
            return map(x.data)
          case x: Internal =>
            if (x.pendingUpdates.isEmpty && x.descendantPendingUpdates.isEmpty) {
//                            println(x.data.asInstanceOf[Array[Int]].mkString(" "))
              return x.data
            }
            propagateUpdates(node)
            val mid = calcMid(nodeSt, nodeEnd)
            val l = queryRecurse(node.left, nodeSt, mid, st, Math.min(mid, end))
            val r = queryRecurse(node.right, mid, nodeEnd, Math.max(mid, st), end)
            x.data = reduce(l, r)
//                        println(reduce(l, r).asInstanceOf[Array[Int]].mkString(" "))
            return reduce(l, r)
        }
      }
      propagateUpdates(node)
      val mid = calcMid(nodeSt, nodeEnd)
      var (l, r) = (unit, unit)
      if (st < mid)
        l = queryRecurse(node.left, nodeSt, mid, st, Math.min(mid, end))
      if (mid < end)
        r = queryRecurse(node.right, mid, nodeEnd, Math.max(mid, st), end)
//            println(reduce(l, r).asInstanceOf[Array[Int]].mkString(" "))
      reduce(l, r)
    }

    def propagateUpdates(node: Node[Data]): Unit = {
      val x = node.data.asInstanceOf[Internal]
//      println(s"Propagating updates ${x.l}:${x.r}")
      x.pendingUpdates.foreach(u => {
        pendingUpdatesWithNodes(u) -= node
        var flag = true
        Array(node.left, node.right).foreach(n => {
          n.data match {
            case y: Leaf =>
              if (consistencyCheck.contains((n, u))) {
                println(s"Duplicate update ${y.p} $u")
                throw new Exception("Duplicate update")
              }
              y.data = updater(y.data, u)
              consistencyCheck += Tuple2(n, u)
            case y: Internal =>
              y.pendingUpdates += u
              pendingUpdatesWithNodes(u) += n
              flag = false
          }
        })
        if (!flag) x.descendantPendingUpdates += u
        else {
          x.data = reduce(node.left.data.getValue, node.right.data.getValue)
          var (n, p) = (node, node.parent)
          while (p != null && flag) {
            val internal = p.data.asInstanceOf[Internal]
            val sibling = Array(p.left, p.right).filter(_ != n).head
            sibling.data match {
              case c: Leaf =>
              case c: Internal => if (c.descendantPendingUpdates.contains(u) || c.pendingUpdates.contains(u)) flag = false
            }
            if (flag) {
              internal.descendantPendingUpdates -= u
              internal.data = reduce(p.left.data.getValue, p.right.data.getValue)
            }
            n = p
            p = p.parent
          }
        }
        if (pendingUpdatesWithNodes(u).isEmpty) pendingUpdatesWithNodes -= u
      })
      x.pendingUpdates.clear()
    }

    def printPending(node: Node[Data]): Unit = {
      if (node.data.isInstanceOf[Leaf])
        return
      val internal = node.data.asInstanceOf[Internal]
      val updates = internal.descendantPendingUpdates
      val pending = internal.pendingUpdates
      if (updates.nonEmpty || pending.nonEmpty) println(s"${internal.l}:${internal.r}=>$updates & $pending")
      Array(node.left, node.right).foreach(printPending)
    }

    def pendingUpdatesConsistent(): Boolean = {
      pendingUpdatesWithNodes.foreach(u => {
        if (u._2.isEmpty) {
          println(s"MPU - $u")
          return false
        }
      })
      true
    }

    def isConsistent(node: Node[Data]): Boolean = {
      node.data match {
        case x: Leaf => true
        case x: Internal =>
          x.descendantPendingUpdates.foreach(u => {
            var updateValid = false
            Array(node.left, node.right).foreach(c => {
              c.data match {
                case y: Leaf =>
                case y: Internal => if (y.descendantPendingUpdates.contains(u) || y.pendingUpdates.contains(u)) updateValid = true
              }
            })
            if (!updateValid) {
              println(s"DPU - ${x.l}:${x.r}#$u")
              return false
            }
          })
          x.pendingUpdates.foreach(u => if (!pendingUpdatesWithNodes(u).contains(node)) {
            println(s"PU - ${x.l}:${x.r}#$u")
            return false
          })
          isConsistent(node.left) && isConsistent(node.right)
      }
    }

    def dpuConsistent(): Boolean = {
      pendingUpdatesWithNodes.foreach(pu => {
        pu._2.foreach(node => {
          var (n, p) = (node.data.asInstanceOf[Internal], node.parent)
          while (p != null) {
            val pi = p.data.asInstanceOf[Internal]
            if (!pi.descendantPendingUpdates.contains(pu._1)) {
              println(s"DPU missing in ${pi.l}:${pi.r} for ${pu._1} present in ${n.l}:${n.r}")
              return false
            }
            p = p.parent
          }
        })
      })
      true
    }

    var edCt = 0

    def traverse(g: DelegateTree[Data, Int], node: Node[Data]): Unit = {
      if (node.data.isInstanceOf[Leaf])
        return
      Array(node.left, node.right).foreach(n => {
        edCt += 1
        g.addChild(edCt, node.data, n.data)
        traverse(g, n)
      })
    }

    def printTree() = {
      val g = new DelegateTree[Data, Int]()
      g.addVertex(root.data)
      edCt = 0
      traverse(g, root)
      GraphUtil.visualize(new TreeLayout[Data, Int](g), null)
    }

  }

  case class Point(var x: Int, var y: Int) {
    override def toString: String = s"($x,$y)"
  }

  case class Update(var axis: Char, id: Int) {
    override def toString: String = s"$id.$axis"
  }

  def quad(p: Point) =
    if (p.x > 0 && p.y > 0) 1
    else if (p.x > 0) 4
    else if (p.y > 0) 2
    else 3

  def solve(num: Int) = {
    //    val lines = scala.io.Source.fromInputStream(System.in).getLines()
    val lines = scala.io.Source.fromFile(s"/Users/balaudt/Temp/qq-gen${num}.in").getLines()
    val stream = new PrintStream(s"/Users/balaudt/Temp/qq-gen${num}.out")
    System.setOut(stream)
    val n = lines.next().toInt

    val pts = Array.ofDim[Point](n)
    for (i <- 0 until n) {
      pts(i) = Point(0, 0)
      val pt = lines.next().split(" ").map(_.toInt)
      pts(i).x = pt(0)
      pts(i).y = pt(1)
    }
    val tree = new LazySegmentTree[Point, Array[Int], Update](
      pts,
      pt => {
        val out = Array.ofDim[Int](4)
        out(quad(pt) - 1) = 1
        out
      },
      (x, y) => {
        val out = Array.ofDim[Int](4)
        for (i <- 0 to 3) out(i) = x(i) + y(i)
        out
      },
      (pt, u) => {
        if (u.axis == 'X') pt.y = -pt.y
        else pt.x = -pt.x
        pt
      },
      Array(0, 0, 0, 0)
    )

    val q = lines.next().toInt
    for (j <- 1 to q) {
              if (j == 1) tree.printTree()
      val tlr = lines.next().split(" ")
      val (op, l, r) = (tlr(0).toCharArray()(0), tlr(1).toInt, tlr(2).toInt)
      op match {
        case 'C' => stream.println(tree.query(l - 1, r).mkString(" "))
        case x => tree.lazyUpdate(l - 1, r, Update(x, j))
      }
//                  println(pts.mkString(" "))
    }
  }

//  for (i <- 1 to 100) solve(i)
  solve(4)
}
