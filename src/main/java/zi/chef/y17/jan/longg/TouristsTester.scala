package zi.chef.y17.jan.longg

import java.io.PrintStream

import scala.io.Source

/**
  * Created by balamurugan on 1/10/17.
  */
object TouristsTester extends App {
  private def gen() = {
    val ps = new PrintStream("/Users/balamurugan/Temp/tourists.in")
    System.setOut(ps)
    val lines = Source.fromFile("/Users/balamurugan/Temp/eul7.e").getLines()
    println(37)
    var ct = 1
    while (lines.hasNext) {
      println(s"TC$ct")
      val nm = lines.next()
      println(nm)
      if (nm.split(" ")(1).toInt != 0)
        lines.next().split("  ").foreach(uvs => println(uvs.split(" ").map(_.toInt + 1).mkString(" ")))
      ct += 1
    }
    ps.close()
  }

  gen()
}
