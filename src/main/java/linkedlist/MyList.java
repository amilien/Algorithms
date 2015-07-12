package linkedlist;

import java.util.Stack;

public class MyList {

	Node root;
	
	class Node {
		Node next, random;
		int value;
		
		Node(int t) {
			value = t;
		}
	}

	void create() {
		Node prev = new Node(0);
		root = prev;
		for (int i = 1; i < 10; i++) {
			Node node = new Node(i);
			node.next = null;
			prev.next = node;
			prev = node;
		}
	}
	
	void createDup() {
		Node prev = new Node(0);
		root = prev;
		for (int i = 1; i < 10; i++) {
			Node node = new Node(i == 3 || i == 4 || i == 9 ? 1 : i == 5 || i == 6 || i == 7 ? 2 : i);
			node.next = null;
			prev.next = node;
			prev = node;
		}
	}
	
	void create1() {
		Node prev = new Node(3);
		root = prev;
		Node node = new Node(1);
		prev.next = node;
		prev = node;
		node = new Node(5);
		node.next = null;
		prev.next = node;
	}
	
	void create2() {
		Node prev = new Node(5);
		root = prev;
		Node node = new Node(9);
		prev.next = node;
		prev = node;
		node = new Node(2);
		node.next = null;
		prev.next = node;
	}
	
	void reverse() {
		Node node = root.next;
		root.next = null;
		while (node != null) {
			Node next = node.next;
			node.next = root;
			root = node;
			node = next;
		}
	}
	
	void reverseR(Node curr) {
		if (curr == null)
			return;
		if (curr.next == null) { 
			root = curr;
			return;
		}
		reverseR(curr.next);
		curr.next.next = curr;
		curr.next = null;
	}
	
	void delete(int value) {
		Node cur = root;
		if (cur == null)
			return;
		if (cur.value == value) {
			root = cur.next;
			return;
		}
		while (cur.next != null) {
			if (cur.next.value == value) {
				cur.next = cur.next.next;
				break;
			}
			cur = cur.next;
		}
	}
	
	// remove duplicated elements with O(1)-space
	void removeDups() {
		Node cur = root;
		while (cur != null) {
			Node node = cur;
			while (node.next != null) {
				if (node.next.value == cur.value)
					node.next = node.next.next;
				else
					node = node.next;
			}
			cur = cur.next;
		}
	}
	
	// find the node located on the n-th place from the tail
	Node getNthToLastNode(int n) {
		Node cur = root;
		return getNode(cur, cur, n);
	}
	
	Node getNode(Node head, Node tail, int n) {
		if (tail == null)
			return (n > 0 ? null : head);
		else if (n > 0)
			getNode(head, tail.next, n - 1);
		else
			getNode(head.next, tail.next, n);
		return null;
	}

	// sum lists with reversed digits
	// [3 -> 1 -> 5] + [5 -> 9 -> 2] = [8 -> 0 -> 8]
	Node sum(Node node1, Node node2) {
		Node head = null, previous = null;
		int remainder = 0;
		boolean isHead = true;
		while (node1 != null || node2 != null) {
			int value1 = 0;
			if (node1 != null) {
				value1 = node1.value;
				node1 = node1.next;
			} else
				value1 = 0;
			int value2 = 0;
			if (node2 != null) {
				value2 = node2.value;
				node2 = node2.next;
			} else
				value2 = 0;
			int sum = value1 + value2 + remainder;
			if (sum >= 10) {
				sum = sum % 10;
				remainder = 1;
			} else {
				remainder = 0;
			}
			Node next = new Node(sum);
			if (isHead) {
				head = next;
				isHead = false;
			} else
				previous.next = next;
			previous = next;
		}
		if (remainder == 1) {
			Node next = new Node(1);
			previous.next = next;
		}
		return head;
	}
	
	// copy list with random pointers
	Node copyRandomList() {
		if (root == null)
			return null;
		Node p = root;
		// copy every node and insert to list
		while (p != null) {
			Node copy = new Node(p.value);
			copy.next = p.next;
			p.next = copy;
			p = copy.next;
		}
		// copy random pointer for each new node
		p = root;
		while (p != null) {
			if (p.random != null)
				p.next.random = p.random.next;
			p = p.next.next;
		}
		// break list to two
		p = root;
		Node newHead = p.next;
		while (p != null) {
			Node temp = p.next;
			p.next = temp.next;
			if (temp.next != null)
				temp.next = temp.next.next;
			p = p.next;
		}
		return newHead;
	}
	
	Stack<Integer> sort(Stack<Integer> s) {
		if (s.isEmpty())
			return s;
		int pivot = s.pop();
		// partition
	    Stack<Integer> left = new Stack<Integer>();
	    Stack<Integer> right = new Stack<Integer>();
	    while (!s.isEmpty()) {
	    	int y = s.pop();
	        if (y < pivot)
	        	left.push(y);
	        else
	        	right.push(y);
	    }
	    sort(left);
	    sort(right);
	    // merge
	    Stack<Integer> tmp = new Stack<Integer>();
	    while (!right.isEmpty())
	    	tmp.push(right.pop());
	    tmp.push(pivot);
	    while (!left.isEmpty())
	        tmp.push(left.pop());
	    while (!tmp.isEmpty())
	        s.push(tmp.pop());
	    return s;
	}
	
	void print() {
		Node node = root;
		while (node != null) {
			System.out.print(node.value + " -> ");
			node = node.next;
		}
		System.out.println("null");
	}
	
	public static void main(String[] args) {
		MyList l1 = new MyList();
		MyList l2 = new MyList();
		Stack<Integer> s = new Stack<Integer>();
		s.add(9);s.add(2);s.add(7);s.add(4);s.add(0);s.add(8);s.add(6);s.add(3);s.add(5);s.add(1);
		//create();
		//print();
		//reverseR(root);
		//System.out.println(getNthToLastNode(3));
		//print();
		//s = sort(s);
		//while (!s.isEmpty())
		//	System.out.println(s.pop());
		
		l1.create1();
		l2.create2();
		Node node = l1.sum(l1.root, l2.root);
		while (node != null) {
			System.out.print(node.value + " -> ");
			node = node.next;
		}
		System.out.println("null");
	}

}
