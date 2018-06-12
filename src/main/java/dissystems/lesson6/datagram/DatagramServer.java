package dissystems.lesson6.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/12/18.
 */
public class DatagramServer {
	public static void main(String[] args) {
		DatagramPacket receivedPacket, returnPacket;
		int port = 2022;
		int length = 1024;


			try {
				byte[] data = new byte[1024];
				DatagramSocket datagramSocket = new DatagramSocket(port);
				while(true) {
					try{
						receivedPacket = new DatagramPacket(data, length);

						String receivedString = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
						System.out.println(receivedString);

						datagramSocket.receive(receivedPacket);

						returnPacket = new DatagramPacket(receivedPacket.getData(),
								receivedPacket.getLength(),
								receivedPacket.getAddress(),
								receivedPacket.getPort());
						datagramSocket.send(returnPacket);
					}
					catch(IOException e){
						e.printStackTrace();
					}
				}
			} catch (SocketException e) {
				e.printStackTrace();
			}
	}
}
