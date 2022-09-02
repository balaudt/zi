package zi.jam.y21.qual

import scala.io.Source

object MoonUmbrella {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val in = lines.next().split(" ")
      val (x, y) = (in(0).toInt, in(1).toInt)
      val mural = in(2).toCharArray
      var lastChar = '?'
      var cost = 0
      for(j <- mural.indices) {
        if(lastChar=='C' && mural(j)=='J')
          cost += x
        else if(lastChar == 'J' && mural(j) =='C')
          cost += y
        if(mural(j)!='?')
          lastChar = mural(j)
      }
      println(s"Case #$i: $cost")
    }
  }

}
