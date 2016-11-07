package zi.chef.y16.feb

/**
  * Created by balaudt on 2/9/16.
  */
object EAnalysis extends App {
  val m = 29
  for (i <- 1 to m) {
    var ct = 0
    for (j <- 1 to m) {
      for (k <- j to m) {
        ct += (if (Range(j, k + 1).find(_ == i).isEmpty) 0 else 1)
      }
    }
    print(ct + ",")
  }
  println()
  for (i <- 1 to (m / 2+1))
    print((m + 1) * i - i * i+",")
}