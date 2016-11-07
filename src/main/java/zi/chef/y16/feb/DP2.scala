package zi.chef.y16.feb

import scala.collection.mutable

/**
  * Created by balaudt on 8/10/16.
  */
object DP2 extends App {
  val memo = mutable.HashMap[(Int, Boolean, Byte), Long]()
  memo.put((1, true, 0), 2l)
  memo.put((1, true, 1), 2l)
  memo.put((1, true, 2), 1l)
  memo.put((1, false, 0), 3l)
  memo.put((1, false, 1), 3l)
  memo.put((1, false, 2), 2l)

  def getCount(n: Int, containsB: Boolean, endC: Byte): Long = {
    memo.get((n, containsB, endC)) match {
      case Some(x) => return x
      case None => {
        var ans = 0l
        if (!containsB)
          ans += getCount(n - 1, true, 0)
        if (endC < 2)
          ans += getCount(n - 1, containsB, (endC + 1).toByte)
        ans += getCount(n - 1, containsB, endC)
        ans
      }
    }
  }

  println(getCount(3, false, 0))
}
