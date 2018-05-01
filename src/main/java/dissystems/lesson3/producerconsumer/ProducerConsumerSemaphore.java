package dissystems.lesson3.producerconsumer;

import dissystems.lesson3.BinarySemaphoreV2;
import dissystems.lesson3.CountingSemaphore;

/**
 * Created by apaud on 4/28/18.
 */
public class ProducerConsumerSemaphore implements ProducerConsumer{
	private int n;
	private int currProd = -1;
	private int currCons = -1;
	private double[] buffer;

	private BinarySemaphoreV2 mutex = new BinarySemaphoreV2();
	private CountingSemaphore PS;
	private CountingSemaphore CS;

	public ProducerConsumerSemaphore(int n) {
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
