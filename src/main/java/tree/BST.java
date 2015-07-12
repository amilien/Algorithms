package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import tree.BinaryTree.Node;

public class BST<K extends Comparable<K>, V> {
    
    private Node root; // root of BST

    private class Node {
        private K key; // sorted by key
        private V val; // associated data
        private Node left, right; // left and right subtrees
        private int N; // number of nodes in subtree

        public Node(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of nodes in BST
    public int size() {
        return size(root);
    }

    // return number of nodes in BST rooted at x
    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

   /***********************************************************************
    *  Search BST for given key, and return associated value if found,
    *  return null if not found
    ***********************************************************************/
    // does there exist a key-value pair with given key?
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    public Node getNode(K key) {
        return getNode(root, key);
    }

    private Node getNode(Node x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return getNode(x.left, key);
        else if (cmp > 0)
            return getNode(x.right, key);
        else
            return x;
    }
    
    // return value associated with the given key, or null if no such key exists
    public V getValue(K key) {
        return getValue(root, key);
    }

    private V getValue(Node x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return getValue(x.left, key);
        else if (cmp > 0)
            return getValue(x.right, key);
        else
            return x.val;
    }
    
    public Node getParent(K key) {
        return getParent(root, key);
    }
    
    public Node getParent(Node x) {
        return getParent(root, x.key);
    }
    
    private Node getParent(Node x, K key) {
        if (x == null)
            return null;
        if (x.left.key.equals(key) || x.right.key.equals(key))
            return x;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return getParent(x.left, key);
        else if (cmp > 0)
            return getParent(x.right, key);
        else
            return null;
    }
    
   /***********************************************************************
    *  Searching min value in BST
    ***********************************************************************/
    public V min() {
    	if (isEmpty()) return null;
        return min(root).val;
    }
    
    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }
    
    public K max() {
        if (isEmpty()) return null;
        return max(root).key;
    } 

    private Node max(Node x) { 
        if (x.right == null)
        	return x; 
        else
        	return max(x.right); 
    } 

    public int depth() {
        return depth(root);
    }
    
    private int depth(Node x) {
        if (x == null)
            return -1;
        return Math.max(depth(x.left), depth(x.right)) + 1;
    }
    
   /***********************************************************************
    *  Insert key-value pair into BST
    *  If key already exists, update with new value
    ***********************************************************************/
    public void put(K key, V val) {
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        //assert check();
    }

    private Node put(Node x, K key, V val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }    

    /***********************************************************************
     *  Delete
     ***********************************************************************/

     public void deleteMin() {
         if (isEmpty()) throw new NoSuchElementException("Tree is empty");
         root = deleteMin(root);
         //assert check();
     }

     private Node deleteMin(Node x) {
         if (x.left == null) return x.right;
         x.left = deleteMin(x.left);
         x.N = size(x.left) + size(x.right) + 1;
         return x;
     }

     public void deleteMax() {
         if (isEmpty()) throw new NoSuchElementException("Tree is empty");
         root = deleteMax(root);
         //assert check();
     }

     private Node deleteMax(Node x) {
         if (x.right == null) return x.left;
         x.right = deleteMax(x.right);
         x.N = size(x.left) + size(x.right) + 1;
         return x;
     }

    public void delete(K key) {
        root = delete(root, key);
        //assert check();
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    /*************************************************************************
     *  Find successor/predecessor
     *************************************************************************/
    public V findSuccessor(K key) {
        return findSuccessor(getNode(key)).val;
    }

    public Node findSuccessor(Node node) {
        if (node == null)
            return null;
        if (node.right != null)
            return min(node.right);
        Node y = getParent(node);
        Node x = node;
        while (y != null && x == y.right) {
            x = y;
            y = getParent(y);
        }
        return y;
    }

    public V findPredecessor(K key) {
        return findPredecessor(getNode(key)).val;
    }

    public Node findPredecessor(Node node) {
        if (node == null)
            return null;
        if (node.left != null)
            return max(node.left);
        Node y = getParent(node);
        Node x = node;
        while (y != null && x == y.left) {
            x = y;
            y = getParent(y);
        }
        return y;
    }

    public K floor(K key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        else
            return x.key;
    } 

    private Node floor(Node x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null)
            return t;
        else
            return x; 
    } 

    /*************************************************************************
     *  BFS/DFS
     *************************************************************************/
    public void bfs() {
        bfs(root);
    }
    
    private void bfs(Node root) {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.clear();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.val + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }
    
    public void dfs() {
        //dfs(root);
        dfsR(root);
    }
    
    // usually not used
    private void dfs(Node root) {
        if (root == null)
            return;
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            Node tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            System.out.print(node.val + " ");
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
    }
    
    // preorder (vlr)
    private void dfsR(Node root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        dfsR(root.left);
        dfsR(root.right);
    }
    
    /*************************************************************************
     *  Check integrity of BST data structure
     *************************************************************************/
     // does this binary tree satisfy symmetric order?
     // Note: this test also ensures that data structure is a binary tree since order is strict
     private boolean isBST() {
         return isBST(root, null, null);
     }

     // is the tree rooted at x a BST with all keys strictly between min and max
     // (if min or max is null, treat as empty constraint)
     private boolean isBST(Node x, K min, K max) {
         if (x == null)
             return true;
         if (min != null && x.key.compareTo(min) <= 0)
             return false;
         if (max != null && x.key.compareTo(max) >= 0)
             return false;
         return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
     }
    
     /*************************************************************************
      *  Tree and array
      *************************************************************************/
     // array -> tree
     public static void buildTree(BST<Integer, Integer> bst, int[] a, int l, int r) {
    	 if (r >= l) {
	    	 int mid = (r + l) / 2;
	    	 bst.put(a[mid], a[mid]);
	    	 buildTree(bst, a, l, mid - 1);
	    	 buildTree(bst, a, mid + 1, r);
    	 }
     }
     
     public static BST<Integer, Integer>.Node buildTree(int[] a, int l, int r) {
    	 if (r >= l) {
	    	 int mid = (r + l) / 2;
	    	 BST<Integer, Integer>.Node node = new BST<Integer, Integer>().new Node(a[mid], a[mid], 1);
	    	 node.left = buildTree(a, l, mid - 1);
	    	 node.right = buildTree(a, mid + 1, r);
	    	 return node;
    	 } else
    		 return null;
     }
     
     // tree -> array
     public static int[] buildArray(BST<Integer, Integer>.Node root) {
    	 List<Integer> arr = new ArrayList<Integer>();
    	 lvr(root, arr);
    	 int[] a = new int[arr.size()];
    	 for (int i = 0; i < arr.size(); i++)
    		 a[i] = arr.get(i);
    	 return a;
     }
     
     // inorder
     static void lvr(BST<Integer, Integer>.Node root, List<Integer> arr) {
         if (root == null)
             return;
         lvr(root.left, arr);
         arr.add(root.val);
         lvr(root.right, arr);
     }
     
     static BST<Integer, Integer>.Node LCA(BST<Integer, Integer>.Node root, int a, int b) {
     	if (root == null)
     		return null;
     	System.out.println(root.val);
     	if (Math.max(a, b) < root.val)
     		return LCA(root.left, a, b); // both nodes are on the left
     	else if (Math.min(a, b) > root.val)
     		return LCA(root.right, a, b); // both nodes are on the right
     	else
     		return root; // the nodes are on separate branches
     }
     
     public static void main(String[] args) {
    	 int[] a = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         //bst.put(20, 20);bst.put(15, 15);bst.put(23, 23);bst.put(17, 17);bst.put(16, 16);bst.put(10, 10);bst.put(8, 8);bst.put(12, 12);bst.put(11, 11);bst.put(13, 13);bst.put(19, 19);bst.put(14, 14);
         //bst.put(3, 3);bst.put(1, 1);bst.put(0, 0);bst.put(2, 2);bst.put(7, 7);bst.put(5, 5);bst.put(4, 4);bst.put(6, 6);bst.put(11, 11);bst.put(9, 9);bst.put(10, 10);bst.put(8, 8);bst.put(12, 12);
         bst.put(6, 6);bst.put(2, 2);bst.put(1, 1);bst.put(4, 4);bst.put(3, 3);bst.put(5, 5);bst.put(8, 8);bst.put(7, 7);bst.put(9, 9);bst.put(10, 10);
         //bst.delete(7);
         
         //buildTree(bst, a, 0, a.length - 1);
         //BST<Integer, Integer>.Node node = buildTree(a, 0, a.length - 1);
         //bst.bfs(bst.root);
         
         //System.out.println(LCA(bst.root, 1, 5).val);
         int[] b = buildArray(bst.root);
         for (int i = 0; i < b.length; i++)
        	 System.out.print(b[i] + " ");
         
         //System.out.println(""+bst.isBST());
     }

}
