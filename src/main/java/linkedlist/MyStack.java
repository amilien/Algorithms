package linkedlist;

public class MyStack<T> {

	Node<T> root;
	int size;
	int MAX_SIZE;
	
	public MyStack(int maxSize) {
		root = null;
		size = 0;
		MAX_SIZE = maxSize;
	}
	
	static class Node<T> {
		
		Node<T> next;
		T value;
		
		Node(T t) {
			value = t;
		}
	}

	void push(T t) {
		if (size == MAX_SIZE) {
			root.value = t;
		} else {
			Node<T> node = new Node<T>(t);
			node.next = root;
			root = node;
			size++;
		}
	}

	T pop() {
		if (root == null)
			return null;
		Node<T> node = root;
		root = root.next;
		return node.value;
	}
	
	void reverse() {
		Node<T> cur = root.next;
		root.next = null;
		while (cur != null) {
			Node<T> next = cur.next;
			cur.next = root;
			root = cur;
			cur = next;
		}
	}
	
	void traverse() {
		Node<T> cur = root;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<Integer>(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.traverse();
		stack.reverse();
		stack.traverse();
	}

}
