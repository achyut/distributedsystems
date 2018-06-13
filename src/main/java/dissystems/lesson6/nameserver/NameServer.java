package dissystems.lesson6.nameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/13/18.
 */
public class NameServer {

	NameTable table;

	public NameServer(NameTable table) {
		this.table = table;
	}

	void handleClient (Socket client) {

		try {
			BufferedReader din = new BufferedReader(new InputStreamReader(client.getInputStream()));

			PrintWriter pout = new PrintWriter(client.getOutputStream());

			String line = din.readLine();
			System.out.println("Received: "+line);
			StringTokenizer st = new StringTokenizer(line);
			String tag = st.nextToken();

			if(tag.equals("search")) {
				// search logic
				String domainName = st.nextToken();
				int index = table.search(domainName);
				if (index == -1){
					pout.println(-1 +" nullhost");
				}
				else{
					pout.println(table.getPort(index) + " " + table.getIpAddress(index));
				}
			}
			else if (tag.equals("insert")) {
				// insert logic
				String domainName = st.nextToken();
				String ipAddress = st.nextToken();

				int port = Integer.parseInt(st.nextToken());
				int retValue = table.insert(domainName, ipAddress, port);
				pout.println(retValue);
			}
			pout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		NameTable table = new NameTable();
		NameServer server = new NameServer(table);

		int serverPort = 2022;
		System.out.println("Nameserver Started!");

		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			while (true){
				Socket socket = serverSocket.accept();
				server.handleClient(socket);
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
