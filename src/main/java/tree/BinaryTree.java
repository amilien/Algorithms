package tree;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTree {

    static class Node {
        Node left;
        Node right;
        int value;
        
        Node(int v) {
            left = right = null;
            value = v;
        }
        
        @Override
        public int hashCode() {
        	final int prime = 17;
        	int hash = prime * value;
        	return hash;
        }
        
        @Override
        public boolean equals(Object node) {
        	if (!(node instanceof Node))
        		return false;
        	Node tmp = (Node) node;
        	if (value == tmp.value)
        		return true;
        	return false;
        }
    }
    
    static Node getNode(Node root, int value) {
        Node result = null;
        if (root != null) {
            if (root.value == value)
                return root;
            result = getNode(root.left, value);
            if (result == null)
                result = getNode(root.right, value);
        }
        return result;
    }
    
    static Node getParent(Node root, Node node) {
        Node result = null;
        if (root != null) {
        	if (root.left != null && root.left.equals(node) ||
        		root.right != null && root.right.equals(node))
                return root;
            result = getParent(root.left, node);
            if (result == null)
                result = getParent(root.right, node);
        }
        return result;
    }
    
    // preorder
    static void vlr(Node root) {
        if (root == null)
            return;
        System.out.println(root.value);
        vlr(root.left);
        vlr(root.right);
    }
    
    // inorder
    static void lvr(Node root) {
        if (root == null)
            return;
        lvr(root.left);
        System.out.println(root.value);
        lvr(root.right);
    }
    
    // postorder
    static void lrv(Node root) {
        if (root == null)
            return;
        lrv(root.left);
        lrv(root.right);
        System.out.println(root.value);
    }
    
    // breadth-first
    static void bfs(Node root) {
        if (root == null)
            return;
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(root);
    	while (!queue.isEmpty()) {
    		Node cur = queue.poll();
    		if (cur != null) {
	    		System.out.print(cur.value + " ");
	    		if (cur.left != null)
	    			queue.add(cur.left);
	    		if (cur.right != null)
	    			queue.add(cur.right);
    		}
    	}
    }
    
    // get number of elements
    static int count(Node root) {
    	return 1 + (root.left != null ? count(root.left) : 0) + (root.right != null ? count(root.right) : 0);
    }
    
    // get max depth
    static int maxDepth(Node root) {
    	if (root == null)
    		return 0;
    	return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    // get min depth
    static int minDepth(Node root) {
    	int depth = 0;
        if (root == null)
            return depth;
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(root);
    	while (!queue.isEmpty()) {
    		Node cur = queue.poll();
    		depth++;
    		if (cur != null) {
	    		if (cur.left != null)
	    			queue.add(cur.left);
	    		else
	    			break;
	    		if (cur.right != null)
	    			queue.add(cur.right);
	    		else
	    			break;
    		}
    	}
    	return depth;
    }
    
    static Node lca(Node root, Node node1, Node node2) {
    	Node cur = node1;
    	LinkedList<Node> list1 = new LinkedList<Node>();
    	while (cur != root) {
    		cur = getParent(root, cur);
    		list1.add(cur);
    		if (cur.equals(node2))
    			return node2;
    	}
    	cur = node2;
    	LinkedList<Node> list2 = new LinkedList<Node>();
    	while (cur != root) {
    		cur = getParent(root, cur);
    		list2.add(cur);
    		if (cur.equals(node1))
    			return node1;
    	}
    	cur = null;
    	while (!list1.isEmpty() && !list2.isEmpty()) {
    		Node tmp1 = list1.removeLast();
    		Node tmp2 = list2.removeLast();
    		if (tmp1.equals(tmp2))
    			cur = tmp1;
    		else
    			return cur;
    	}
    	return cur;
    }
    
    static Node LCA(Node root, Node a, Node b) {
    	if (root == null)
    		return null;
    	if (root.equals(a) || root.equals(b))
    		return root; // if at least one matched, no need to continue, this is the LCA for this root
    	Node left = null, right = null;
    	left = LCA(root.left, a, b);
    	right = LCA(root.right, a, b);
    	if (left != null && right != null)
    		return root; // nodes are each on a separate branch
    	return (left != null) ? left : right; // either one node is on one branch or none was found in any of the branches
    }
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc = new Scanner(new FileInputStream("sample_input_8.txt"));
        int tc = sc.nextInt();
        int value = sc.nextInt();
        Node current = new Node(value);
        value = sc.nextInt();
        Node next = new Node(value);
        current.left = next;
        Node root = current;
        for (int i = 1; i < tc - 1; i++) {
            value = sc.nextInt();
            current = getNode(root, value);
            value = sc.nextInt();
            next = new Node(value);
            if (current.left == null)
                current.left = next;
            else if (current.right == null)
                current.right = next;
        }
        //lvr(root);
        //bfs(root);
        System.out.println(LCA(root, new Node(12), new Node(13)).value);
    }

}
