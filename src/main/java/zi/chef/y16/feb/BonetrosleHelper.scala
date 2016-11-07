package zi.chef.y16.feb

/**
  * Created by balaudt on 8/27/16.
  */
object BonetrosleHelper extends App {
  val sums = (1l to 100l).scan(0l)(_ + _).tail
  for (i <- 0 to 18) {
    if (sums(32 + i) - sums(i) > 737) println(i)
  }
  println((6 to 38).sum + 11)
}
