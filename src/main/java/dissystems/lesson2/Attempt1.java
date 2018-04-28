package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public class Attempt1 implements  Lock{

	volatile int turn = 0;

	@Override
	public void requestCS(int pid) {
		while(turn!=pid);
	}

	@Override
	public void releaseCS(int pid) {
		if(pid==1){
			turn = 0;
		}
		if(pid==0){
			turn = 1;
		}
	}
}
