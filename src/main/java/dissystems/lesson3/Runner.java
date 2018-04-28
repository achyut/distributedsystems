package dissystems.lesson3;

import dissystems.lesson3.producerconsumer.ConsumerThread;
import dissystems.lesson3.producerconsumer.ProducerConsumer;
import dissystems.lesson3.producerconsumer.ProducerThread;
import dissystems.lesson3.readerwriter.ReaderThread;
import dissystems.lesson3.readerwriter.ReaderWriter;
import dissystems.lesson3.readerwriter.WriterThread;

/**
 * Created by apaud on 4/28/18.
 */
public class Runner {

	public static void main(String[] args) {
		//example();
		//producerconsumer();
		readerwriter();
	}

	public static void readerwriter(){
		int currentVal = -1;
		ReaderWriter readerWriter = new ReaderWriter(currentVal);
		for(int i=0;i< 10;i++){
			ReaderThread rn = new ReaderThread(readerWriter);
			rn.start();
		}
		WriterThread w1 = new WriterThread(readerWriter);
		WriterThread w2 = new WriterThread(readerWriter);

		w1.start();
		w2.start();
	}

	public static void producerconsumer(){
		int n = 2;
		ProducerConsumer pc = new ProducerConsumer(3);
		ProducerThread p1 = new ProducerThread(pc);
		ProducerThread p2 = new ProducerThread(pc);
		ConsumerThread c1 = new ConsumerThread(pc);

		p1.start();
		p2.start();
		c1.start();
	}

	private static void example() {
		//Semaphore binSemaphorev1 = new BinarySemaphoreV1();
		//Semaphore binSemaphorev2 = new BinarySemaphoreV2();
		Semaphore countingSemaphore = new CountingSemaphore(1);
		MyThread th1 = new MyThread(1,countingSemaphore);
		MyThread th2 = new MyThread(2,countingSemaphore);

		th1.start();
		th2.start();
	}
}
