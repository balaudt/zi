package zi.chef.y16.feb

/**
  * Created by balaudt on 8/18/16.
  */
object Solution extends App {
  palindrome("aabaa")

  def palindrome(str: String) = {
    val c = str.toCharArray

    val root = new Node(' ')
    var ct = 0
    for (i <- 0 until c.length) {
      for (j <- i until c.length) {
        if (root.insert(c, i, j) && isPalindrome(c, i, j)) ct += 1
      }
    }
    println(ct)
  }

  def isPalindrome(c: Array[Char], st: Int, end: Int): Boolean = {
    val mid = (end - st) / 2
    for (i <- 0 to mid)
      if (c(i + st) != c(end - i))
        return false
    true
  }

  class Node(c: Char) {
    val next = Array.ofDim[Node](26)

    def insert(c: Array[Char], st: Int, end: Int): Boolean = {
      if (st > end)
        return true
      var flag = true
      if (next(c(st) - 'a') == null) {
        next(c(st) - 'a') = new Node(c(st))
        flag = false
      }
      flag = flag && next(c(st) - 'a').insert(c, st + 1, end)
      flag
    }


    override def toString = s"($c," + next.mkString(",") + ")"
  }

}
