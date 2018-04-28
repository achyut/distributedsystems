package dissystems.lesson3;

import dissystems.utils.Util;

/**
 * Created by apaud on 4/28/18.
 */
public class CountingSemaphore implements Semaphore{

	int value;

	public CountingSemaphore(int value) {
		this.value = value;
	}

	@Override
	public synchronized void P() {
		while(value<=0){
			Util.myWait(this);
		}
		value--;
	}

	@Override
	public synchronized void V() {
		value++;
		notify();
	}
}
