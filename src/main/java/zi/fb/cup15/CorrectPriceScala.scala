package zi.fb.cup15

import java.io.PrintStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.FileInputStream
import scala.collection.Searching._;

object CorrectPriceScala {
  def main(args: Array[String]): Unit = {
    System.setIn(new FileInputStream("/Users/balaudt/Temp/fb/A.in"));
    System.setOut(new PrintStream("/Users/balaudt/Temp/fb/A.out"));
    val reader = new BufferedReader(new InputStreamReader(System.in));
    var lineIt = reader.lines().iterator();
    var t = lineIt.next().toInt;
    val N = 1e5.toInt;
    val lkup = new Array[Int](N);
    lkup(0) = 1;
    for (i <- 2 to N)
      lkup(i - 1) = lkup(i - 2) + i;
    for (i <- 1 to t) {
      val p = lineIt.next().split(" ")(1).toLong;
      val b = lineIt.next().split(" ").map(_.toInt);
      val n = b.length;
      var runningSums = new Array[Long](n);
      var curSum = 0l;
      var ans = 0l;
      runningSums(0) = b(0);
      if (b(0) <= p) {
        ans += 1;
      }
      for (j <- 1 to n - 1) {
        runningSums(j) = runningSums(j - 1) + b(j);
        
      }
    }
  }
}