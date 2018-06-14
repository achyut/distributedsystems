package dissystems.lesson6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import dissystems.lesson6.nameserver.NameClient;
import dissystems.utils.PortAddr;
import dissystems.utils.Symbols;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/13/18.
 */
public class Connector {
	private ServerSocket socket;
	private Socket[] link;

	public void connect(String baseName, int myid, int num, BufferedReader[] dataIn, PrintWriter[] dataOut) throws IOException, InterruptedException {
		NameClient nameClient = new NameClient();
		int localPort = getPort(myid);

		socket = new ServerSocket(localPort);

		nameClient.insertName(baseName+myid,(InetAddress.getLocalHost()).getHostName(), localPort);

		//accepting conenctions from all small processes.

		for(int i=0; i < myid; i++){
			Socket s = socket.accept();
			BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line = din.readLine();
			StringTokenizer st = new StringTokenizer(line);
			int hisid = Integer.parseInt(st.nextToken());
			int destId = Integer.parseInt(st.nextToken());

			String tag = st.nextToken();
			if(tag.equalsIgnoreCase("hello")){
				link[hisid] = s;
				dataIn[hisid] = din;
				dataOut[hisid] = new PrintWriter(s.getOutputStream());
			}
		}

		//contacting all bigger processes.
		for(int i=myid+1; i< num; i++) {
			PortAddr addr;
			do{
				addr = nameClient.searchName(baseName+i);
				Thread.sleep(1000);
			}
			while(addr.getPortNumber() == -1);
			link[i] = new Socket(addr.getIpAddress(), addr.getPortNumber());
			dataOut[i] = new PrintWriter(link[i].getOutputStream());
			dataIn[i] = new BufferedReader(new InputStreamReader(link[i].getInputStream()));

			//send hello to the process higher than you.
			dataOut[i].println(myid + " "+ i + " hello "+ " null");
			dataOut[i].flush();
		}
	}

	private int getPort(int myid) {
		return Symbols.serverPort + 10 + myid;
	}

	public void closeSockets(){
		try {
			socket.close();
			for(int i=0; i<link.length;i++){
				link[i].close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
