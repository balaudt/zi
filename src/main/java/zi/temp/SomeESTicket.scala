package zi.temp

import com.softwaremill.sttp.HttpURLConnectionBackend

import scala.io.Source
import com.softwaremill.sttp.quick._

/**
 * @author balamurugan
 */
object SomeESTicket extends App {
  implicit val backend = HttpURLConnectionBackend()
  Source.fromFile("/Users/balamurugan/all_shards.txt").getLines()
    .map(_.split("\\s+")(0))
    .filter(_.contains("201903"))
    .foreach(ind => {
      println(ind)
      try {
        println(sttp.delete(uri"http://quasares07.adx.gq1.yahoo.com:4080/${ind}").send().unsafeBody)
      } catch {
        case e: Throwable => e.printStackTrace()
      }
    })
}
