package zi.fb.cup15

import scala.util.Random
import java.io.PrintStream

object BoomerangTester {
  def main(args: Array[String]): Unit = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/fb/A-gen.in"));
    var rand = new Random();
    val t = 50
    println(t);
    for (i <- 0 to t - 1) {
      val n = 100 + rand.nextInt(100);
      println(n);
      for (j <- 0 to n - 1) {
        println(s"${rand.nextInt(10000)} ${rand.nextInt(10000)}");
      }
    }
  }
}