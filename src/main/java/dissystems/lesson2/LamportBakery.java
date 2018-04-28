package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public class LamportBakery implements Lock {
	int N = 0;

	volatile int[] assignedNumbers;

	volatile boolean[] entering;

	public LamportBakery(int n) {
		N = n;
		assignedNumbers = new int[N];
		entering = new boolean[N];
	}

	@Override
	public void requestCS(int pid) {
		entering[pid] = true;
		for(int j=0;j<N;j++){
			if(assignedNumbers[j]>assignedNumbers[pid]){
				assignedNumbers[pid] = assignedNumbers[j];
			}
		}
		assignedNumbers[pid]++;
		entering[pid] = false;

		for(int i=0;i<N;i++){
			while(entering[i]);
			while (assignedNumbers[i] !=0 && i!=pid && assignedNumbers[i]<assignedNumbers[pid]);
		}
	}

	@Override
	public void releaseCS(int pid) {
		assignedNumbers[pid] = 0;
	}

}
