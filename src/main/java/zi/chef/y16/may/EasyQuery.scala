package zi.chef.y16.may

import scala.collection.mutable.ArrayBuffer
import scala.io

/**
  * Created by balaudt on 5/15/16.
  */
object EasyQuery extends App {

  class SegTree(a: Array[Int]) {
    val size = a.length
    val hgt = java.lang.Math.ceil(java.lang.Math.log(size) / java.lang.Math.log(2)).toInt
    var maxSize = 1 << (hgt + 1)
    val tree = Array.ofDim[ArrayBuffer[Int]](maxSize + 1)
    val rangeTree = Array.ofDim[(Int, Int)](maxSize + 1)
    maxSize /= 2
    maxSize -= 1
    for (i <- maxSize until maxSize + size) {
      tree(i) = ArrayBuffer(a(i - maxSize))
      rangeTree(i) = (i - maxSize, i - maxSize)
    }
    for (depth <- hgt - 1 to 0 by -1) {
      for (ind <- (1 << depth) - 1 to (1 << depth + 1) - 2) {
        val leftNode = tree(ind * 2 + 1)
        val rightNode = tree(ind * 2 + 2)
        if (leftNode != null) {
          if (rightNode == null) {
            tree(ind) = ArrayBuffer() ++ leftNode
            rangeTree(ind) = (rangeTree(ind * 2 + 1)._1, rangeTree(ind * 2 + 1)._2)
          } else {
            rangeTree(ind) = (rangeTree(ind * 2 + 1)._1, rangeTree(ind * 2 + 2)._2)
            var leftInd = 0
            var rightInd = 0
            val leftSize = leftNode.size
            val rightSize = rightNode.size
            tree(ind) = ArrayBuffer()
            if (leftNode(0) < rightNode(0)) {
              tree(ind) += leftNode(0)
              leftInd = 1
            }
            else {
              tree(ind) += rightNode(0)
              rightInd = 1
            }
            var lastNum = tree(ind)(0)
            while (leftInd < leftSize && rightInd < rightSize) {
              if (leftNode(leftInd) < rightNode(rightInd)) {
                if (leftNode(leftInd) != lastNum) {
                  tree(ind) += leftNode(leftInd)
                  lastNum = leftNode(leftInd)
                }
                leftInd += 1
              } else {
                if (rightNode(rightInd) != lastNum) {
                  tree(ind) += rightNode(rightInd)
                  lastNum = rightNode(rightInd)
                }
                rightInd += 1
              }
            }
            while (leftInd < leftSize) {
              if (leftNode(leftInd) != lastNum) {
                tree(ind) += leftNode(leftInd)
                lastNum = leftNode(leftInd)
              }
              leftInd += 1
            }
            while (rightInd < rightSize) {
              if (rightNode(rightInd) != lastNum) {
                tree(ind) += rightNode(rightInd)
                lastNum = rightNode(rightInd)
              }
              rightInd += 1
            }
          }
        }
      }
    }

    //    println(tree.mkString("\n"))

    def query(l: Int, r: Int, k: Int): Int = {
      var leftParent = (l + maxSize - 1) / 2
      var rightParent = (r + maxSize - 1) / 2
      while (leftParent != rightParent) {
        leftParent = (leftParent - 1) / 2
        rightParent = (rightParent - 1) / 2
      }
      query(leftParent, l, r, k)
    }

    def query(node: Int, l: Int, r: Int, k: Int): Int = {
      if (l == r)
        if (k == 1 && tree(node) != null)
          return tree(node)(0)
        else
          return -1
      val range = rangeTree(node)
      if (range == null)
        return -1
      if (range._1 >= l && range._2 <= r)
        if (tree(node) != null && tree(node).size >= k)
          return tree(node)(k - 1)
        else
          return -1
      if (l > range._2 || r < range._1)
        return -1

      val leftCount = count(node * 2 + 1, l, r)
      if (k <= leftCount)
        return query(node * 2 + 1, l, r, k)
      else
        return query(node * 2 + 2, l, r, k - leftCount)
    }

    def count(node: Int, l: Int, r: Int): Int = {
      val range = rangeTree(node)
      if (range == null)
        return 0
      if (l > range._2 || r < range._1)
        return 0
      if (range._1 >= l && range._2 <= r)
        return tree(node).size
      return count(node * 2 + 1, l, r) + count(node * 2 + 2, l, r)
    }
  }

  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val nq = lineIt.next().split(" ").map(_.toInt)
  val (n, q) = (nq(0), nq(1))
  val a = lineIt.next().split(" ").map(_.toInt)
  val s = new SegTree(a)
  var ans = 0
  for (i <- 1 to q) {
    val q = lineIt.next().split(" ").map(_.toInt)
    val (a, b, c, d, k) = (q(0), q(1), q(2), q(3), q(4))
    val l = (a * java.lang.Math.max(0, ans) + b) % n + 1
    val r = (c * java.lang.Math.max(0, ans) + d) % n + 1
    ans = s.query(l - 1, r - 1, k)
    println(ans)
  }
}
