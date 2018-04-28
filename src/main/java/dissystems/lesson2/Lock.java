package dissystems.lesson2;

/**
 * Created by apaud on 4/24/18.
 */
public interface Lock {

	void requestCS(int pid);
	void releaseCS(int pid);
}
