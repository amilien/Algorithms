package threads;

public class ProducerConsumer2 {
	
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		Producer2 producer = new Producer2(buffer, 1);
	    Consumer2 consumer = new Consumer2(buffer, 1);
	    producer.start(); 
	    consumer.start();
	}
}

class Buffer {
	private int contents;
	private boolean available = false;
	
	public synchronized int get() {
		while (available == false) {
			try {
				wait();
			} catch (InterruptedException e) {
		    }
		}
		available = false;
		notifyAll();
		return contents;
	}
	
	public synchronized void put(int value) {
		while (available == true) {
			try {
				wait();
			} catch (InterruptedException e) { 
			} 
		}
		contents = value;
		available = true;
		notifyAll();
	}
}

class Consumer2 extends Thread {
	private Buffer buffer;
	private int number;
		   
	public Consumer2(Buffer buffer, int number) {
		this.buffer = buffer;
		this.number = number;
	}
		   
	public void run() {
		int value = 0;
		for (int i = 0; i < 10; i++) {
			value = buffer.get();
		    System.out.println("Consumer #" + this.number + " got: " + value);
		}
	}
}

class Producer2 extends Thread {
	private Buffer buffer;
	private int number;

	public Producer2(Buffer buffer, int number) {
		this.buffer = buffer;
		this.number = number;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			buffer.put(i);
			System.out.println("Producer #" + this.number + " put: " + i);
			try {
				sleep((int)(Math.random() * 100));
			} catch (InterruptedException e) {
			}
		}
	}
}