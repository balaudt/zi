package zi.jam.y20.qual

import java.io.{InputStream, OutputStream, PrintWriter}

import scala.collection.mutable
import scala.io.Source
import java.util
import scala.collection.JavaConverters._

object ParentingSweepLine extends App {

  case class Event(id: Int, isBegin: Boolean)

  def addBinding(m: util.TreeMap[Int, mutable.Set[Event]], k: Int, event: Event): Unit = {
    var events = m.get(k)
    if (events == null) {
      events = mutable.Set[Event]()
      m.put(k, events)
    }
    events += event
  }

  def parenting(is: InputStream, os: OutputStream): Unit = {
    val lines = Source.fromInputStream(is).getLines()
    val w = new PrintWriter(os)
    val t = lines.next().toInt
    for (i <- 1 to t) {
      val n = lines.next().toInt
      val sweepLine = new util.TreeMap[Int, mutable.Set[Event]]()
      val out = new Array[Char](n)
      for (j <- 0 until n) {
        val event = lines.next().split(" ").map(_.toInt)
        addBinding(sweepLine, event(0), new Event(j, true))
        addBinding(sweepLine, event(1), new Event(j, false))
      }
      var (cameron, jamie, isPossible) = (-1, -1, true)
      for (events <- sweepLine.asScala) if (isPossible) {
        events._2.filter(!_.isBegin).foreach(x => if (cameron == x.id) cameron = -1 else jamie = -1)
        events._2.filter(_.isBegin).foreach(x => {
          if (cameron == -1) {
            cameron = x.id
            out(x.id) = 'C'
          }
          else if (jamie == -1) {
            jamie = x.id
            out(x.id) = 'J'
          }
          else isPossible = false
        })
      }
      if (isPossible)
        println(s"Case #$i: ${new String(out)}")
      else
        println(s"Case #$i: IMPOSSIBLE")
    }
  }

  parenting(System.in, System.out)
//  parenting(new FileInputStream("io/20/qual/parenting.in"), System.out)
}
