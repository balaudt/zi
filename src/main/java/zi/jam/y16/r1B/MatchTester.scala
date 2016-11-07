package zi.jam.y16.r1B

import scala.io.Source

/**
  * Created by balaudt on 4/30/16.
  */
object MatchTester extends App {

  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    var input: Array[Array[Char]] = lineIt.next().split(" ").map(_.toCharArray)
    val l = input(0).length
    var qc = 0
    for (w <- input) {
      for (c <- w) {
        if (c == '?')
          qc += 1
      }
    }
    val max = Math.pow(10, qc).toInt
    var min = Integer.MAX_VALUE
    var out = Array.ofDim[Int](2)
    for (num <- 0 until max) {
      var qa = new StringBuilder(num.toString)
      while (qa.length < qc)
        qa.insert(0, '0')
      var ind = 0
      val in = Array.ofDim[Array[Char]](2)
      in(0) = input(0).clone()
      in(1) = input(1).clone()
      for (j <- 0 until l) {
        if (in(0)(j) == '?') {
          in(0)(j) = qa(ind)
          ind += 1
        }
      }
      for (j <- 0 until l) {
        if (in(1)(j) == '?') {
          in(1)(j) = qa(ind)
          ind += 1
        }
      }

      val cur = Math.abs(Integer.parseInt(new String(in(0))) - Integer.parseInt(new String(in(1))))
      if (cur < min) {
        min = cur
        out(0) = Integer.parseInt(new String(in(0)))
        out(1) = Integer.parseInt(new String(in(1)))
      }
    }

    val outstr = Array.ofDim[StringBuilder](2)
    for (j <- 0 to 1) {
      outstr(j) = new StringBuilder(out(j).toString)
      while (outstr(j).length < l)
        outstr(j).insert(0, '0')
    }
    println(s"Case #$i: ${outstr(0)} ${outstr(1)}")
  }

}
