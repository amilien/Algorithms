package linkedlist;

public class MyQueue<T> {

	Node<T> head, tail;

	public MyQueue() {
		head = tail = null;
	}

	static class Node<T> {
		
		Node<T> next;
		T value;
		
		Node(T t) {
			value = t;
		}
	}

	void enqueue(T t) {
		Node<T> node = new Node<T>(t);
		node.next = null;
		if (head == null)
			head = node;
		if (tail == null)
			tail = node;
		else {
			tail.next = node;
			tail = node;
		}
	}
	
	T dequeue() {
		if (head == null)
			return null;
		Node<T> node = head;
		head = head.next;
		if (head == null)
			tail = null;
		return node.value;
	}
	
	boolean isLooped() {
		if (head == null)
			return false;
		Node<T> slow = head;
		Node<T> fast = head;
		while (true) {
			slow = slow.next;
			if (fast.next == null)
				return false;
			else
				fast = fast.next.next;
			if (slow == null || fast == null)
				return false;
			if (slow == fast)
				return true;
		}
	}
	
	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		//queue.tail.next = queue.head;
		System.out.println(queue.isLooped());
	}

}
