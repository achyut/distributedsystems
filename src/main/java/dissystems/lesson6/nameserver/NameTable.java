package dissystems.lesson6.nameserver;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/13/18.
 */
public class NameTable {
	final int maxSize = 100;
	private String[] domainNames = new String[maxSize];
	private String[] ipAddresses = new String[maxSize];
	private int[] ports = new int[maxSize];

	private int dirSize = 0;

	int search(String domainName) {
		for(int i=0;i<dirSize;i++){
			if (domainNames[i].equals(domainName)){
				return i;
			}
		}
		return -1;
	}


	int insert(String domainName, String ipAddress, int portNumber) {
		int oldIndex = search(domainName);
		if( (oldIndex == -1) && (dirSize < maxSize)) {
			domainNames[dirSize] = domainName;
			ipAddresses[dirSize] = ipAddress;
			ports[dirSize] = portNumber;
			dirSize ++;
			return 1;
		}
		return 0;
	}

	int getPort(int index){
		return ports[index];
	}

	String getIpAddress(int index) {
		return ipAddresses[index];
	}
}
