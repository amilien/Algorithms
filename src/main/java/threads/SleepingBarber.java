package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SleepingBarber {

	static int CHAIRS = 5;
	
	BlockingQueue<Integer> mQueue = new ArrayBlockingQueue<Integer>(CHAIRS);
	
	class Barber extends Thread {
		
		@Override
		public void run() {
			while (true) {
				try {
					Integer customer = mQueue.poll(4000, TimeUnit.MILLISECONDS);
					if (customer == null)
						break;
					System.out.println("Handling customer " + customer);
					sleep(2000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	class Customer extends Thread {
		
		private int mId;
		
		public Customer(int id) {
			mId = id;
		}
		
		@Override
		public void run() {
			try {
				mQueue.add(mId);
			} catch (IllegalStateException e) {
				System.out.println("No available chair for " + mId + ", leaving...");
			}
		}
	}
	
	public static void main(String[] args) {
		SleepingBarber instance = new SleepingBarber();
		Barber barber = instance.new Barber();
		barber.start();
		for (int i = 0; i < 15; i++) {
			Customer customer = instance.new Customer(i);
			customer.start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

}
