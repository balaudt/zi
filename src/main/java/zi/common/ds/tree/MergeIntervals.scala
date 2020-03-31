package zi.common.ds.tree

/**
  * @author balamurugan
  */
object MergeIntervals extends App {

  class Interval(var _start: Int = 0, var _end: Int = 0) {
    var start: Int = _start
    var end: Int = _end

    override def toString: String = (start, end).toString()
  }

  def mergeIntervals(i1: Inter, i2: Inter): Inter = Inter(Math.min(i1.low, i2.low), Math.max(i1.high, i2.high))

  def merge(intervals: List[Interval]): List[Interval] = {
    val tree = new IntervalTree()
    intervals.foreach(interval => {
      val inter = Inter(interval.start, interval.end)
      val node = tree.overlapSearch(inter)
      if (node != null) {
        tree.remove(node.interval)
        val newInterval = mergeIntervals(node.interval, inter)
        tree.insert(newInterval)
      }
      else tree.insert(inter)
    })
    tree.inOrderList().map(i => new Interval(i.low, i.high)).toList
  }

  //  println(merge(List((1, 3), (2, 6), (8, 10), (15, 18)).map(ind => new Interval(ind._1, ind._2))))
  println(merge(List((303, 306), (59, 60), (119, 128), (201, 208), (219, 226), (430, 433), (38, 43), (175, 182), (424, 430), (417, 421), (130, 136), (48, 53), (436, 438), (494, 500), (48, 51), (327, 328), (236, 242), (484, 491), (347, 352), (4, 11), (335, 341), (62, 67), (151, 151), (4, 11), (496, 497), (484, 492), (177, 181), (373, 378), (337, 346), (441, 448), (13, 14), (89, 90), (359, 364), (309, 316), (463, 468), (126, 134), (304, 312), (23, 30), (278, 287), (235, 241), (300, 301), (105, 114), (455, 455), (295, 299), (50, 58), (382, 385), (209, 212), (164, 173), (248, 253), (128, 131), (343, 347), (361, 368), (294, 300), (454, 457), (115, 116), (51, 58), (112, 118), (97, 105), (487, 491), (212, 219), (332, 338), (452, 454), (269, 275), (463, 463), (442, 443), (433, 438), (345, 352), (284, 293), (235, 243), (64, 67), (280, 286), (470, 472), (124, 133), (312, 313), (163, 168), (0, 6), (471, 475), (390, 393), (421, 424), (315, 320), (296, 296), (0, 1), (247, 251), (132, 137), (374, 381), (390, 394), (165, 165), (399, 408), (132, 133), (253, 258), (88, 93), (143, 143), (410, 413), (5, 6), (238, 238), (46, 50), (272, 280), (27, 29), (34, 34), (207, 215), (209, 216)).map(i => new Interval(i._1, i._2))))
}
