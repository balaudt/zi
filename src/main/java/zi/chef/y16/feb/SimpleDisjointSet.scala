package zi.chef.y16.feb

import scala.collection.mutable

/**
  * Created by balaudt on 8/25/16.
  */
class SimpleDisjointSet[T] {

  case class Node[T](var parent: T, var rank: Int)

  val lookup = mutable.HashMap[T, Node[T]]()

  def makeSet(in: T) = lookup += in -> Node(in, 0)

  def union(l: T, r: T): Unit = {
    val (lRep, rRep) = (find(l), find(r))
    if (lRep == rRep) return
    val (lInfo, rInfo) = (lookup(lRep), lookup(rRep))
    if (lInfo.rank > rInfo.rank)
      rInfo.parent = lRep
    else {
      lInfo.parent = rRep
      if (lInfo.rank == rInfo.rank)
        rInfo.rank += 1
    }
  }

  def find(in: T): T = {
    val inRep = lookup(in)
    if (!inRep.parent.equals(in))
      inRep.parent = find(inRep.parent)
    inRep.parent
  }
}
