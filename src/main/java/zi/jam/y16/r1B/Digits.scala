package zi.jam.y16.r1B

import scala.collection.mutable
import scala.io.Source

/**
  * Created by balaudt on 4/30/16.
  */
object Digits extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val digits = Array("ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE")
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    val s = lineIt.next().toCharArray.sorted
    val charMap = mutable.HashMap[Char, Int]()
    "ABCEDEFGHIJKLMNOPQRSTUVWXYZ".foreach(x => charMap.put(x, 0))
    s.foreach(x => charMap.put(x, charMap.get(x).get + 1))
    val out = mutable.HashMap[Int, Int]()

    if (charMap.get('Z').get > 0) {
      val x = charMap.get('Z').get
      out.put(0, x)
      digits(0).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('W').get > 0) {
      val x = charMap.get('W').get
      out.put(2, x)
      digits(2).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('U').get > 0) {
      val x = charMap.get('U').get
      out.put(4, x)
      digits(4).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('X').get > 0) {
      val x = charMap.get('X').get
      out.put(6, x)
      digits(6).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('G').get > 0) {
      val x = charMap.get('G').get
      out.put(8, x)
      digits(8).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('S').get > 0) {
      val x = charMap.get('S').get
      out.put(7, x)
      digits(7).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('F').get > 0) {
      val x = charMap.get('F').get
      out.put(5, x)
      digits(5).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('T').get > 0) {
      val x = charMap.get('T').get
      out.put(3, x)
      digits(3).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('O').get > 0) {
      val x = charMap.get('O').get
      out.put(1, x)
      digits(1).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }
    if (charMap.get('N').get > 0) {
      val x = charMap.get('N').get / 2
      out.put(9, x)
      digits(9).foreach(y => charMap.put(y, charMap.get(y).get - x))
    }

    print(s"Case #$i: ")
    for (j <- 0 to 9) {
      if (out.contains(j)) {
        for (k <- 1 to out.get(j).get) {
          print(j)
        }
      }
    }
    println()

  }
}
