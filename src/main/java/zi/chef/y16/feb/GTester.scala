package zi.chef.y16.feb

import java.io.{FileReader, PrintStream, BufferedReader}

import org.apache.commons.lang3.RandomUtils

/**
  * Created by balaudt on 2/13/16.
  */
object GTester extends App {
  val MAX = 3
  val L = MAX * MAX

  def generate = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/feb/G-gen.in"))
    val t = 10
    println(t)
    for (i <- 0 until t) {
      val (p, d, h, n) = (MAX, MAX, MAX, RandomUtils.nextInt(1, MAX + 1))
      println(s"${p} ${d} ${h} ${n}")
      val l = RandomUtils.nextInt(1, L + 1)
      var builder = new StringBuilder()
      for (j <- 0 until p) builder.append(RandomUtils.nextInt(1, MAX + 1)).append(' ')
      println(builder.deleteCharAt(builder.length - 1).toString())
      val ls = RandomUtils.nextInt(1, MAX + 1)
      var le = RandomUtils.nextInt(ls, MAX + 1)
      println(s"${ls} ${le}")
      for (j <- 0 until d) {
        builder.clear()
        for (k <- 0 until h) {
          builder.append(RandomUtils.nextInt(1, MAX + 1)).append(' ')
        }
        println(builder.deleteCharAt(builder.length - 1).toString())
      }
      for (j <- 0 until p) {
        for (k <- 0 until d) {
          builder.clear()
          for (m <- 0 until h) {
            builder.append(RandomUtils.nextInt(0, 2)).append(' ')
          }
          println(builder.deleteCharAt(builder.length - 1).toString())
        }
      }
    }
  }

  def test = {
    val reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/G-gen.in"))
    val t = reader.readLine().toInt
    for (i <- 1 to t) {
      var in = reader.readLine().split(" ").map(_.toInt)
      val (p, d, h, n) = (in(0), in(1), in(2), in(3))
      val l = reader.readLine().split(" ").map(_.toInt)
      in = reader.readLine().split(" ").map(_.toInt)
      val (ltBeg, ltEnd) = (in(0) - 1, in(1) - 1)
      val r = new Array[Array[Int]](d)
      for (j <- 0 until d) {
        r(j) = reader.readLine().split(" ").map(_.toInt)
      }
      val f = Array.ofDim[Array[Int]](p, d)
      for (j <- 0 until p) {
        for (k <- 0 until d) {
          f(j)(k) = reader.readLine().split(" ").map(_.toInt)
        }
      }
    }
  }
}
