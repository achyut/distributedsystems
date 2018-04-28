package dissystems.lesson3;

/**
 * Created by apaud on 4/28/18.
 */
public class ProducerConsumer {
	int n;
	int currProd = -1;
	int currCons = -1;
	double[] buffer;

	BinarySemaphoreV2 mutex = new BinarySemaphoreV2();
	CountingSemaphore PS;
	CountingSemaphore CS;

	public ProducerConsumer(int n) {
		this.n = n;
		PS = new CountingSemaphore(n);
		CS = new CountingSemaphore(0);
		buffer = new double[n];
	}

	public void produce(double value){
		PS.P();
		mutex.P();
		currProd = (currProd + 1) % n;
		buffer[currProd] = value;
		System.out.println(Thread.currentThread().getName()+" produced value "+value);
		mutex.V();
		CS.V();
	}

	public  double consume(){
		CS.P();
		mutex.P();
		currCons = (currCons + 1)%n;
		double val = buffer[currCons];
		System.out.println(Thread.currentThread().getName()+" is consuming value "+val);
		mutex.V();
		PS.V();
		return val;
	}
}
