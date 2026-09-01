package zi.chef.y16.nov

import org.paukov.combinatorics.{CombinatoricsVector, Factory, ICombinatoricsVector}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Random

/**
  * Created by balaudt on 11/8/16.
  */
object ChefSquareHelper extends App {
  val n = 7

  def fk() = {
    def isValid(in: ICombinatoricsVector[ICombinatoricsVector[Integer]]): Boolean = {
      val s = mutable.HashSet[Int]()
      for (i <- 0 until n) {
        for (j <- 0 until n) s += in.getValue(i).getValue(j)
        if (s.size != n) return false
        s.clear()
        for (j <- 0 until n) s += in.getValue(j).getValue(i)
        if (s.size != n) return false
      }
      true
    }

    def print(in: ICombinatoricsVector[ICombinatoricsVector[Integer]]) = {
      in.asScala.foreach(x => println(x.asScala.mkString(" ")))
      println("----------")
    }
    val perms = new CombinatoricsVector[ICombinatoricsVector[Integer]](Factory.createPermutationGenerator(new CombinatoricsVector[Integer]((1 to n).map(new Integer(_)).asJavaCollection)).generateAllObjects())
    val combs = Factory.createSimpleCombinationGenerator(perms, n)
    val out = combs.asScala.filter(isValid)
    println(out.size)
    out.foreach(print)


  }

  def anotherFine() = {
    val perms = (1 to n).permutations.toList.combinations(n).flatMap(_.permutations).toList

    def isValid(in: List[IndexedSeq[Int]]): Boolean = {
      val s = mutable.HashSet[Int]()
      for (i <- 0 until n) {
        for (j <- 0 until n) s += in(i)(j)
        if (s.size != n) return false
        s.clear()
        for (j <- 0 until n) s += in(j)(i)
        if (s.size != n) return false
        s.clear()
      }
      true
    }

    val out = perms.filter(isValid)
    println(out.size)
    out.foreach(x => {
      x.foreach(y => println(y.mkString(" ")))
      println("___________")
    })
  }

  val perms = (1 to n).permutations.toList

  def print(x: List[IndexedSeq[Int]]) = {
    x.foreach(y => println(y.mkString(" ")))
    //    println("___________")
  }

  def isValid(rawIn: List[IndexedSeq[Int]], x: IndexedSeq[Int]): Boolean = {
    val in = x :: rawIn
    val s = mutable.HashSet[Int]()
    val l = in.size
    for (i <- 0 until l) {
      for (j <- 0 until n) s += in(i)(j)
      if (s.size != n) return false
      s.clear()
    }
    for (i <- 0 until n) {
      for (j <- 0 until l) s += in(j)(i)
      if (s.size != l) return false
      s.clear()
    }
    true
  }

  var (ct, f) = (0, 0)

  def metric(in: List[IndexedSeq[Int]]) = {
    var min = Int.MaxValue
    val center = n / 2
    for (i <- 0 until n) {
      for (j <- 0 until n) {
        if (in(i)(j) == 1) {
          val dist = Math.abs(center - i) + Math.abs(center - j)
          if (dist < min) min = dist
        }
      }
    }
    if (min > f) f = min
    min
  }

  def gen(cur: List[IndexedSeq[Int]]): Unit = {
    if (cur.size == n) {
      print(cur)
      println(ct)
      ct += 1
      val m = metric(cur)
      println((m, f))
      println("___________")
      return
    }
    val possibilities = perms.filter(x => !cur.contains(x) && isValid(cur, x))
    val pl = possibilities.size
    for (i <- 1 to 4) {
      val x = possibilities(Random.nextInt(pl))
      if (!cur.contains(x) && isValid(cur, x)) gen(x :: cur)
    }
    //    for (x <- perms if !cur.contains(x) && isValid(cur, x)) gen(x :: cur)
  }

  //  gen(List())

  val test = mutable.PriorityQueue[Int]()(Ordering[Int].reverse)
  test ++= 1 to 10
  println(test.dequeue())

  for (i <- if (false) (0 to n) else (n to 0 by -1)) println(i)
}
