package dissystems.lesson3.diningphiloshopher;

import dissystems.utils.Util;

/**
 * Created by apaud on 4/28/18.
 */
public class Philoshoper extends Thread {
	private int pid;
	DiningPhiloshoper diningPhiloshoper;

	public Philoshoper(int pid, DiningPhiloshoper diningPhiloshoper) {
		this.pid = pid;
		this.diningPhiloshoper = diningPhiloshoper;
	}


	@Override
	public void run() {
		while (true){
			System.out.println(Thread.currentThread().getName()+" Philoshopher is thinking..");
			Util.randomSleep(1000);
			System.out.println(Thread.currentThread().getName()+" Philoshopher is hungry");
			diningPhiloshoper.acquire(pid);
			Util.randomSleep(1000);
			System.out.println(Thread.currentThread().getName()+" Philoshopher is eating.");
			diningPhiloshoper.release(pid);
		}
	}
}
