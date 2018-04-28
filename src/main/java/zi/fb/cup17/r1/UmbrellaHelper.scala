package zi.fb.cup17.r1

/**
  * Created by balamurugan on 1/15/17.
  */
object UmbrellaHelper extends App {
  def back = {
    val n = 7
    for (k <- 2 to n) {
      //partWithinPart
      val pwp = Array.ofDim[Int](4)
      val max = Math.pow(n + 1, k).toInt
      var ct = 0
      for (num <- 0 until max) {
        val a = Integer.toString(num, n + 1).toCharArray.map(_ - '0')
        val digits = Array.ofDim[Int](k)
        val l = a.length
        System.arraycopy(a, 0, digits, 0, l)
        if (digits.sum == n) {
          ct += 1
          if (a(l - 1) != 0 && l < k) pwp(0) += a(l - 1)
          else if (a(l - 1) == 0 && l == k) pwp(1) += a(0)
          else if (a(l - 1) != 0 && l == k) pwp(2) += a(0) * a(l - 1)
          else pwp(3) += 1
        }
      }
      val pwpStr = pwp.mkString(",")
      println(s"$ct -> $pwpStr")
    }
  }

  val n = 6
  for (k <- 2 to n) {
    //partWithinPart
    var mainCt = 0
    val max = Math.pow(n + 1, k).toInt
    var ct = 0
    for (num <- 0 until max) {
      val a = Integer.toString(num, n + 1).toCharArray.map(_ - '0')
      val digits = Array.ofDim[Int](k)
      val l = a.length
      System.arraycopy(a, 0, digits, 0, l)
      if (digits.sum == n) {
        ct += 1
        if (a(l - 1) != 0 && l < k) mainCt += a(l - 1)
        else if (a(l - 1) == 0 && l == k) mainCt += a(0)
        else if (a(l - 1) != 0 && l == k) mainCt += a(0) * a(l - 1)
        else mainCt += 1
      }
    }
    println(mainCt)
  }

  back
}
