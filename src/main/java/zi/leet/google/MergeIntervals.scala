package zi.leet.google

/**
  * @author balamurugan
  */
object MergeIntervals extends App {

  class Interval(var _start: Int = 0, var _end: Int = 0) {
    var start: Int = _start
    var end: Int = _end

    override def toString: String = (start, end).toString()
  }

  def merge(intervals: List[Interval]): List[Interval] = {
    if (intervals.isEmpty) return intervals
    val sortedIntervals = intervals.sortBy(_.start)
    sortedIntervals.foldLeft(scala.collection.mutable.ArrayBuffer(sortedIntervals.head))((intervals, interval) => {
      val lastInterval = intervals(intervals.size - 1)
      if (lastInterval.start <= interval.end && interval.start <= lastInterval.end) {
        lastInterval.end = Math.max(interval.end, lastInterval.end)
      } else {
        intervals += interval
      }
      intervals
    }).toList
  }

  println(merge(List((1, 3), (2, 6), (8, 10), (15, 18)).map(i => new Interval(i._1, i._2))))

}
