package amazon.tree;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Michael Nikitin
 * 
 * Assumptions:
 * 1. Data structure is a binary tree defined by the Node subclass
 * 2. Program is supplied only with the one simple test case, similar 
 * to the one given in the task description
 * 3. No other test cases are implemented (not required by the task)
 * 4. A smart way of constructing a binary tree from any given sample 
 * input data is not implemented (not required by the task)
 * 
 * Solution:
 * Printing a given binary tree level by level requires a basic breadth-first search algorithm
 * implementation. It should be slightly modified in order to be able to track which level we are
 * printing currently, as well as detecting an end of each level in order to avoid printing 
 * separation symbol (a comma).
 * 
 * Runtime complexity:
 * If there are N nodes in the tree, time complexity is O(N), because this algorithm goes level
 * by level, visiting each node once.
 *
 * Solution can be tested by launching this program. main() function contains sample input data
 * and calls pintTree() function.
 * 
 * Required test cases:
 * 1. Tree is empty (root = null)
 * 2. Tree has one node equal to root
 * 3. Tree has no right (or left) nodes, degraded to linked list
 * 4. Tree is populated with different data inputs
 * ...
 */

public class AmazonTest {

	class Node {
		private int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			value = v;
			left = right = null;
		}
	}

	void printTree(Node root) {
		if (root == null)
			return;
        Queue<Node> currentLevel = new LinkedList<Node>();
        Queue<Node> nextLevel = new LinkedList<Node>();
        currentLevel.add(root);
        while (!currentLevel.isEmpty()) {
        	Node currentNode = currentLevel.poll();
        	if (currentNode != null) {
        		System.out.print(currentNode.value);
                if (currentNode.left != null)
                    nextLevel.add(currentNode.left);
                if (currentNode.right != null)
                    nextLevel.add(currentNode.right);
        	}
        	if (currentLevel.isEmpty()) { // go to next level
        		System.out.println();
				Queue<Node> tmpLevel = currentLevel;
				currentLevel = nextLevel;
				nextLevel = tmpLevel;
        	} else {
        		System.out.print(",");
        	}
        }
    }

    public static void main(String[] args) {
    	AmazonTest instance = new AmazonTest();
    	// sample input integer data
    	int[] input = {5, 3, 1, 9, 4, 5, 2};
    	// construct a sample binary tree from the sample input
        Node current = instance.new Node(input[0]);
        Node root = current;
        Node left = instance.new Node(input[1]);
        current.left = left;
        Node right = instance.new Node(input[2]);
        current.right = right;
        current = instance.new Node(input[3]);
        left.left = current;
        current = instance.new Node(input[4]);
        right.left = current;
        current = instance.new Node(input[5]);
        right.right = current;
        left = instance.new Node(input[6]);
        current.left = left;
        // call the printing tree
        instance.printTree(root);
    }

}