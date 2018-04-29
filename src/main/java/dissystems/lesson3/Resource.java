package dissystems.lesson3;

/**
 * Created by apaud on 4/29/18.
 */
public interface Resource {
	public void acquire(int pid);
	public void release(int pid);
}
