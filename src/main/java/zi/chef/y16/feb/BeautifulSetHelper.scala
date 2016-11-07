package zi.chef.y16.feb

import org.paukov.combinatorics.Factory

import scala.collection.JavaConverters._
import scala.collection.mutable

/**
  * Created by balaudt on 8/28/16.
  */
object BeautifulSetHelper extends App {
  def basic() = {
    for (n <- 3 to 30) {
      val v = Factory.createVector[Integer]((0 to n).map(Integer.valueOf).toArray)
      val generator = Factory.createPermutationWithRepetitionGenerator(v, 3).iterator()
      var ct = 0
      while (generator.hasNext) {
        val list = generator.next().getVector.asScala.map(_.toInt).toList
        if (list.sum == n) {
          //      println(list)
          ct += 1
        }
      }
      print(s"$ct,")
    }
  }

  val n = 10
  val v = Factory.createVector[Integer]((0 to n).map(Integer.valueOf).toArray)
  val generator = Factory.createPermutationWithRepetitionGenerator(v, 3).iterator()
  var ct = 0
  val mainSet = mutable.HashSet[List[Int]]()
  while (generator.hasNext) {
    val list = generator.next().getVector.asScala.map(_.toInt).toList
    if (list.sum == n)
      mainSet += list
  }
//  println(mainSet.mkString("\n"))
  val nv = Factory.createVector(mainSet.asJava)
  val ngen = Factory.createSimpleCombinationGenerator(nv, 7).iterator()
  var found = false
  while (ngen.hasNext && !found) {
    val matrix = ngen.next().asScala.toList
    if(isValid(matrix)){
//      found=true
      println(matrix.mkString("\n"))
      println("_____")
    }
  }

  def isValid(matrix: List[List[Int]]): Boolean = {
    val outLen = matrix.length
    for (i <- 0 to 2) {
      val curSet = mutable.HashSet[Int]()
      for (j <- 0 until outLen) {
        if (curSet.contains(matrix(j)(i)))
          return false
        else
          curSet += matrix(j)(i)
      }
    }
    return true
  }
}
