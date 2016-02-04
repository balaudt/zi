package zi.fb.cup15

import scala.io.Source
import scala.collection.breakOut
import scala.collection.Searching._
import scala.collection.mutable.ArraySeq

object PerfComp extends App {
  var t0 = System.nanoTime;
  val lines = Source.fromFile("/Users/balaudt/Temp/fb/C.in").bufferedReader().lines().iterator();
  val t = lines.next().toInt;
  for (i <- 1 to t) {
    val line = lines.next().split(" ");
    val n = line(0).toInt;
    val p: Long = line(1).toInt;
    val b = lines.next().split(" ").map(_.toInt)(breakOut)
    val runningSum = new ArraySeq[Long](n + 1);
    runningSum(0) = 0;
    var ans: Long = 0;
    var left = 1;
    if (b(0) <= p) {
      ans += 1;
      left = 0;
      runningSum(1) = b(0);
    }
    for (j <- 1 to n - 1) {
      if (b(j) >= p) {
        if (b(j) == p)
          ans += 1;
        left = j + 1;
        runningSum(j + 1) = 0;
      } else {
        runningSum(j + 1) = runningSum(j) + b(j);
        if (runningSum(j + 1) <= p)
          ans += j + 1 - left;
        else {
          val res = runningSum.search(runningSum(j + 1) - p, left, j + 2);
          var ip = res match {
            case Found(ind)          => ind
            case InsertionPoint(ind) => ind
          };
          ip -= left;
          left += ip;
          ans += j - left + 1;
        }
      }
    }
    println(s"Case #${i}: ${ans}");
  }
  println(System.nanoTime - t0);
}