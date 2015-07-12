package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyPriorityQueue {

	static Comparator<Integer> mComparator = new Comparator<Integer>() {

		private boolean isOdd(Integer o) {
			return o % 2 == 0 ? false : true;
		}
		
		private boolean isEven(Integer o) {
			return o % 2 == 0 ? true : false;
		}
		
		@Override
		public int compare(Integer o1, Integer o2) {
			if (isEven(o1) && isEven(o2) || isOdd(o1) && isOdd(o2)) {
				if (o1 < o2)
					return -1;
				else if (o1 > o2)
					return 1;
				else
					return 0;
			} else if (isEven(o1) && isOdd(o2))
				return -1;
			else
				return 1;
		}
	};
	
	static Queue<Integer> mQueue = new PriorityQueue<Integer>(mComparator);
	
	public static void main(String[] args) {
		mQueue.add(5);
		mQueue.add(2);
		mQueue.add(1);
		mQueue.add(4);
		while (!mQueue.isEmpty())
			System.out.println(mQueue.poll());
	}

}
