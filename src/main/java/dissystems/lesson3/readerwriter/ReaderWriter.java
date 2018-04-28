package dissystems.lesson3.readerwriter;

import dissystems.lesson3.BinarySemaphoreV2;
import dissystems.lesson3.CountingSemaphore;
import dissystems.lesson3.Semaphore;

/**
 * Created by apaud on 4/28/18.
 */
public class ReaderWriter {

	private int currentValue;
	private Semaphore mutex = new BinarySemaphoreV2();
	private Semaphore writeLock = new BinarySemaphoreV2();
	private int numReaders = 0;

	public ReaderWriter(int currentValue) {
		this.currentValue = currentValue;
	}

	public int read(){
		mutex.P();
		numReaders++;
		if(numReaders == 1){
			writeLock.P();
		}
		int val = currentValue;
		numReaders --;
		if(numReaders == 0){
			writeLock.V();
		}
		System.out.println(Thread.currentThread().getName()+ " reads value "+val);
		mutex.V();
		return val;
	}

	public void write(int val){
		writeLock.P();
		this.currentValue = val;
		System.out.println(Thread.currentThread().getName()+" writes value "+val);
		writeLock.V();
	}
}