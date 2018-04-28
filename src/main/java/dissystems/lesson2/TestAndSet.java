package dissystems.lesson2;

/**
 * Created by apaud on 4/25/18.
 */
public class TestAndSet {
	int myValue = -1;

	public synchronized int testAndSet(int newValue){
		int oldValue = this.myValue;
		myValue = newValue;
		return oldValue;
	}
}
