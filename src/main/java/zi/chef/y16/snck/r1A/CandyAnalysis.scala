package zi.chef.y16.snck.r1A

/**
  * Created by balaudt on 6/4/16.
  */
object CandyAnalysis extends App {

  val (c, d) = (64, 112)
  var min: (Int, Int, Int) = (-1, -1, Int.MaxValue)
  for (i <- -10000 to 10000) {
    for (j <- -10000 to 10000) {
      val diff = Math.abs(i * c + j * d)
      if (diff != 0 && diff < min._3)
        min = (i, j, diff);
    }
  }
  println(min)
}
