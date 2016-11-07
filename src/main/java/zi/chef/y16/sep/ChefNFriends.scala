import java.io.PrintStream
import java.util

import scala.collection.mutable

/**
  * Created by chaba005 on 9/2/2016.
  */
object ChefNFriends extends App {
    val lines = scala.io.Source.fromInputStream(System.in).getLines()
//  val lines = scala.io.Source.fromFile("/Users/balaudt/Temp/cfc/cnf.in").getLines()
//    System.setOut(new PrintStream("/Users/balaudt/Temp/cfc/cnf.out"))
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nm = lines.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val adjMat = Array.fill(n)(new util.BitSet(n))
    for (j <- 0 until n) adjMat(j).set(j)
    for (j <- 1 to m) {
      val pair = lines.next().split(" ").map(_.toInt - 1)
      val (a, b) = (pair(0), pair(1))
      adjMat(a).set(b)
      adjMat(b).set(a)
    }
    val sortedMat = adjMat.zipWithIndex //.sortBy(_._1.cardinality())
    val sGrp = mutable.HashSet[Int]() ++ (0 until n)
    val oGrp = mutable.HashSet[Int]()
    for (ent <- sortedMat if sGrp.nonEmpty) {
      var (g1, g2) = if (sGrp.contains(ent._2)) (sGrp, oGrp) else (oGrp, sGrp)
      var (lastInd, ct) = (ent._2, 0)
      while (lastInd < n) {
        lastInd = ent._1.nextClearBit(lastInd + 1)
        if (lastInd < n) {
          g1 -= lastInd
          g2 += lastInd
        }
      }
//      println(s"${ent._2} $sGrp $oGrp")
    }
    println(if (isCompleteSubgraph(sGrp) && isCompleteSubgraph(oGrp)) "YES" else "NO")

    def isCompleteSubgraph(grp: mutable.HashSet[Int]): Boolean = {
      for (u <- grp) for (v <- grp) if (!adjMat(u).get(v)) return false
      true
    }
  }
}
