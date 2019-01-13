package zi.jam.y16.r1C

import scala.collection.mutable
import scala.io

/**
  * Created by balaudt on 5/8/16.
  */
object Senate extends App {
  val lineIt = io.Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  val alps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  case class Party(p: Int, ind: Int) extends Ordered[Party] {
    override def compare(that: Party): Int = p compare that.p
  }

  for (i <- 1 to t) {
    print(s"Case #$i: ")
    val n = lineIt.next().toInt
    var party = 0
    val queue = mutable.PriorityQueue[Party]()
    var total = 0
    lineIt.next().split(" ").map(_.toInt).foreach(x => {
      queue += Party(x, party)
      party += 1
      total += x
    })
    while (!queue.isEmpty) {
      val curParty = queue.dequeue()
      if (total == 3) {
        print(alps(curParty.ind))
        total -= 1
        if (curParty.p > 1)
          queue += Party(curParty.p - 1, curParty.ind)
      } else {
        if (!queue.isEmpty && queue.head.p == curParty.p) {
          val anotherParty = queue.dequeue()
          print(alps(curParty.ind))
          print(alps(anotherParty.ind))
          if (anotherParty.p > 1)
            queue += Party(anotherParty.p - 1, anotherParty.ind)
          if (curParty.p > 1)
            queue += Party(curParty.p - 1, curParty.ind)
        } else {
          print(alps(curParty.ind))
          if (curParty.p > 1)
            print(alps(curParty.ind))
          if (curParty.p > 2)
            queue += Party(curParty.p - 2, curParty.ind)
        }
        total -= 2
      }
      print(' ')
    }
    println()
  }
}
