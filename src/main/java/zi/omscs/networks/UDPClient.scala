package zi.omscs.networks

import java.net.{DatagramPacket, DatagramSocket, InetAddress}

import scala.util.Random

object UDPClient {

  def main(args: Array[String]): Unit = {
    val socket = new DatagramSocket()
    val data = new Array[Byte](1)
    new Random().nextBytes(data)
    socket.send(new DatagramPacket(data, 1, InetAddress.getLocalHost, 4321))
  }
}
