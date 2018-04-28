package dissystems.lesson2;

import dissystems.utils.Util;

import java.util.Random;

/**
 * Created by apaud on 4/24/18.
 */
public class MyThread extends Thread{

	long numberOfExec = 0;

	long expectedExecution = -1;

	private int myId;

	private Lock lock;

	private Random random = new Random();

	public MyThread(int myId, Lock lock) {
		this.myId = myId;
		this.lock = lock;
	}

	void nonCS(){
		System.out.println(this.myId + " is in non CS");
		Util.mySleep(random.nextInt(1000));
	}

	void cs(){
		Util.mySleep(random.nextInt(1000));
		System.out.println(this.myId + " in CS");
		//Util.mySleep(random.nextInt(1000));
	}

	@Override
	public void run() {
		while(expectedExecution!=numberOfExec){
			lock.requestCS(this.myId);
			cs();
			lock.releaseCS(this.myId);
			nonCS();
			++numberOfExec;
		}
	}

	public void setExpectedExecution(long expectedExecution){
		this.expectedExecution = expectedExecution;
	}
}
