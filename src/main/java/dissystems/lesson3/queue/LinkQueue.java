package dissystems.lesson3.queue;

import dissystems.utils.Util;

/**
 * Created by apaud on 4/30/18.
 */
public class LinkQueue {
	Node head;
	Node tail;
	int count;

	public synchronized void enQueue(int data){
		Node node = new Node();
		node.data = data;
		node.next = null;
		if(head==null){
			head = node;
			tail = node;
		}
		else{
			tail.next = node;
			tail = node;
		}
		count++;
		notify();
	}

	public synchronized int deQueue(){
		while (head==null){
			Util.myWait(this);
		}
		int value = head.data;
		head = head.next;
		count--;
		return value;
	}

	public void printQueue(){
		Node tmp = head;
		while(tmp!=null){
			System.out.print(tmp.data+"->");
			tmp = tmp.next;
		}
		System.out.print("/"+System.lineSeparator());
	}

	class Node {
		int data;
		Node next;
	}
}
