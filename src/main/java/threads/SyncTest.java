package threads;

public class SyncTest {

	static SyncTest inst;
	
	class MyThread extends Thread {
		
		boolean b = false;
		
		public MyThread(boolean a) {
			b = a;
		}
		
		@Override
		public void run() {
			if (b)
				a1();
			else
				a2();
		}
	}

	public synchronized void a1() {
		try {
			System.out.println("a1 start");
			Thread.sleep(3000);
			System.out.println("a1 end");
		} catch (InterruptedException e) {
			
		}
	}
	
	public synchronized void a2() {
		try {
			System.out.println("a2 start");
			Thread.sleep(3000);
			System.out.println("a2 end");
		} catch (InterruptedException e) {
			
		}
	}
	
	public static void main(String[] args) {
		inst = new SyncTest();
		MyThread t1 = inst.new MyThread(true);
		MyThread t2 = inst.new MyThread(false);
		t1.start();
		t2.start();
	}

}
