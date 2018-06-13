package dissystems.utils;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/13/18.
 */
public class PortAddr {
	String ipAddress;
	int portNumber;

	public PortAddr(String ipAddress, int portNumber) {
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	/**
	 * getter method for ipAddress.
	 *
	 * @return ipAddress - returns ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * getter method for portNumber.
	 *
	 * @return portNumber - returns portNumber
	 */
	public int getPortNumber() {
		return portNumber;
	}
}
