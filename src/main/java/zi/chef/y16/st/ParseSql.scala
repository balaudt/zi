package zi.chef.y16.st

import com.google.gson.Gson

import java.util
import scala.io.Source

/**
  * Created by balaudt on 10/15/16.
  */
object ParseSql extends App {

  case class Table(var name: String, var columns: util.ArrayList[Column], var indices: util.ArrayList[Index], var constraints: util.ArrayList[ForeignConstraint])

  case class Column(var name: String, var colType: String)

  case class Index(var name: String, var colName: String)

  case class ForeignConstraint(name: String, colName: String, referencedTable: String, referencedColumn: String)

  val tables = new util.ArrayList[Table]()
  var currentTable: Table = null

  def remFirAndLas(in: String) = in.substring(1, in.length - 1)

  Source.fromFile("/Users/balaudt/Temp/sakila-db/sakila-schema.sql").getLines().foreach(line => {
    if (line.startsWith("CREATE")) {
      currentTable = Table(line.split(" ")(2),new util.ArrayList[Column](),new  util.ArrayList[Index](), new util.ArrayList[ForeignConstraint]())
      tables.add(currentTable)
    } else if (line.startsWith(")")) {
    } else {
      val str = line.trim.split(" ")
      str(0) match {
        case "KEY" => currentTable.indices.add(Index(str(1), remFirAndLas(str(2))))
        case "CONSTRAINT" => currentTable.constraints.add(ForeignConstraint(str(1), remFirAndLas(str(4)), str(6), remFirAndLas(str(7))))
        case _ => currentTable.columns.add(Column(str(0),str(1)))
      }
    }
  })
  println(tables)
  println(new Gson().toJsonTree(tables))
}
