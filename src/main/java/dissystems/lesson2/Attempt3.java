package dissystems.lesson2;

/**
 * Deadlock example of CS
 * Created by apaud on 4/24/18.
 */
public class Attempt3 implements Lock {

	boolean[] wantCS = new boolean[2];

	@Override
	public void requestCS(int pid) {
		wantCS[pid] = true;
		if(pid == 0){
			while(wantCS[1]);
		}
		if(pid == 1){
			while(wantCS[0]);
		}
	}

	@Override
	public void releaseCS(int pid) {
		wantCS[pid] = false;
	}
}
