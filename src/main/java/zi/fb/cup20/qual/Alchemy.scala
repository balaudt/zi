package zi.fb.cup20.qual

import java.io.{FileWriter, PrintWriter}

import scala.io.Source

object Alchemy {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(System.in).getLines()
    val writer = new PrintWriter(new FileWriter("/Users/bchandrasekaran/src/zi/io/20/qual/alchemy_output.txt"))
    val t = lines.next().toInt
    val pos = Map("AAB" -> "A", "ABA" -> "A", "ABB" -> "B", "BAA" -> "A", "BAB" -> "B", "BBA" -> "B")
    val st = System.currentTimeMillis()
    for (i <- 1 to t) {
      val n = lines.next().toInt
      val s = new StringBuilder(lines.next())
      var found = true
      while (s.length > 1 && found) {
        found = false
        for (j <- 0 to s.length - 3 if !found) {
          pos.get(s.substring(j, j + 3)) match {
            case Some(x) =>
              found = true
              s.replace(j, j + 3, x)
            case None =>
          }
        }
      }
      val out = if (found) 'Y' else 'N'
      writer.println(s"Case #$i: $out")
    }
    writer.close()
    println((System.currentTimeMillis() - st) / 1000)
  }

}
