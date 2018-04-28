package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public class Runner {

	public static void main(String[] args) {
		int n = 5;
		Lock lk = new HWMutex();
		synchronization(lk,n);
	}

	private static void synchronization(Lock lk,int n) {
		MyThread[] psarr = new MyThread[n];
		for(int i=0;i<n;i++){
			psarr[i] = new MyThread(i,lk);
			psarr[i].start();
			if(i==0){
				//To view progress
				psarr[i].setExpectedExecution(5);
			}
		}
	}
}
