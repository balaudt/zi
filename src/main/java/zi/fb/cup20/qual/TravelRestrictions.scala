package zi.fb.cup20.qual

import java.io.{FileWriter, PrintWriter}

import scala.io.Source

object TravelRestrictions {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val writer = new PrintWriter(new FileWriter("/Users/bchandrasekaran/src/zi/io/20/qual/travel_restrictions_full_out.txt"))
    val t = lines.next().toInt
    for (i <- 1 to t) {
      writer.println(s"Case #$i:")
      val n = lines.next().toInt
      val in = lines.next().toCharArray.map(_ == 'Y')
      val out = lines.next().toCharArray.map(_ == 'Y')
      for (j <- 0 until n) {
        val cur = Array.ofDim[Boolean](n)
        cur(j) = true
        for (k <- j - 1 to 0 by -1)
          cur(k) = cur(k + 1) && in(k) && out(k + 1)
        for (k <- j + 1 until n)
          cur(k) = cur(k - 1) && in(k) && out(k - 1)
        writer.println(cur.map(x => if (x) 'Y' else 'N').mkString(""))
      }
    }
    writer.close()
  }

}
