package dissystems.lesson6;

import java.util.StringTokenizer;

/**
 * Stub description of the class.
 *
 * @author Achyut Paudel.
 *         Date 6/14/18.
 */
public class Msg {
	int srcId, destId;
	String tag;
	String msgBuf;

	public Msg(int srcId, int destId, String tag, String msgBuf) {
		this.srcId = srcId;
		this.destId = destId;
		this.tag = tag;
		this.msgBuf = msgBuf;
	}

	/**
	 * getter method for srcId.
	 *
	 * @return srcId - returns srcId
	 */
	public int getSrcId() {
		return srcId;
	}

	/**
	 * getter method for destId.
	 *
	 * @return destId - returns destId
	 */
	public int getDestId() {
		return destId;
	}

	/**
	 * getter method for tag.
	 *
	 * @return tag - returns tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * getter method for msgBuf.
	 *
	 * @return msgBuf - returns msgBuf
	 */
	public String getMessage() {
		return msgBuf;
	}

	public int getMessageInt(){
		StringTokenizer st = new StringTokenizer(msgBuf);
		return Integer.parseInt(st.nextToken());
	}

	public static Msg parseMsg(StringTokenizer st){
		int srcId = Integer.parseInt(st.nextToken());
		int destId = Integer.parseInt(st.nextToken());

		String tag = st.nextToken();
		String buf = st.nextToken("#");
		return new Msg(srcId,destId,tag,buf);
	}

	public String toString(){
		String str = String.valueOf(srcId) + " " + String.valueOf(destId) + " " + tag + " " + msgBuf + "#";
		return str;
	}
}
