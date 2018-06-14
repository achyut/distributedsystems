package dissystems.lesson6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/13/18.
 */
public class Topology {

	// Method to read a file and find the neighbor
	public static void readNeighbors(int myid, int N, List<Integer> neighbors){
		System.out.println("Reading topology");

		try {
			BufferedReader din = new BufferedReader(new FileReader("topology"+myid));
			StringTokenizer st = new StringTokenizer(din.readLine());
			while (st.hasMoreTokens()){
				int neighbor = Integer.parseInt(st.nextToken());
				neighbors.add(neighbor);
			}
		} catch (FileNotFoundException e) {
			for(int i=0;i<N;i++){
				if(i!=myid){
					neighbors.add(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(neighbors);
	}
}
