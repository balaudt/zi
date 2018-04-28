package zi.chef.y17.dec

import java.io._
import java.nio.charset.Charset

import org.apache.commons.io.{FileUtils, IOUtils}
import org.scalatest.FunSuite

import scala.io.Source
import scala.util.Random


/**
 * Created by balamurugan on 12/10/16.
 */
class RosesAlexeyTest extends FunSuite {
  test("Sample case") {
    val os = new ByteArrayOutputStream()
    val ps = new PrintStream(os)
    RosesAlexey.execute(IOUtils.toInputStream("2\n8 4\n1 2 2 3 4 5 5 6\n4 4\n1 1 1 3", Charset.defaultCharset()), ps)
    val output = os.toString("UTF8")
    println(output)
    assert(output.equals("5\n1\n"))
  }

  test("Generated case") {
    val fileBase = "/Users/balamurugan/Temp/dec"
    val inFile = s"$fileBase/roses-gen.in"
    largeGenerate(inFile)
    val solnFile = s"$fileBase/roses.out"
    RosesAlexey.execute(new FileInputStream(inFile), new PrintStream(solnFile))
    val bruteFile = s"$fileBase/roses-brute.out"
    bruteForce(inFile, bruteFile)
    assert(FileUtils.contentEquals(new File(solnFile), new File(bruteFile)))
  }

  test("Handwritten case") {
    RosesAlexey.execute(IOUtils.toInputStream("1\n20 5\n1 1 1 1 2 2 2 2 3 3 3 3 4 4 4 4 5 5 5 5", Charset.defaultCharset()), System.out)
  }

  def generate(file: String) = {
    val ps = new PrintStream(file)
    val k = Random.nextInt(100) + 100
    val n = (Random.nextInt(100) + 100) * k
    ps.println("1")
    ps.println(s"$n $k")
    val a = Array.ofDim[Int](n)
    for (i <- a.indices) a(i) = Random.nextInt(25)
    ps.println(a.mkString(" "))
    ps.close()
  }

  def largeGenerate(file: String) = {
    val ps = new PrintStream(file)
    val t = Random.nextInt(100) + 1
    ps.println(t)
    for (_ <- 1 to t) {
      val factors = Array(2, 3, 4, 6)
      val k = factors(Random.nextInt(4))
      ps.println(s"12 $k")

      val l = Random.nextInt(12) + 1
      val a = Array.ofDim[Int](12)
      for (i <- a.indices) a(i) = Random.nextInt(l) + 1
      ps.println(a.mkString(" "))
    }
    ps.close()
  }

  def bruteForce(inFile: String, outFile: String) = {
    val lines = Source.fromFile(inFile).getLines()
    val ps = new PrintStream(outFile)
    val t = lines.next().trim().toInt
    for (_ <- 1 to t) {
      val nk = lines.next().trim().split(" ").map(_.toInt)
      val (n, k, bc) = (nk(0), nk(1), nk(0) / nk(1))

    }
  }
}
