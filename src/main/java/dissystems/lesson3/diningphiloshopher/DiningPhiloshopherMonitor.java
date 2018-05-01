package dissystems.lesson3.diningphiloshopher;

import dissystems.lesson3.Resource;
import dissystems.utils.Util;

/**
 * Created by apaud on 4/30/18.
 */
public class DiningPhiloshopherMonitor implements Resource{
	int n;
	int[] state;
	static final int thinking = 0, hungry = 1, eating = 2;

	public DiningPhiloshopherMonitor(int n) {
		this.n = n;
		state = new int[n];
		for(int i=0;i<n;i++){
			state[i] = thinking;
		}
	}

	int left(int i){
		return (n + i - 1) % n;
	}

	int right(int i){
		return (i + 1) % n;
	}


	@Override
	public synchronized void acquire(int pid) {
		state[pid] = hungry;
		test(pid);
		while(state[pid]!=eating){
			Util.myWait(this);
		}
		System.out.println(Thread.currentThread().getName()+" philoshoper is eating. with forks "+pid+" and "+(pid + 1)%n);
	}

	@Override
	public synchronized void release(int pid) {
		state[pid] = thinking;
		System.out.println(Thread.currentThread().getName()+" philoshoper released forks "+pid+" and "+(pid + 1)%n);
		test(left(pid));
		test(right(pid));
	}

	protected void test(int pid){
		if(state[left(pid)] != eating && state[right(pid)] !=eating && state[pid]==hungry){
			state[pid] = eating;
			notifyAll();
		}
	}
}
