package dissystems.lesson6.nameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

import dissystems.utils.PortAddr;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/13/18.
 */
public class NameClient {
	BufferedReader din;
	PrintStream pout;

	String NAMESERVERHOST = "localhost";
	int NAMESERVERPORT = 2022;

	public void getSocket() throws IOException {
		Socket socket = new Socket(NAMESERVERHOST,NAMESERVERPORT);
		din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pout = new PrintStream(socket.getOutputStream());
	}

	public int insertName(String domainName, String ipAddress, int port) throws IOException {
		getSocket();
		pout.println("insert "+domainName+" "+ipAddress+" "+port);
		pout.flush();
		return Integer.parseInt(din.readLine());
	}

	public PortAddr searchName(String domainName) throws IOException {
		getSocket();
		pout.println("search "+domainName);
		pout.flush();

		String result = din.readLine();
		StringTokenizer st = new StringTokenizer(result);
		int port = Integer.parseInt(st.nextToken());
		String ipAddress = st.nextToken();
		return new PortAddr(ipAddress,port);
	}

	public static void main(String[] args) {
		NameClient client = new NameClient();
		try {
			client.insertName("oak.ece.utexas.edu","192.168.10.1",5001);
			PortAddr portAddr = client.searchName("oak.ece.utexas.edu");
			System.out.println(portAddr.getIpAddress()+":"+portAddr.getPortNumber());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
