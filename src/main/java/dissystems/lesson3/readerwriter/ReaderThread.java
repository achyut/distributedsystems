package dissystems.lesson3.readerwriter;

import dissystems.utils.Util;

import java.util.Random;

/**
 * Created by apaud on 4/28/18.
 */
public class ReaderThread extends Thread {

	ReaderWriter readerWriter;

	public ReaderThread(ReaderWriter readerWriter){
		this.readerWriter = readerWriter;
	}

	@Override
	public void run() {
		while(true){
			int value = readerWriter.read();
			Util.randomSleep(1000);
		}
	}
}
