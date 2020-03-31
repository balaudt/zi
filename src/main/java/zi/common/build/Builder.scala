package zi.common.build

import java.io.{BufferedWriter, File, FileInputStream, FileWriter}
import java.util

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.ImportDeclaration

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

/**
  * @author balamurugan
  */
object Builder extends App {
  val in = classOf[zi.jam.y08.beta8.C]
  val out = "/Users/balamurugan/Temp/Solution.java"

  val basePath = "/Users/balamurugan/source/playground/"
  val codePackages = Map("zi" -> "zi", "jung.algorithms" -> "jung/jung-algorithms",
    "jung.graph" -> "jung/jung-graph-impl", "jung.layout" -> "jung/jung-algorithms", "psjava" -> "psjava",
    "google.common" -> "guava/guava")
  val imports = new util.ArrayDeque[ImportDeclaration]()
  val importStatements = new ListBuffer[String]()
  val visitedClasses = new mutable.HashMap[String, String]()

  def clzPath(name: String, path: String): String = {
    val names = name.split("\\.")
    val c = names(names.length - 1).charAt(0)
    val isStaticImport = c >= 'a' && c <= 'z'
    val entity = if(isStaticImport) name.substring(0, name.lastIndexOf('.')) else name

    var out: String = null
    if (path.contains("guava"))
      out = basePath + path + "/src/" + entity.replaceAll("\\.", "/") + ".java"
    else
      out = basePath + path + "/src/main/java/" + entity.replaceAll("\\.", "/") + ".java"
    if (!new File(out).exists())
      out = basePath + "jung/jung-api" + "/src/main/java/" + entity.replaceAll("\\.", "/") + ".java"
    out
  }

  def visit(importStr: String, isPublic: Boolean, path: String): Unit = {
    if (visitedClasses.contains(importStr)) {
      return
    }
    val cu = JavaParser.parse(new FileInputStream(clzPath(importStr, path)))
    val ims = new util.ArrayList[ImportDeclaration](cu.getImports)
    ims.asScala.foreach(im => {
      if (!visitedClasses.contains(im.getNameAsString)) {
        imports.add(im)
      }
      cu.remove(im)
    })
    cu.removePackageDeclaration()
    if (!isPublic) {
      cu.getType(0).setPublic(false)
    } else {
      cu.getType(0).setName("Solution")
    }
    visitedClasses += importStr -> cu.toString()
  }

  visit(in.getName, isPublic = true, "zi")
  val imps = new mutable.HashSet[String]()
  breakable {
    while (true) {
      if (!imports.isEmpty) {
        val importStr = imports.remove().getNameAsString
        var isCodePackage = false
        codePackages.foreach(codePackage => {
          if (importStr.contains(codePackage._1)) {
            isCodePackage = true
            visit(importStr, isPublic = false, codePackage._2)
          }
        })
        if (!isCodePackage)
          importStatements += importStr
      } else
        break
    }
  }

  val writer = new BufferedWriter(new FileWriter(out))
  importStatements.map(s => s"import $s};").foreach(i => writer.write(i + "\n"))
  visitedClasses.values.foreach(clz => writer.write(clz + "\n"))
  writer.close()
}
