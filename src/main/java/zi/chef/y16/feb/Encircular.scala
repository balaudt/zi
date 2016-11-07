package zi.chef.y16.feb

import scala.io.Source

/**
  * Created by balaudt on 8/18/16.
  */
object Encircular extends App {
  println(doesCircleExists(Array("G","GRGL")).mkString("\n"))

  def doesCircleExists(commands: Array[String]): Array[String] = {
    commands.map(com => if (doesCircle(com)) "YES" else "NO")

  }

  object Direction extends Enumeration {
    val North, South, East, West = Value
  }

  def doesCircle(s: String): Boolean = {
    val c = s.toCharArray
    var (x, y, dir) = (0, 0, Direction.North)
    for (i <- 0 until c.length) {
      for (j <- 0 until c.length) {
        c(j) match {
          case 'G' =>
            dir match {
              case Direction.North => y += 1
              case Direction.South => y -= 1
              case Direction.East => x += 1
              case Direction.West => x -= 1
            }
          case 'L' => dir match {
            case Direction.North => dir = Direction.West
            case Direction.South => dir = Direction.East
            case Direction.East => dir = Direction.North
            case Direction.West => dir = Direction.South
          }
          case 'R' => dir match {
            case Direction.North => dir = Direction.East
            case Direction.South => dir = Direction.West
            case Direction.East => dir = Direction.South
            case Direction.West => dir = Direction.North
          }
        }
      }
      println((x,y))
      if (x == 0 && y == 0)
        return true
    }
    false
  }
}
