package dissystems.lesson3.producerconsumer;

import dissystems.utils.Util;

import java.util.Random;

/**
 * Created by apaud on 4/28/18.
 */
public class ProducerThread extends Thread {

	private ProducerConsumer producerConsumer;
	private Random rand = new Random();

	public ProducerThread(ProducerConsumer producerConsumer){
		this.producerConsumer = producerConsumer;
	}

	@Override
	public void run() {
		while(true){
			System.out.println(Thread.currentThread().getName()+" is producing");
			producerConsumer.produce(rand.nextDouble());
			Util.mySleep(rand.nextInt(1000));
		}
	}
}
