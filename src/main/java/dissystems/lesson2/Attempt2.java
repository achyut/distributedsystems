package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public class Attempt2 implements Lock {

	volatile boolean openDoor = true;

	@Override
	public void requestCS(int pid) {
		while(!openDoor);
		openDoor = false;
	}

	@Override
	public void releaseCS(int pid) {
		openDoor = true;
	}
}
