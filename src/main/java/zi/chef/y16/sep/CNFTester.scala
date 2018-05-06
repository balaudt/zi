import java.io.{BufferedWriter, FileWriter, PrintStream}

import scala.io.Source
import java.util

import edu.uci.ics.jung.graph.UndirectedSparseGraph
import zi.common.archive.GraphUtil

import scala.collection.mutable

/**
  * Created by chaba005 on 9/2/2016.
  */
object CNFTester extends App {
  def genIn = {
    val w = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/cfc/cnf.in"))
    val lines = Source.fromFile("/Users/balaudt/Temp/cfc/allgraphs.out").getLines()
    w.write("12346\n")
    for (i <- 1 to 12346) {
      val b = new StringBuilder()
      var ct = 0
      for (j <- 0 until 8) {
        lines.next().substring(6).init.split(" ").filter(!_.isEmpty).map(_.toInt).filter(_ > j).foreach(x => {
          ct += 1
          b.append(s"${j + 1} ${x + 1}\n")
        })
      }
      b.insert(0, s"8 $ct\n")
      w.write(b.toString())
    }
    w.close()
  }

  val lines = scala.io.Source.fromFile("/Users/balaudt/Temp/cfc/sin.in").getLines()
  //  System.setOut(new PrintStream("/Users/balaudt/Temp/cfc/cnf.brute.out"))
  val t = lines.next().toInt
  for (i <- 1 to t) {
    val nm = lines.next().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val adjMat = Array.fill(n)(new util.BitSet(n))
    val g = new UndirectedSparseGraph[Int, Int]()
    for (j <- 0 until n) {
      adjMat(j).set(j)
      g.addVertex(j)
    }
    var edCt = 0
    for (j <- 1 to m) {
      val pair = lines.next().split(" ").map(_.toInt - 1)
      val (a, b) = (pair(0), pair(1))
      adjMat(a).set(b)
      adjMat(b).set(a)
      g.addEdge(edCt, a, b)
      edCt += 1
    }
//    GraphUtil.printTree(g)
    var isPossible = false
    for (j <- 0 until 256 if !isPossible) {
      val sGrp = mutable.HashSet[Int]()
      val oGrp = mutable.HashSet[Int]()
      val comb = Array.fill(8)('0')
      System.arraycopy(Integer.toBinaryString(j).toCharArray, 0, comb, 0, Integer.toBinaryString(j).length)
      comb.zipWithIndex.foreach(x => if (x._1 == '0') sGrp += x._2 else oGrp += x._2)
      isPossible = isCompleteSubgraph(sGrp) && isCompleteSubgraph(oGrp)
      if (isPossible) println(s"$sGrp $oGrp")
    }
    println(if (isPossible) "YES" else "NO")

    def isCompleteSubgraph(grp: mutable.HashSet[Int]): Boolean = {
      for (u <- grp) for (v <- grp) if (!adjMat(u).get(v)) return false
      true
    }
  }
}
