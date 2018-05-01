package dissystems.lesson3.diningphiloshopher;

import dissystems.lesson3.BinarySemaphoreV1;
import dissystems.lesson3.BinarySemaphoreV2;
import dissystems.lesson3.Resource;
import dissystems.lesson3.Semaphore;

/**
 * Created by apaud on 4/28/18.
 */
public class DiningPhiloshoperSemaphore implements Resource{

	int num;
	Semaphore[] forks;

	public DiningPhiloshoperSemaphore(int num) {
		this.num = num;
		this.forks = new BinarySemaphoreV2[num];
		for(int i=0; i<num; i++){
			forks[i] = new BinarySemaphoreV2();
		}
	}

	@Override
	public void acquire(int pid) {
		forks[pid].P();
		forks[(pid + 1)%num].P();
		System.out.println(Thread.currentThread().getName()+" philoshoper is eating. with forks "+pid+" and "+(pid + 1)%num);
	}

	@Override
	public void release(int pid) {
		forks[pid].V();
		forks[(pid + 1)%num].V();
		System.out.println(Thread.currentThread().getName()+" philoshoper released forks "+pid+" and "+(pid + 1)%num);
	}
}
