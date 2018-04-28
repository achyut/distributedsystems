package dissystems.lesson3;

/**
 * Created by apaud on 4/28/18.
 */
public class Runner {

	public static void main(String[] args) {
		//example();
		producerconsumer();

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
