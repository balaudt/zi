package zi.jam.y17.qual

/**
  * Created by balamurugan on 4/8/17.
  */
object FashionHelper extends App {
  val models = Array('.', 'x', '+', 'o')
  for (i <- 4 to 4) {
    var ct = 0
    val cap = Math.pow(i * i, 4).toInt
    var validGrids = 0
    for (j <- 0 until cap) {
      val grid = Array.ofDim[Char](i, i)
      Integer.toString(j, 4).toCharArray.map(_ - '0').zipWithIndex.foreach(x => {
        val (r, c) = (x._1 / i, x._1 % i)
        grid(r)(c) = models(x._2)
      })
      if (isValidGrid(grid)) ct += 1
    }
    println(ct)
  }
  //  println(isValidGrid(Array("+.x".toCharArray, "+x+".toCharArray, "o..".toCharArray)))

  def isValidGrid(grid: Array[Array[Char]]): Boolean = {
    val n = grid.length
    val rowChecks = Array.ofDim[Boolean](n)
    val colChecks = Array.ofDim[Boolean](n)
    val diagChecks = Array.ofDim[Boolean](2, 2 * n + 1)
    for (i <- 0 until n) {
      for (j <- 0 until n) {
        if (grid(i)(j) == 'x' || grid(i)(j) == 'o') {
          if (rowChecks(i)) return false
          else rowChecks(i) = true
          if (colChecks(j)) return false
          else colChecks(j) = true
        } else if (grid(i)(j) == '+' || grid(i)(j) == 'x') {
          if (diagChecks(0)(i + j)) return false
          else diagChecks(0)(i + j) = true

          if (diagChecks(1)(i - j + n - 1)) return false
          else diagChecks(1)(i - j + n - 1) = true
        }
      }
    }
    true
  }
}
