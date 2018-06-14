package dissystems.lesson6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/14/18.
 */
public class Linker {

	PrintWriter[] dataOut;
	BufferedReader[] dataIn;
	BufferedReader dIn;
	int myId, N;

	Connector connector;

	List<Integer> neighbors = new ArrayList<Integer>();

	public Linker(String baseName, int id, int numProc) throws IOException, InterruptedException {
		myId = id;
		N = numProc;

		dataIn = new BufferedReader[numProc];
		dataOut = new PrintWriter[numProc];

		Topology.readNeighbors(myId,numProc,neighbors);
		connector = new Connector();
		connector.connect(baseName, myId, numProc, dataIn, dataOut);
	}

	public void sendMsg(int dstid, String tag, String msg){
		dataOut[dstid].println(myId+" "+dstid+" "+tag+" "+msg+"#");
		dataOut[dstid].flush();
	}

	public void sendMsg(int dstId, String tag){
		sendMsg(dstId, tag, "0");
	}

	public void multicast(List<Integer> destIds, String tag, String msg){
		for(Integer destId : destIds){
			sendMsg(destId, tag, msg);
		}
	}

	public Msg receiveMsg(int fromId) throws IOException {
		String line = dataIn[fromId].readLine();
		System.out.println("Received message from "+ fromId +": "+line);
		StringTokenizer st = new StringTokenizer(line);
		return Msg.parseMsg(st);
	}

	/**
	 * getter method for myId.
	 *
	 * @return myId - returns myId
	 */
	public int getMyId() {
		return myId;
	}

	/**
	 * getter method for N.
	 *
	 * @return N - returns N
	 */
	public int getNumProc() {
		return N;
	}

	public void close(){
		connector.closeSockets();
	}
}
