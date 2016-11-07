package zi.chef.y16.feb

import java.io.{FileReader, InputStreamReader, BufferedReader}

/**
  * Created by balaudt on 2/9/16.
  */
object E extends App {
  var reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/E-sample.in"))
  var t = reader.readLine().toInt
  for (i <- 1 to t) {
    var in = reader.readLine().split(" ").map(_.toInt)
    val (n, m, k) = (in(0).toLong, in(1).toLong, in(2).toLong)
    in = reader.readLine().split(" ").map(_.toInt)
    var csum: BigInt = 0
    var funA: BigInt = n * (n + 1)
    funA *= m * (m + 1)
    println(funA)
    var temp: BigInt = 0
    in.foreach(x => {
      val y = (x - 1).toLong
      val (r, c) = (y / m + 1, y % m + 1)
      println((r, c))
      temp = n * (n + 1)
      temp *= c * (c - 1)
      csum += temp
      temp = n * (n + 1)
      temp *= (m - c) * (m - c + 1)
      csum += temp
      csum += 2 * r * (r - 1)
      csum += 2 * (n - r) * (n - r + 1)
      println(csum)
    })
    val ans = k - (BigDecimal.apply(csum) / BigDecimal.apply(funA)).toDouble
    println(ans)
  }
  reader.close()
}
