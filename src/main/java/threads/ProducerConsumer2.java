package threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer2 {

	public static void main(String[] args) {
		DataQueue2 queue = new DataQueue2();
		Producer2 p = new Producer2(queue);
		Consumer2 c = new Consumer2(queue);
		p.start();
		c.start();
	}
}

class DataQueue2 {
	
	private static final int CAPACITY = 5;
	private Queue<Integer> queue = new LinkedList<>();
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public boolean isFull() {
		return queue.size() == CAPACITY;
	}
	
	public Integer dequeue() throws InterruptedException {
		int value;
		lock.lock();
		if (isEmpty()) {
			cond.await();
		}
		value = queue.poll();
		System.out.println("Dequeue: " + value);
		cond.signal();
		lock.unlock();
		return value;
	}
	
	public void enqueue(int value) throws InterruptedException {
		lock.lock();
		if (isFull()) {
			cond.await();
		}
		System.out.println("Enqueue: " + value);
		queue.add(value);
		cond.signal();
		lock.unlock();
		Thread.sleep(50);
	}
}

class Producer2 extends Thread {

	private static final int LIMIT = 100;
	private DataQueue2 queue;
	
	public Producer2(DataQueue2 queue) {
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

class Consumer2 extends Thread {
	
	private static final int LIMIT = 100;
	private DataQueue2 queue;
	
	public Consumer2(DataQueue2 queue) {
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

