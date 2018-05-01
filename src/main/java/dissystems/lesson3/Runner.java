package dissystems.lesson3;

import dissystems.lesson3.diningphiloshopher.DiningPhiloshoperSemaphore;
import dissystems.lesson3.diningphiloshopher.DiningPhiloshopherMonitor;
import dissystems.lesson3.diningphiloshopher.Philoshoper;
import dissystems.lesson3.producerconsumer.*;
import dissystems.lesson3.queue.LinkQueue;
import dissystems.lesson3.readerwriter.ReaderThread;
import dissystems.lesson3.readerwriter.ReaderWriter;
import dissystems.lesson3.readerwriter.WriterThread;
import dissystems.utils.Util;

import java.util.Random;

/**
 * Created by apaud on 4/28/18.
 */
public class Runner {

	public static void main(String[] args) {
		//example();
		//producerconsumer();
		//readerwriter();
		//diningphiloshoper();
		//queue();
		bcell();
	}

	private static void bcell() {

		final Bcell cell1 = new Bcell();
		final Bcell cell2 = new Bcell();

		while(true){
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					cell1.setValue(1);
				}
			});
			th.start();
			System.out.println("Cell1 value: "+cell1.value);
			Thread th2 = new Thread(new Runnable() {
				@Override
				public void run() {
					cell2.setValue(2);
				}
			});
			th2.start();
			System.out.println("Cell2 value: "+cell2.value);
			Thread th3 = new Thread(new Runnable() {
				@Override
				public void run() {
					cell2.swap(cell1);
				}
			});
			th3.start();
			System.out.println("Cell2 value after swap: "+cell2.value);

			Util.randomSleep(5000);
		}


	}

	private static void queue() {
		int num = 5;
		final LinkQueue queue = new LinkQueue();

		for(int i=0;i<10;i++){
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					Random rnd = new Random();
					if(rnd.nextInt(1000)%2==0){
						queue.enQueue(rnd.nextInt(100));
					}
					else{
						System.out.println(queue.deQueue());
					}
					queue.printQueue();
				}
			});
			th.start();
		}
	}

	public static void diningphiloshoper(){
		int num = 5;
		//Resource diningPhiloshoper = new DiningPhiloshoperSemaphore(num);
		Resource diningPhiloshoper = new DiningPhiloshopherMonitor(num);
		for(int i=0;i<num;i++){
			Philoshoper p = new Philoshoper(i,diningPhiloshoper);
			p.start();
		}
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
		//ProducerConsumerSemaphore pc = new ProducerConsumerSemaphore(3);
		ProducerConsumer pc = new ProducerConsumerMonitor(3);

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
