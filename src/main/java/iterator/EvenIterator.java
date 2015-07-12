package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EvenIterator implements Iterator<Integer> {

	private Iterator<Integer> iterator;
	private Integer next;

	public EvenIterator(Iterator<Integer> it) {
		iterator = it;
		setNext();
	}

	@Override
	public boolean hasNext() {
		boolean hasNext = next != null;
		return hasNext;
	}

	@Override
	public Integer next() {
		Integer res = next;
		setNext();
		return res;
	}

	private void setNext() {
		while (iterator.hasNext()) {
			Integer val = iterator.next();
			if (val % 2 == 0) {
				next = val;
				return;
			}
		}
		next = null;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);list.add(7);
		EvenIterator myIterator = new EvenIterator(list.iterator());
		while (myIterator.hasNext()) {
			System.out.print(myIterator.next() + " ");
		}
	}

}
