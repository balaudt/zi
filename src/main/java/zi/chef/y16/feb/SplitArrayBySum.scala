package zi.chef.y16.feb

/**
  * Created by balaudt on 8/13/16.
  */
object SplitArrayBySum extends App {
  val in = Array(6, -2, -3, 2, 3)
  val sum = in.sum
  var (running, flag) = (0, false)
  for (el <- in if (!flag)) {
    if (2 * running + el == sum) {
      println(el)
      flag = true
    }
    running += el
  }
  if (!flag)
    println(-1)
}
