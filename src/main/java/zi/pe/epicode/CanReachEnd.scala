package zi.pe.epicode

object CanReachEnd extends App {
  println(Array(3, 3, 1, 0, 2, 0, 1))

  /**
    * Write a program which takes an array of n integers, where a[i] denotes the maximum
    * you can advance from index i, and returns whether it is possible to advance to the
    * last index starting from the beginning of the array
    */
  def canReachEnd(a: Array[Int]): Boolean = {
    val lastIndex = a.length - 1
    var (furthestReachSoFar, i) = (0, 0)
    while (i <= furthestReachSoFar && furthestReachSoFar < lastIndex) {
      furthestReachSoFar = Math.max(furthestReachSoFar, i + a(i))
      i += 1
    }
    furthestReachSoFar >= lastIndex
  }
}