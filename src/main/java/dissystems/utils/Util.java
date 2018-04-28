package dissystems.utils;

import java.util.Random;

/**
 * Created by apaud on 4/24/18.
 */
public class Util {
	static Random rand = new Random();

	public static void randomSleep(int time){
		mySleep(rand.nextInt(time));
	}
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
