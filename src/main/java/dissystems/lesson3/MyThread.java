package dissystems.lesson3;

import dissystems.utils.Util;

import java.util.Random;

/**
 * Created by apaud on 4/28/18.
 */
public class MyThread extends Thread {
	private int pid;
	private Semaphore semaphore;
	private Random rand = new Random();

	public MyThread(int pid, Semaphore semaphore) {
		this.pid = pid;
		this.semaphore = semaphore;
	}

	public void CS(){
		System.out.println(this.pid+" Inside CS");
	}

	public void nonCS(){
		System.out.println(this.pid+" Not in CS");
	}
	@Override
	public void run() {
		while(true){
			semaphore.P();
			CS();
			semaphore.V();
			nonCS();
			Util.mySleep(rand.nextInt(1000));
		}
	}
}
