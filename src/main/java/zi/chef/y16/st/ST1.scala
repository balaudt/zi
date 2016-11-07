package zi.chef.y16.st

import zi.chef.y16.feb.A

/**
  * Created by balaudt on 10/8/16.
  */
object ST1 extends App {

  case class A[+T](v: T) {
    def print = v
  }

  def iter(s: Seq[A[CharSequence]]) = s.map(_.print)

  println(iter(Seq(A[StringBuilder](new StringBuilder("Test")), A[String]("Hello"))))
}


