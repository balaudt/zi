package zi.fb.cup17.r1

import java.io.PrintStream

import scala.util.Random

/**
  * Created by balamurugan on 1/14/17.
  */
object ManicMovingPerf extends App{
  private def gen() = {
    val ps = new PrintStream("/Users/balamurugan/Temp/io/17/fb/test/manic-perf.in")
    System.setOut(ps)
    println(100)
    for (_ <- 1 to 100) {
      println("100 5000 5000")
      for (_ <- 1 to 5000)
        println(s"${Random.nextInt(100) + 1} ${Random.nextInt(100) + 1} ${Random.nextInt(1000) + 1}")
      for (_ <- 1 to 5000)
        println(s"${Random.nextInt(100) + 1} ${Random.nextInt(100) + 1}")
    }
    ps.close()
  }

  gen()
}
