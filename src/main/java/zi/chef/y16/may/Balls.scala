package zi.chef.y16.may

/**
  * Created by balaudt on 5/7/16.
  */
object Balls extends App {
  println(1)
  println("4 1 1 2 3")
  println("4 2 4 5 5")
  System.out.flush()
  io.Source.fromInputStream(System.in).bufferedReader().readLine().toInt match {
    case 2 => println("2\n1")
    case 0 => println("2\n2")
    case 1 => println("2\n3")
    case -1 => println("2\n4")
    case -2 => println("2\n5")
  }
}
