package zi.chef.y16.feb

/**
  * Created by balaudt on 8/14/16.
  */
object Adobe1 extends App {
  val in = "DDIDDIID"
  val out = Array.ofDim[Char](in.length + 1)
  var (num, inInd, outInd, ct) = (in.length + 1, in.length - 1, out.length, 0)
  while (inInd >= 0) {
    ct = 0
    while (inInd >= 0 && in(inInd) == 'D') {
      inInd -= 1
      ct += 1
    }
    ct += 1
    var t = outInd - ct
    out(outInd - ct) = ('0' + num).toChar
    num -= 1
    ct = outInd - ct + 1
    while (ct < outInd) {
      ct += 1
      out(ct) = ('0' + num).toChar
      num -= 1
    }
  }
  println(out.mkString(""))
}
