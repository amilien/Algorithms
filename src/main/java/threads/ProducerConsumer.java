package threads;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

	public static void main(String[] args) {
		DataQueue queue = new DataQueue();
		Producer p = new Producer(queue);
		Consumer c = new Consumer(queue);
		p.start();
		c.start();
	}
}

class DataQueue {
	
	private static final int CAPACITY = 5;
	private Queue<Integer> queue = new LinkedList<>();
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public boolean isFull() {
		return queue.size() == CAPACITY;
	}
	
	public Integer dequeue() throws InterruptedException {
		int value;
		synchronized (queue) {
			while (isEmpty()) {
				queue.wait();
			}
			value = queue.poll();
			System.out.println("Dequeue: " + value);
			queue.notify();
		}
		return value;
	}
	
	public void enqueue(int value) throws InterruptedException {
		synchronized (queue) {
			while (isFull()) {
				queue.wait();
			}
			System.out.println("Enqueue: " + value);
			queue.add(value);
			queue.notify();
		}
		//Thread.sleep(50);
	}
}

class Producer extends Thread {

	private static final int LIMIT = 100;
	private DataQueue queue;
	
	public Producer(DataQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < LIMIT; i++) {
				queue.enqueue(i);
			}
		} catch (InterruptedException e) {
		}
	}
}

class Consumer extends Thread {
	
	private static final int LIMIT = 100;
	private DataQueue queue;
	
	public Consumer(DataQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < LIMIT; i++) {
				queue.dequeue();
			}
		} catch (InterruptedException e) {
		}
	}
}

