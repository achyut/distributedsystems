package dissystems.lesson3;

import dissystems.utils.Util;

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

	public synchronized void doSwap(Bcell cell){
		int tmp = getValue();
		setValue(cell.getValue());
		cell.value = tmp;
	}
	public void swap(Bcell cell){
		if(this == cell){
			return;
		}
		else if(System.identityHashCode(this) < System.identityHashCode(cell)){
			doSwap(cell);
		}
		else{
			doSwap(this);
		}
	}
}
