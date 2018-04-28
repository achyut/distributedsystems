package dissystems.lesson3;

import dissystems.utils.Util;

import java.util.Random;

/**
 * Created by apaud on 4/28/18.
 */
public class ConsumerThread extends Thread {

	ProducerConsumer producerConsumer;
	Random rand = new Random();


	public ConsumerThread(ProducerConsumer producerConsumer){
		this.producerConsumer = producerConsumer;
	}

	@Override
	public void run() {
		while(true){
			double value = producerConsumer.consume();
			System.out.println(Thread.currentThread().getName()+" consumed "+value);
			Util.mySleep(rand.nextInt(1000));
		}
	}
}
