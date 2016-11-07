package zi.chef.y16.feb

import java.io.{BufferedReader, InputStreamReader}
import java.util


/**
  * Created by balaudt on 2/14/16.
  */
object DSimple extends App {

  //  var reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/D-sample.in"))
  var reader = new BufferedReader(new InputStreamReader(System.in))
  var t = reader.readLine().toInt
  for (i <- 1 to t) {
    val nm = reader.readLine().split(" ").map(_.toInt)
    val (n, m) = (nm(0), nm(1))
    val in = new Array[Array[Int]](n)
    val rowCounters = new util.ArrayList[Counter](n)
    val columnCounters = new util.ArrayList[Counter](m)
    val numbers = new util.HashSet[Int](n * m)
    for (j <- 0 until n) {
      in(j) = reader.readLine().split(" ").map(_.toInt)
      var rowCounter = rowCounters.get(j)
      for (k <- 0 until m) {
        rowCounter.increment(in(j)(k))
        columnCounters.get(k).increment(in(j)(k))
        numbers.add(in(j)(k))
      }
    }
  }

  class Counter extends util.TreeMap[Int, Int] {

    def increment(k: Int): Unit = {
      var v = get(k)
      if (v == null)
        put(k, 1)
      else
        put(k, v + 1)
    }
  }

}
