package zi.chef.y16.feb

import java.io.{FileReader, Reader, BufferedReader, PrintStream}
import java.util

import com.google.common.base.Joiner
import org.apache.commons.lang3.RandomUtils

/**
  * Created by balaudt on 2/10/16.
  */
object FAnalysis extends App {
  def gen = {
    System.setOut(new PrintStream("/Users/balaudt/Temp/feb/F-gen.in"))
    val n = 5
    println(n)
    val a = new StringBuilder
    val b = new StringBuilder
    for (i <- 1 to n) {
      a.append(RandomUtils.nextInt(5, 50)).append(' ')
      b.append(RandomUtils.nextInt(5, 50)).append(' ')
    }
    a.deleteCharAt(a.length - 1)
    b.deleteCharAt(b.length - 1)
    println(a.toString())
    println(b.toString())
  }

  def att1 = {
    val reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/F-gen.in"))
    val n = reader.readLine().toInt
    val a = reader.readLine().split(" ").map(_.toInt)
    val b = reader.readLine().split(" ").map(_.toInt)
    def analyze = {
      val c = Array.ofDim[Int](n, n)
      var sum: Long = 0l
      for (i <- 0 to n - 1) {
        for (j <- 0 to n - 1) {
          c(i)(j) = a(i) * b(j) + (i + 1) * b(j) + (j + 1) * a(i) + (i + 1) * (j + 1)
          val t = c(i)(j)
          print(f"$t%4d ")
        }
        println()
      }

      def maxInLk(top: Int, left: Int, k: Int): Int = {
        var ans: Int = 0
        for (i <- top to top + k - 1) {
          for (j <- left to left + k - 1) {
            if (c(i)(j) > ans)
              ans = c(i)(j)
          }
        }
        ans
      }
      for (i <- 1 to n) {
        var sum: Long = 0l
        for (r <- 0 to n - i) {
          for (c <- 0 to n - i) {
            sum += maxInLk(r, c, i)
          }
        }
        print(sum + " ")
      }
      println("\n----")
    }

    analyze

    util.Arrays.sort(a)
    util.Arrays.sort(b)
    analyze

    reader.close()
  }

  def att2 = {
    val reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/F-gen.in"))
    val n = reader.readLine().toInt
    val a = reader.readLine().split(" ").map(_.toInt)
    val b = reader.readLine().split(" ").map(_.toInt)
    val c = Array.ofDim[Int](n, n)
    for (i <- 0 to n - 1) {
      for (j <- 0 to n - 1) {
        c(i)(j) = a(i) * b(j) + (i + 1) * b(j) + (j + 1) * a(i) + (i + 1) * (j + 1)
        val t = c(i)(j)
      }
    }

    def maxInLk(top: Int, left: Int, k: Int): Int = {
      var ans: Int = 0
      for (i <- top to top + k - 1) {
        for (j <- left to left + k - 1) {
          if (c(i)(j) > ans)
            ans = c(i)(j)
        }
      }
      ans
    }
    for (i <- 1 to n) {
      var sum: Long = 0l
      for (r <- 0 to n - i) {
        for (c <- 0 to n - i) {
          sum += maxInLk(r, c, i)
        }
      }
      print(s"${sum} ")
    }
    println()

    util.Arrays.sort(a)
    util.Arrays.sort(b)
    for (i <- 0 to n - 1) {
      for (j <- 0 to n - 1) {
        c(i)(j) = a(i) * b(j) + (i + 1) * b(j) + (j + 1) * a(i) + (i + 1) * (j + 1)
      }
    }
    for (i <- 0 to n - 1) {
      var sum: Long = 0
      for (j <- i to n - 1) {
        for (k <- i to n - 1) {
          sum += c(j)(k)
        }
      }
      print(s"${sum} ")
    }
    println()
  }

  def kMatAnalysis = {
    var n = 27
    for (i <- 1 to n) {
      var ct = Array.ofDim[Int](n, n)
      for (k <- 0 to n - 1) {
        for (l <- 0 to n - 1) {
          ct(k)(l) = 0
        }
      }
      var ctOfSubMat: Int=0
      for (top <- 0 to n - i) {
        for (left <- 0 to n - i) {
          ctOfSubMat+=1
          for (k <- top to top + i - 1) {
            for (l <- left to left + i - 1) {
              ct(k)(l) += 1
            }
          }
        }
      }
      println(ctOfSubMat)
      /*println(i)
      for (k <- 0 to n - 1) {
        for (l <- 0 to n - 1) {
          print(f"${ct(k)(l)}%2d ")
        }
        println()
      }
      println("---")*/
    }
  }

  kMatAnalysis
  att1
  att2
}
