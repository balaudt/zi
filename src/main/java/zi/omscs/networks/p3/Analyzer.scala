package zi.omscs.networks.p3

import java.io.File
import java.sql.{Connection, DriverManager, PreparedStatement}
import java.util

import scala.io.Source

object Analyzer {
  Class.forName("org.postgresql.Driver")
  private val connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pinpoint")
  private val stmt = connection.prepareStatement("insert into tfo.transactions(url, start_ts, end_ts, ts_length, content_length, status, mime_type, domain, is_tfo, request_num, rtt, test_run, site) " +
    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")

  def insertTransaction(site: String, url: String, start: Float, end: Float, ts: Float, contentLength: Int, status: Int,
                        mime: String, domain: String, isTFO: Boolean, requestNum: Int, rtt: Int, testRun: Int): Unit = {
    stmt.setString(1, url)
    stmt.setFloat(2, start)
    stmt.setFloat(3, end)
    stmt.setFloat(4, ts)
    stmt.setInt(5, contentLength)
    stmt.setInt(6, status)
    stmt.setString(7, mime)
    stmt.setString(8, domain)
    stmt.setBoolean(9, isTFO)
    stmt.setInt(10, requestNum)
    stmt.setInt(11, rtt)
    stmt.setInt(12, testRun)
    stmt.setString(13, site)
    stmt.execute()
  }

  def closeDB(connection: Connection): Unit = {
    connection.close()
  }

  def main(args: Array[String]): Unit = {
    val clientBase = "/Users/bchandrasekaran/src/omscs/cs6250/Project3/attempt1/logs/client"
    val fetchPath = "/Users/bchandrasekaran/src/omscs/cs6250/Project3/attempt1/logs/myURLs"
    for (baseUrl <- Set("httpsports.yahoo.com", "httpapache.org", "httpwww.gct.ac.in", "httpwww.nyu.edu")) {
      for (rtt <- Set(10, 50, 100)) {
        for (run <- Set(0, 1)) {
          for (isTFO <- Set(true, false)) {
            val f = Source.fromFile(s"$clientBase/$rtt-$run-tfo-${if (isTFO) 1 else 0}/$baseUrl")
            val fileStr = f.getLines().mkString("\n")
            val str = fileStr.substring(fileStr.indexOf("<transactions>") + 15, fileStr.indexOf("</transactions>") - 1)
            for (line <- str.split("\n").tail) {
              val trans = line.split("\\|").map(_.trim)
              val t = trans.slice(0, 3).map(_.toFloat)
              val (st, end, len) = (t(0), t(1), t(2))
              val url = line.substring(line.indexOf(trans(2)) + trans(2).length + 3)
              val domain = url.split("/")(2)
              val path = url.substring(url.indexOf(domain) + 1 + domain.length)
              val urlFile = new File(s"$fetchPath/$domain/$path")
              val contentLength = if (urlFile.exists()) urlFile.length() else 0
              insertTransaction(baseUrl, url, st, end, len, contentLength.toInt, 0, null, domain, isTFO, 0, rtt, run)
            }
            f.close()
          }
        }
      }
    }
  }

}
