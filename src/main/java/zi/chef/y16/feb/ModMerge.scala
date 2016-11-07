package zi.chef.y16.feb

/**
  * Created by balaudt on 8/14/16.
  */
object ModMerge extends App {
  val in = Array(Array(1, 5, 9, 10, 15, 20), Array(2, 3, 8, 13))
  for (i <- 0 until in(0).length) {
    if (in(0)(i) > in(1)(0)) {
      var t = in(0)(i)
      in(0)(i) = in(1)(0)
      in(1)(0) = t
      var j = 0
      while ((j < in(1).length - 1) && in(1)(j) > in(1)(j + 1)) {
        t = in(1)(j)
        in(1)(j) = in(1)(j + 1)
        in(1)(j + 1) = t
        j += 1
      }
    }
  }
  println(in(0).mkString(","))
  println(in(1).mkString(","))
}
