package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public class Dekker implements Lock {
	volatile boolean[] wantCS = new boolean[2];
	volatile int favoured = 1;

	@Override
	public void requestCS(int pid) {
		wantCS[pid] = true;
		int j = 1-pid;
		while(wantCS[j]){
			if(favoured == j){
				wantCS[pid] = false;
				while (favoured == j);
				wantCS[pid]  = true;
			}
		}
	}

	@Override
	public void releaseCS(int pid) {
		wantCS[pid] = false;
		favoured = 1-pid;
	}
}
