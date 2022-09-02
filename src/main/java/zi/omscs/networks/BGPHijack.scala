package zi.omscs.networks

import java.io.{BufferedWriter, FileWriter}

import scala.collection.{SortedMap, mutable}

object BGPHijack extends App {
  def generateConfigs(): Unit = {
    val topology = SortedMap(1 -> List(2, 3), 2 -> List(1, 3, 4, 5), 3 -> List(1, 2, 4, 5), 4 -> List(2, 3, 5),
      5 -> List(2, 3, 4, 6), 6 -> List(5))
    val linkSubnets = mutable.Map[(Int, Int), Int]()
    var linkSubnetCt = 0
    val builder = new StringBuilder()
    val isPart4 = true
    val dir = System.getProperty("user.dir")
    for (i <- 1 to 5) builder.append(s"ip prefix-list PLIST-$i permit ${10 + i}.0.0.0/8\n")
    builder.append('\n')
    for (i <- 1 to 5) builder.append(s"ip as-path access-list AC-$i permit _$i$$\n")
    builder.append('\n')
    for (i <- 1 to 5) {
      builder.append(s"route-map MAP-PLIST-SOURCE-AS permit ${10 * i}\n")
      builder.append(s"\tmatch ip address prefix-list PLIST-$i\n")
      builder.append(s"\tmatch as-path AC-$i\n")
    }
    builder.append("route-map MAP-PLIST-SOURCE-AS deny 60\n")
    builder.append('\n')
    val staticConfig = builder.toString()
    for ((id, peers) <- topology) {
      val zebraWriter = new BufferedWriter(new FileWriter(s"$dir/conf/zebra-R$id.conf"))
      val bgpWriter = new BufferedWriter(new FileWriter(s"$dir/conf/bgpd-R$id.conf"))

      zebraWriter.write(s"hostname R$id\n")
      zebraWriter.write("password en\n")
      zebraWriter.write("enable password en\n")
      zebraWriter.write("\n")

      bgpWriter.write(s"hostname bgpd-R$id\n")
      bgpWriter.write("password en\n")
      bgpWriter.write("enable password en\n")
      bgpWriter.write("\n")
      if (isPart4)
        bgpWriter.write(staticConfig)
      bgpWriter.write(s"router bgp $id\n")
      if (isPart4)
        bgpWriter.write("\tredistribute connected\n")

      zebraWriter.write("interface lo\n")
      zebraWriter.write("\tip address 127.0.0.1/32\n")
      val subnet = if (id == 6) 1 else id
      for (i <- 1 to 2) {
        zebraWriter.write(s"interface R$id-eth$i\n")
        zebraWriter.write(s"\tip address 1$subnet.0.$i.254/24\n")
      }
      var ct = 3
      var routerId = 0
      for (peer <- peers) {
        zebraWriter.write(s"interface R$id-eth$ct\n")
        val subnet = if (peer < id) (linkSubnets((peer, id)), 2) else {
          linkSubnetCt += 1
          linkSubnets += (id, peer) -> linkSubnetCt
          routerId = linkSubnetCt
          (linkSubnetCt, 1)
        }
        zebraWriter.write(s"\tip address 9.0.${subnet._1}.${subnet._2}/24\n")
        ct += 1
      }
      zebraWriter.write("\n")

      bgpWriter.write(s"\tbgp router-id 9.0.$routerId.1\n")
      bgpWriter.write(s"\tnetwork 1$subnet.0.0.0/8\n")
      for (peer <- peers) {
        val subnet = if (id < peer) linkSubnets((id, peer)) else linkSubnets((peer, id))
        var configs = List(s"remote-as $peer", "timers 5 5")
        val address = if (id < peer) 2 else 1
        if (isPart4)
          configs = configs ++ List("soft-reconfiguration inbound", "route-map MAP-PLIST-SOURCE-AS in")
        configs.foreach(config => {
          bgpWriter.write(s"\tneighbor 9.0.$subnet.$address $config\n")
        })
      }
      bgpWriter.write("\n")

      zebraWriter.write(s"log file /tmp/R$id.log\n")
      zebraWriter.close()

      bgpWriter.write(s"log file /tmp/R$id-bgpd.log\n")
      List("as4", "events", "filters", "fsm", "keepalives", "updates").foreach(param =>
        bgpWriter.write(s"debug bgp $param\n"))
      bgpWriter.write("log stdout\n")
      bgpWriter.close()

      bgpWriter.close()
    }
  }

  generateConfigs()
}
