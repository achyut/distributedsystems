package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public class LamportBakeryUsingSemaphore implements Lock {
	int N = 0;

	volatile int[] assignedNumbers;

	volatile int currentNumber = 0;

	public LamportBakeryUsingSemaphore(int n) {
		N = n;
		assignedNumbers = new int[N];
	}

	@Override
	public void requestCS(int pid) {
		assignedNumbers[pid] = ++currentNumber;
		for(int j=0;j<N;j++){
			while (j!=pid && assignedNumbers[j]!=0 && (assignedNumbers[j] < assignedNumbers[pid] && pid<j));
		}
	}

	@Override
	public void releaseCS(int pid) {
		assignedNumbers[pid] = 0;
		//System.out.println(currentNumber);
		currentNumber--;
	}
}
