package dissystems.lesson3;

/**
 * Created by apaud on 4/30/18.
 */
public class Bcell {
	int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}

	public void swap(Bcell cell){
		int tmp = cell.value;
		cell.value = this.value;
		this.value = tmp;
	}
}
