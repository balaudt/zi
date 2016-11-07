package zi.chef.y16.feb

import scala.collection.mutable

/**
  * Created by balaudt on 9/4/16.
  */
object Abb extends App {
  val lines = scala.io.Source.fromFile("/Users/balaudt/Temp/input08.in").getLines()
  //  val lines = scala.io.Source.fromInputStream(System.in).getLines()
  val q = lines.next().toInt
  for (i <- 1 to q) {
    val a = lines.next().toCharArray
    val b = lines.next().toCharArray

    val memo = mutable.HashSet[(Int, Int)]()
    def isFeasible(ast: Int, bst: Int): Boolean = {
      //      println((ast, bst))
      if (ast == a.length && bst == b.length)
        return true
      if (memo.contains((ast, bst)))
        return false
      if (ast == a.length || (bst == b.length && a(ast).isUpper)) {
        memo += Tuple2(ast, bst)
        return false
      }
      var flag = false
      if (a(ast).isLower)
        flag = isFeasible(ast + 1, bst)
      if (!flag && bst != b.length && (a(ast) == b(bst) || a(ast).toUpper == b(bst)))
        flag = isFeasible(ast + 1, bst + 1)
      if (flag)
        true
      else {
        memo += Tuple2(ast, bst)
        false
      }
    }

    println(if (isFeasible(0, 0)) "YES" else "NO")
  }
}
