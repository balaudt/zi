package zi.chef.y16.feb

/**
  * Created by balaudt on 8/13/16.
  */
object GPTriplets extends App {
  val in = Array(1, 2, 6, 10, 18, 54)
  var ct = 0
  for (j <- 1 until in.length - 1) {
    var (i, k) = (j - 1, j + 1)
    while (i >= 0 && k < in.length) {
      if (in(j) % in(i) == 0 && in(k) % in(j) == 0) {
        if (in(j) / in(i) == in(k) / in(j)) {
          i -= 1
          k += 1
          ct += 1
        }
        else if (in(j) / in(i) < in(k) / in(j))
          i -= 1
        else
          k += 1
      } else if (in(j) % in(i) == 0)
        k += 1
      else i -= 1
    }
  }
  println(ct)
}
