package zi.chef.y16.feb

import edu.uci.ics.jung.algorithms.layout.TreeLayout
import edu.uci.ics.jung.graph.DelegateTree
import zi.common.GraphUtil

import scala.collection.mutable


/**
  * Created by balaudt on 9/9/16.
  */
//L - Leaf node type; I - Internal node type; U - Update data to be propagated
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
      case x: Internal => s"${x.data},pu=${x.pendingUpdates},dpu=${x.descendantPendingUpdates}"
    }
  }

  class Leaf(var data: L) extends Data

  class Internal(var data: I, val pendingUpdates: mutable.HashSet[U] = mutable.HashSet[U](),
                 val descendantPendingUpdates: mutable.HashSet[U] = mutable.HashSet[U]()) extends Data

  class Node[D](var data: D, val left: Node[D] = null, val right: Node[D] = null, var parent: Node[D] = null){
    override def toString: String = data.toString
  }

  //Key: update to be propagated; Value: All nodes for which this update has to be propagated completely
  val pendingUpdatesWithNodes = mutable.HashMap[U, mutable.HashSet[Node[Data]]]()

  def construct(data: Array[L], st: Int, end: Int): Node[Data] = {
    if (end - st == 1)
      return new Node[Data](new Leaf(data(st)))
    val mid = calcMid(st, end)
    val left = construct(data, st, mid)
    val right = construct(data, mid, end)
    val node = new Node[Data](new Internal(reduce(left.data.getValue, right.data.getValue)), left, right)
    left.parent = node
    right.parent = node
    node
  }

  def calcMid(st: Int, end: Int) = (st + end) / 2

  val root = construct(initData, 0, size)

  def lazyUpdate(st: Int, end: Int, update: U) = {
    pendingUpdatesWithNodes += update -> mutable.HashSet[Node[Data]]()
    lazyUpdateRecurse(root, 0, size, st, end, update)
  }

  def lazyUpdateRecurse(node: Node[Data], nodeSt: Int, nodeEnd: Int, st: Int, end: Int, updateData: U): Unit = {
    //    println((nodeSt, nodeEnd, st, end, updateData))
    node.data match {
      case x: Leaf => x.data = updater(x.data, updateData)
      case x: Internal =>
        if (st == nodeSt && end == nodeEnd) {
          x.pendingUpdates += updateData
          pendingUpdatesWithNodes(updateData) += node
          return
        }
        x.descendantPendingUpdates += updateData
        val mid = calcMid(nodeSt, nodeEnd)
        if (st < mid)
          lazyUpdateRecurse(node.left, nodeSt, mid, st, Math.min(mid, end), updateData)
        if (mid < end)
          lazyUpdateRecurse(node.right, mid, nodeEnd, Math.max(mid, st), end, updateData)
    }
  }

  def query(st: Int, end: Int): I = {
    queryRecurse(root, 0, size, st, end)
  }

  def queryRecurse(node: Node[Data], nodeSt: Int, nodeEnd: Int, st: Int, end: Int): I = {
//    println((nodeSt, nodeEnd, st, end))
//    println(pendingUpdatesWithNodes)
    if (st == nodeSt && end == nodeEnd) {
      node.data match {
        case x: Leaf => return map(x.data)
        case x: Internal =>
          if (x.pendingUpdates.isEmpty && x.descendantPendingUpdates.isEmpty) return x.data
          propagateUpdates(node)
          val mid = calcMid(nodeSt, nodeEnd)
          val l = queryRecurse(node.left, nodeSt, mid, st, mid)
          val r = queryRecurse(node.right, mid, nodeEnd, st, end)
          return reduce(l, r)
      }
    }
    if (nodeEnd - nodeSt == 1)
      return node.data.getValue
    propagateUpdates(node)
    val mid = calcMid(nodeSt, nodeEnd)
    var (l, r) = (unit, unit)
    if (st < mid)
      l = queryRecurse(node.left, nodeSt, mid, st, Math.min(mid, end))
    if (mid < end)
      r = queryRecurse(node.right, mid, nodeEnd, Math.max(mid, st), end)
    reduce(l, r)
  }

  def propagateUpdates(node: Node[Data]): Unit = {
    val x = node.data.asInstanceOf[Internal]
    x.pendingUpdates.foreach(u => {
      pendingUpdatesWithNodes(u) -= node
      x.descendantPendingUpdates += u
      Array(node.left, node.right).foreach(n => {
        n.data match {
          case x: Leaf =>
            x.data = updater(x.data, u)
          case x: Internal =>
            x.pendingUpdates += u
            pendingUpdatesWithNodes(u) += n
        }
      })
      if (pendingUpdatesWithNodes(u).isEmpty) {
        pendingUpdatesWithNodes -= u
        var n = node
        while (n != null) {
          n.data.asInstanceOf[Internal].descendantPendingUpdates -= u
          n = n.parent
        }
      }
    })
    x.pendingUpdates.clear()
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
