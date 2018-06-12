package dissystems.lesson6.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/12/18.
 */
public class DatagramClient {

	public static void main(String[] args) {
		String hostname = "localhost";
		int port = 2022;
		int len = 1024;

		DatagramPacket sendingPacket, receivedPacket;

		if(args.length > 0){
			hostname = args[0];
		}

		try {
			InetAddress inetAddress =  InetAddress.getByName(hostname);
			DatagramSocket socket = new DatagramSocket();
			Scanner sc = new Scanner(System.in);
			String line = "";
			while(true){
				try {
					line = sc.nextLine();
					if (line.equalsIgnoreCase("exit")){
						break;
					}
					byte[] buffer = new byte[line.length()];
					buffer = line.getBytes();
					sendingPacket = new DatagramPacket(buffer,buffer.length,inetAddress,port);
					socket.send(sendingPacket);

					byte[] rbuffer = new byte[len];
					receivedPacket = new DatagramPacket(rbuffer,rbuffer.length);
					socket.receive(receivedPacket);

					String receivedString = new String(receivedPacket.getData(),0,receivedPacket.getLength());
					System.out.println(receivedString);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
