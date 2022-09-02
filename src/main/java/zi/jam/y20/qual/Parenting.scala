package zi.jam.y20.qual

import java.io.{FileInputStream, InputStream, OutputStream, PrintWriter}

import scala.io.Source
import scala.collection.mutable

object Parenting extends App {

  case class Activity(st: Int, end: Int, ind: Int, var assigned: Char = 'E')

  def parenting(is: InputStream, os: OutputStream): Unit = {
    val lines = Source.fromInputStream(is).getLines()
    val w = new PrintWriter(os)
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val n = lines.next().toInt
      var activities = new Array[Activity](n)
      for (j <- 0 until n) {
        val activity = lines.next().split(" ").map(_.toInt)
        activities(j) = Activity(activity(0), activity(1), j)
      }
      activities = activities.sortBy(_.end)
      var (came, jamie, isPossible, j) = (0, 0, true, 0)
      while (isPossible && j < n) {
        val activity = activities(j)
        if (came <= activity.st) {
          activity.assigned = 'C'
          came = activity.end
        } else if (jamie <= activity.st) {
          activity.assigned = 'J'
          jamie = activity.end
        } else
          isPossible = false
        j += 1
      }
      if (isPossible) {
        val out = new Array[Char](n)
        activities.foreach(x => out(x.ind) = x.assigned)
        w.println(s"Case #$i: ${new String(out)}")
      } else
        w.println(s"Case #$i: IMPOSSIBLE")
    }
    w.close()
  }

  //  parenting(System.in, System.out)
  parenting(new FileInputStream("io/20/qual/parenting.in"), System.out)
}
