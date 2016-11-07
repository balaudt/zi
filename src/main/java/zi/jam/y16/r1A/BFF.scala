package zi.jam.y16.r1A

import org.paukov.combinatorics.Factory

import scala.io.Source
import scala.collection.JavaConverters._

/**
  * Created by balaudt on 4/15/16.
  */
object BFF extends App {
  val lineIt = Source.fromInputStream(System.in).getLines()
  val t = lineIt.next().toInt
  for (i <- 1 to t) {
    var n = lineIt.next().toInt
    val bffs = lineIt.next().split(" ").map(_.toInt - 1)
    var flag = false
    var ans = 1
    for (j <- n to 2 by -1 if (!flag)) {
      Factory.createSimpleCombinationGenerator(Factory.createVector((0 until n).asJava), j).asScala.foreach(perm => {
        for (innerPerm <- Factory.createPermutationGenerator(Factory.createVector(perm)).asScala if (!flag)) {
          val permList = innerPerm.getVector.asScala
          var innerFlag = (bffs(permList(0)) == permList(1)) || (bffs(permList(0)) == permList(j - 1))
          innerFlag &= ((bffs(permList(j - 1)) == permList(0)) || (bffs(permList(j - 1)) == permList(j - 2)))
          var ind = 1
          while (innerFlag && ind < j - 1) {
            innerFlag = (bffs(permList(ind)) == permList(ind - 1)) || (bffs(permList(ind)) == permList(ind + 1))
            ind += 1
          }
          if (innerFlag) {
            flag = true
            ans = j
          }
        }
      })
    }
    println(s"Case #$i: $ans")
  }

}
