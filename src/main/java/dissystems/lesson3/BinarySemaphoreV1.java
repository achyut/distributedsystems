package dissystems.lesson3;

import dissystems.utils.Util;

/**
 * Created by apaud on 4/28/18.
 */
public class BinarySemaphoreV1 implements Semaphore{

	private boolean value;

	public synchronized void P() {
		while (value);//{
		//	Util.myWait(this);
		//}
		value = true;
	}


	public synchronized void V() {
		value = false;
		//notify();
	}
}
