package dissystems.lesson2;

/**
 * Created by apaud on 4/25/18.
 */
public class HWMutex implements Lock {

	volatile TestAndSet lock = new TestAndSet();

	@Override
	public void requestCS(int pid) {
		while (lock.testAndSet(1) == 1);
	}

	@Override
	public void releaseCS(int pid) {
		lock.testAndSet(0);
	}
}
