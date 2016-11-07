package zi.chef.y16.snck.qual

/**
  * Created by balaudt on 5/27/16.
  */
object Kitchen extends App {
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val n = lineIt.next().toInt
    val a = lineIt.next().split(" ").map(_.toInt)
    val b = lineIt.next().split(" ").map(_.toInt)
    var ans = 0
    if (a(0) >= b(0))
      ans += 1
    for (j <- 1 until n)
      if ((a(j) - a(j - 1)) >= b(j))
        ans += 1
    println(ans)
  }
}
