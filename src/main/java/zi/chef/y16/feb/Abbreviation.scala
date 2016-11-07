package zi.chef.y16.feb

import scala.io.Source

/**
  * Created by balaudt on 8/27/16.
  */
object Abbreviation extends App {
  val lines = Source.fromInputStream(System.in).getLines()
  val q = lines.next().toInt
  for (i <- 1 to q) {
    val a = lines.next().toCharArray
    val b = lines.next().toCharArray
    def isLowerSubseq(ast: Int, aEnd: Int, bst: Int, bEnd: Int): Boolean = {
      //      println((ast, aEnd, bst, bEnd))
      var ai = ast
      var bi = bst
      while (ai < aEnd && bi < bEnd) {
        if (a(ai) == b(bi) || a(ai) == b(bi) + 32)
          bi += 1
        ai += 1
      }
      //      println(bi == bEnd)
      bi == bEnd
    }

    if (a.length < b.length)
      println("NO")
    else {
      var (ai, bi, aLast, bLast, mismatch) = (0, 0, -1, -1, false)
      var (t, tc) = ('a', 0)
      while (!mismatch && ai < a.length && bi < b.length) {
        aLast = ai
        bLast = bi
        while (ai < a.length && a(ai) >= 'a') ai += 1
        if (ai == a.length)
          bi = b.length
        else {
          t = a(ai)
          while (ai < a.length && (a(ai) == t || a(ai) == t + 32)) {
            tc += 1
            ai += 1
          }
          ai -= 1
          if (a(ai) >= 'a') a(ai) = a(ai).toUpper
          while (bi < b.length && b(bi) != a(ai)) bi += 1
          if (bi == b.length) mismatch = true
          else {
            while (bi < b.length && b(bi) == a(ai)) {
              bi += 1
              tc -= 1
            }
            bi -= 1
          }
        }
        if (!mismatch) {
          if (isLowerSubseq(aLast, ai, bLast, bi)) {
            ai += 1
            bi += 1
          }
          else
            mismatch = true
        }
      }
      println(if (!mismatch && bi >= b.length) "YES" else "NO")
    }
  }
}
