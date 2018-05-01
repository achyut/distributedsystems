package dissystems.lesson3.producerconsumer;

import dissystems.utils.Util;

/**
 * Created by apaud on 4/29/18.
 */
public class ProducerConsumerMonitor implements ProducerConsumer{
	int num;
	double[] buffer;
	int inPos;
	int outPos;
	int count;

	public ProducerConsumerMonitor(int num) {
		this.num = num;
		this.buffer = new double[num];
	}

	public synchronized void produce(double value){
		while (count == buffer.length){
			Util.myWait(this);
		}
		buffer[inPos] = value;
		count++;
		inPos = (inPos + 1) % num;
		if(count == 1){
			notify();
		}
	}

	public synchronized double consume(){
		while (count == 0){
			Util.myWait(this);
		}
		double val = buffer[outPos];
		outPos=(outPos+1)%num;
		count--;
		if(count == 0){
			notify();
		}
		return val;
	}
}
