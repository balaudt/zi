

package zi.fb.cup15

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintStream

import scala.util.control.Breaks.break
import scala.util.control.Breaks.breakable

object BoomerangConstellations {
  def main(args: Array[String]): Unit = {
    System.setIn(new FileInputStream("/Users/balaudt/Temp/fb/A.in"));
    System.setOut(new PrintStream("/Users/balaudt/Temp/fb/A.out"));
    val reader = new BufferedReader(new InputStreamReader(System.in));
    var lines = reader.lines();
    var lineIt = lines.iterator();
    var t = lineIt.next().toInt;
    for (i <- 1 to t) {
      var n = lineIt.next().toInt;
      var in = Array.ofDim[String](n, 2);
      for (j <- 1 to n) {
        in(j - 1) = lineIt.next().split(" ");
      }
      type Point = Tuple2[Int, Int];
      def lt(pt1: (Int, Int), pt2: (Int, Int)): Boolean = if (pt1._1 == pt2._1) pt1._2 < pt2._2 else pt1._1 < pt2._1;
      var pts = in.map(pt => (pt(0).toInt, pt(1).toInt)).sortWith(lt);
      var segments = new Array[Tuple3[Int, Point, Point]](n * (n - 1) / 2);
      var ct = 0;
      for (j <- 0 to n - 2) {
        for (k <- j + 1 to n - 1) {
          var (pt1, pt2) = (pts(j), pts(k));
          segments(ct) = ((pt1._1 - pt2._1) * (pt1._1 - pt2._1) + (pt1._2 - pt2._2) * (pt1._2 - pt2._2), pt1, pt2);
          ct += 1;
        }
      }
      var ans = 0;
      segments.groupBy(_._1).foreach(keyVal => {
        var grpPts = keyVal._2;
        //        grpPts.foreach(println(_));
        for (j <- 0 to grpPts.length - 2) {
          breakable {
            for (k <- j + 1 to grpPts.length - 1) {
              var (a, b, c, d) = (grpPts(j)._2, grpPts(j)._3, grpPts(k)._2, grpPts(k)._3);
              if (a.eq(c) || a.eq(d) || b.eq(c) || b.eq(d))
                ans += 1;
              else if (lt(grpPts(j)._3, grpPts(k)._2))
                break;
            }
          }
        }
      });
      println(s"Case #${i}: ${ans}");
    }
  }

}