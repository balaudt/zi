package zi.chef.y16.feb


/**
  * Created by balaudt on 8/14/16.
  */
object MinDiff extends App {
  val in = Array(1, 2, 3)
  val k = 2
  var (min, max) = (in(0), in(0))
  for (elem <- in) {
    if (elem < min) min = elem
    if (elem > max) max = elem
  }
  if (k >= (max - min))
    for (elem <- in.indices) in(elem) += k
  else {
    min += k
    max -= k
    var t = min
    min = Math.min(min, max)
    max = Math.max(t, max)
    for (elem <- in.indices) {
      if (in(elem) < min)
        in(elem) += k
      else if (in(elem) > max)
        in(elem) -= k
      else if (in(elem) - min < max - in(elem))
        in(elem) += k
      else
        in(elem) -= k
      if (in(elem) < min) min = in(elem)
      if (in(elem) > max) max = in(elem)
    }
  }
  println(in.mkString(","))
  println(max - min)
}
