package dissystems.utils;

/**
 * Created by apaud on 4/24/18.
 */
public class Util {
	public static void mySleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void myWait(Object obj){
		try {
			obj.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
