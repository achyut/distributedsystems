package dissystems.lesson3;

import dissystems.utils.Util;

/**
 * Created by apaud on 4/28/18.
 */
public class BinarySemaphoreV2 implements Semaphore {

	private boolean value;

	@Override
	public synchronized void P() {
		while(value){
			Util.myWait(this);
		}
		value = true;
	}

	@Override
	public synchronized void V() {
		value = false;
		notify();
	}
}
