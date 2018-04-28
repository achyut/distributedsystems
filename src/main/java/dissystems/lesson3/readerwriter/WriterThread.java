package dissystems.lesson3.readerwriter;

import dissystems.utils.Util;

import java.util.Random;

/**
 * Created by apaud on 4/28/18.
 */
public class WriterThread extends Thread{

	ReaderWriter readerWriter;

	Random  rand = new Random();

	public WriterThread(ReaderWriter readerWriter) {
		this.readerWriter = readerWriter;
	}

	@Override
	public void run() {
		while(true){
			int val = rand.nextInt(50);
			readerWriter.write(val);
			Util.randomSleep(1000);
		}
	}
}
