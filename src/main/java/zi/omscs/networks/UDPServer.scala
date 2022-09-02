package zi.omscs.networks

import java.net.{DatagramPacket, DatagramSocket}
import java.util

object UDPServer {
  def main(args: Array[String]): Unit = {
    val socket = new DatagramSocket(4321)
    while (true) {
      val packet = new DatagramPacket(new Array[Byte](1), 1)
      socket.receive(packet)
      println(datagramString(packet))
    }
  }

  private def datagramString(packet: DatagramPacket): String = {
    (packet.getAddress, packet.getPort, util.Arrays.toString(packet.getData)).toString
  }
}
