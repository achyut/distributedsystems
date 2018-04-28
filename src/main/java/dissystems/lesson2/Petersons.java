package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public class Petersons implements Lock {
	volatile boolean[] wantCS = new boolean[2];
	volatile int turn = 1;

	@Override
	public void requestCS(int pid) {
		wantCS[pid] = true;
		turn = 1 - pid;
		while (wantCS[1-pid] && turn==1-pid);
	}

	@Override
	public void releaseCS(int pid) {
		wantCS[pid] = false;
	}
}
