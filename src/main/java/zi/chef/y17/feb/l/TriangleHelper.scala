package zi.chef.y17.feb.l

/**
  * Created by balamurugan on 2/5/17.
  */
object TriangleHelper extends App {
  val a = Array(10, 100, 1000, 10000)
  println(a.combinations(2).map(b => (b(1) - b(0) + 1, b(0) + b(1) - 1)).toSeq.sorted.mkString("\n"))
}
