package zi.jam.y20.qual

import java.io.{BufferedWriter, FileInputStream, FileWriter}
import java.util
import java.util.Random

import zi.jam.y20.qual.Parenting.Activity

import scala.io.Source
import scala.util.control.Breaks._

object ParentingTester extends App {
  def generate(): Unit = {
    val writer = new BufferedWriter(new FileWriter("io/20/qual/parenting.in"))
    writer.write("10\n")
    val random = new Random()
    for (i <- 0 until 10) {
      writer.write("10\n")
      for (j <- 0 until 10) {
        val st = random.nextInt(1000)
        val end = random.nextInt(150) + st
        writer.write(s"$st $end\n")
      }
    }
    writer.close()
  }

  bruteForce()

  def bruteForce(): Unit = {
    val lines = Source.fromInputStream(new FileInputStream("io/20/qual/parenting.in")).getLines()
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val n = lines.next().toInt
      var activities = new Array[Activity](n)
      for (j <- 0 until n) {
        val activity = lines.next().split(" ").map(_.toInt)
        activities(j) = Activity(activity(0), activity(1), j)
      }
      val max = (1 << n)
      var isPossible = false
      breakable {
        for (j <- 0 until max) {
          val perm = Integer.toBinaryString(j).toCharArray
          val choices = Array.fill(n)('0')
          System.arraycopy(perm, 0, choices, 0, perm.length)
          if (isValid(choices, activities)) {
            isPossible = true
            println(util.Arrays.toString(choices))
            break
          }
        }
      }
      if (!isPossible) println(isPossible)
    }
  }

  def isValid(choices: Array[Char], activities: Array[Activity]): Boolean = {
    choices.zipWithIndex.foreach(x => activities(x._2).assigned = x._1)
    var isValid = true
    activities.groupBy(_.assigned).foreach(grp => {
      val grpActivities = grp._2
      var last = 0
      grpActivities.sortBy(_.st).foreach(x => if (last > x.st) isValid = false else last = x.end)
    })
    isValid
  }

}
