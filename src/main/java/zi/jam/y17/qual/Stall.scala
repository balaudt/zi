package zi.jam.y17.qual

import java.util

import org.psjava.ds.array.MutableArrayFactory
import org.psjava.ds.tree.segmenttree.{EnhancedRangeUpdatableSegmentTreeOperator, LazyPropagatingSegmentTree}

import scala.io.Source

/**
  * Created by balamurugan on 4/8/17.
  */
object Stall extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val t = lines.next().toInt
  for (j <- 1 to t) {
    val nk = lines.next().split(" ").map(_.toInt)
    val (n, k) = (nk(0), nk(1) - 1)
    val filled = new util.TreeSet[Int]()
    filled.add(0)
    filled.add(n + 1)

    val mutableArray = MutableArrayFactory.create(n + 2, Array.ofDim[Int](3))
    var (initL, initR) = (0, n - 1)
    mutableArray.set(0, Array(-1, -1, 0))
    mutableArray.set(n + 1, Array(-1, -1, n + 1))

    for (i <- 1 to n) {
      mutableArray.set(i, Array(initL, initR, i))
      initL += 1
      initR -= 1
    }
    val tree = new LazyPropagatingSegmentTree[Array[Int], Array[Int]](mutableArray, new EnhancedRangeUpdatableSegmentTreeOperator[Array[Int], Array[Int]] {
      override def mergeSingleValue(v1: Array[Int], v2: Array[Int]): Array[Int] = {
        val (min1, max1) = (Math.min(v1(0), v1(1)), Math.max(v1(0), v2(1)))
        val (min2, max2) = (Math.min(v2(0), v2(1)), Math.max(v2(0), v2(1)))
        val res = if (min1 > min2) v1
        else if (min1 < min2) return v2
        else if (max1 > max2) return v1
        else if (max1 < max2) return v2
        else if (v1(2) < v2(2)) v1 else v2
        val out = Array.ofDim[Int](3)
        Array.copy(res, 0, out, 0, 3)
        out
      }

      override def mergeUpdateData(oldData: Array[Int], newData: Array[Int]): Array[Int] = {
        if (newData(0) == 0) oldData(0) = newData(1)
        else if (newData(0) == 1) oldData(1) = newData(1)
        else {
          oldData(0) = n
          oldData(1) = n
        }
        oldData
      }

      override def mergeRangeValue(oldRangeValue: Array[Int], rangeSize: Int, updateData: Array[Int]): Array[Int] = {
        if (updateData(0) == 0)
          oldRangeValue(0) = updateData(1)
        else
          oldRangeValue(1) = updateData(1)
        oldRangeValue
      }
    })

//    tree.visualize()
    /*for (i <- 0 to k) {
      val next = tree.queryRange(0, n)(2)
      filled.add(next)
      val lower = filled.lower(next)
      tree.updateRange(lower, next - 1, Array(0, next - lower - 1))
      val higher = filled.higher(next)
      tree.updateRange(next + 1, higher, Array(1, higher - next - 1))
      tree.updateRange(next, next, Array(2))
    }
    val out = tree.queryRange(0, n)
    println(s"Case #$j: ${out(0)} ${out(1)}")*/
  }

}
