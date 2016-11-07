package zi.chef.y16.feb

import scala.collection.mutable

/**
  * Created by balaudt on 9/9/16.
  */
object DivMac extends App {
  val smallPrimes = Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
  val primes = Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
    103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
    233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373,
    379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521,
    523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673,
    677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839,
    853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997)

  case class Factor(fact: Int, var pow: Int) extends Ordered[Factor] {
    override def compare(that: Factor): Int = that.fact compare fact
  }

  val semiPrimes = (for {i <- primes
                         j <- primes} yield {
    val out = mutable.PriorityQueue[Factor]()
    if (i == j) out += Factor(i, 2)
    else {
      out += Factor(i, 1)
      out += Factor(j, 1)
    }
    i * j -> out
  }).toMap

  val memo = mutable.HashMap[Int, mutable.PriorityQueue[Factor]]()

  def factorize(num: Int): mutable.PriorityQueue[Factor] = {
    if (memo.contains(num))
      return memo(num).clone()
    semiPrimes.get(num) match {
      case Some(x) => x.clone()
      case None => {
        val out = mutable.PriorityQueue[Factor]()
        var (n, ct) = (num, -1)
        for (smallPrime <- smallPrimes) {
          if (n % smallPrime == 0) {
            ct = 0
            while (n % smallPrime == 0) {
              ct += 1
              n /= smallPrime
            }
            out += Factor(smallPrime, ct)
          }
        }
        if (n > 1)
          out += Factor(n, 1)
        memo += num -> out
        out
      }
    }
  }


  val lines = scala.io.Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nm = lines.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val tree = new LazySegmentTree[(Int, mutable.PriorityQueue[Factor]), Int, Int](
      lines.next().split(" ").map(x => (x.toInt, factorize(x.toInt))),
      x => if (x._2.isEmpty) 1 else x._2.head.fact,
      (x, y) => Math.max(x, y),
      (l, u) => {
        if (l._2.nonEmpty) {
          val factor = l._2.head
          factor.pow -= 1
          if (factor.pow == 0) l._2.dequeue()
        }
        l
      },
      1
    )
    for (j <- 1 to m) {
      val (olr) = lines.next().split(" ").map(_.toInt)
      val (op, l, r) = (olr(0), olr(1), olr(2))
      op match {
        case 0 => tree.lazyUpdate(l - 1, r, j)
        case 1 => print(s"${tree.query(l - 1, r)} ")
      }
    }
    //    tree.printTree()
  }

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

    class Node[D](var data: D, val left: Node[D] = null, val right: Node[D] = null, var parent: Node[D] = null) {
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
  }

}
