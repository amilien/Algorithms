package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UnweightedGraph {

    private Map<String, Node> mNodes;
    
	private class Node {
		String name;
		Node parent; // needed for bfs/bi-bfs
		Node parent2; // needed only for bi-bfs
		List<Node> neighbors;
		
		public Node(String name) {
			this.name = name;
			neighbors = new ArrayList<Node>();
		}
	}
	
	public UnweightedGraph() {
		mNodes = new HashMap<String, Node>();
	}
	
    private boolean hasEdge(String from, String to) {
        Node n1 = mNodes.get(from);
        Node n2 = mNodes.get(to);
        if (n1 == null || n2 == null)
        	return false;
        for (Node n: n1.neighbors)
        	if (n.equals(n2))
        		return true;
        for (Node n: n2.neighbors)
        	if (n.equals(n1))
        		return true;
        return false;
    }
    
    private Node getNode(String name) {
        return mNodes.get(name);
    }

    private Node addNode(String name) {
        Node n = mNodes.get(name);
        if (n == null) {
            n = new Node(name);
            mNodes.put(name, n);
        }
        return n;
    }
    
    public void addEdge(String from, String to) {
        Node n1, n2;
        if (hasEdge(from, to))
        	return; // no duplicate edges
        if ((n1 = getNode(from)) == null)
            n1 = addNode(from);
        if ((n2 = getNode(to)) == null)
            n2 = addNode(to);
        n1.neighbors.add(n2);
        n2.neighbors.add(n1);
    }

    private List<Node> constructPath(Node startNode, Node node) {
    	LinkedList<Node> path = new LinkedList<Node>();
    	while (node.parent != null) {
    	    path.addFirst(node);
    	    node = node.parent;
    	}
    	path.addFirst(startNode);
    	return path;
    }
    
    // finds shortest path iff:
    // 1. no loops
    // 2. no weights
    public List<Node> BFS(String from, String to) {
    	Node startNode = getNode(from);
    	Node endNode = getNode(to);
    	LinkedList<Node> visitedList = new LinkedList<Node>(); // list of visited nodes
    	LinkedList<Node> candidateList = new LinkedList<Node>(); // list of nodes to visit (sorted)
    	candidateList.add(startNode);
    	while (!candidateList.isEmpty()) {
    		Node node = candidateList.removeFirst();
	    	//System.out.println("visited " + node.name + " (1)");
    	    if (node.equals(endNode))
    	    	return constructPath(startNode, node); // path found!
    	    else {
    	    	visitedList.add(node);
    	    	// add neighbors to the candidate list
    	    	Iterator<Node> i = node.neighbors.iterator();
    	    	while (i.hasNext()) {
    	    		Node neighborNode = i.next();
    	    		if (!visitedList.contains(neighborNode) && !candidateList.contains(neighborNode)) {
    	    			neighborNode.parent = node;
    	    			//System.out.println(neighborNode.name + "->" + neighborNode.parent1.name + " (1)");
    	    			candidateList.add(neighborNode);
    	    		}
    	    	}
    	    }
    	}
    	return null;
    }
    
    private List<Node> constructPath(Node startNode, Node endNode, Node node) {
    	LinkedList<Node> path = new LinkedList<Node>();
    	Node tmp = node;
    	while (tmp.parent2 != null) {
    	    path.add(tmp);
    	    tmp = tmp.parent2;
    	}
    	path.add(endNode);
    	node = node.parent; // remove duplicated node
    	while (node.parent != null) {
    	    path.addFirst(node);
    	    node = node.parent;
    	}
    	path.addFirst(startNode);
    	return path;
    }
    
    // finds shortest path iff:
    // 1. no loops
    // 2. no weights
    public List<Node> biBFS(String from, String to) {
    	Node startNode = getNode(from);
    	Node endNode = getNode(to);
    	LinkedList<Node> visitedListStart = new LinkedList<Node>(); // list of visited nodes from start point
    	LinkedList<Node> visitedListEnd = new LinkedList<Node>(); // list of visited nodes from end point
    	LinkedList<Node> candidateListStart = new LinkedList<Node>(); // list of nodes to visit (sorted) from start point
    	LinkedList<Node> candidateListEnd = new LinkedList<Node>(); // list of nodes to visit (sorted) from end point
    	candidateListStart.add(startNode);
    	candidateListEnd.add(endNode);
    	while (!candidateListStart.isEmpty() && !candidateListEnd.isEmpty()) {
    		Node node = candidateListStart.removeFirst();
	    	//System.out.println("visited " + node.name + " (1)");
    	    if (visitedListEnd.contains(node))
    	    	return constructPath(startNode, endNode, node); // path found!
    	    else {
    	    	visitedListStart.add(node);
    	    	// add neighbors to the candidate list
    	    	Iterator<Node> i = node.neighbors.iterator();
    	    	while (i.hasNext()) {
    	    		Node neighborNode = i.next();
    	    		if (!visitedListStart.contains(neighborNode) && !candidateListStart.contains(neighborNode)) {
    	    			neighborNode.parent = node;
    	    			//System.out.println(neighborNode.name + "->" + neighborNode.parent1.name + " (1)");
    	    			candidateListStart.add(neighborNode);
    	    		}
    	    	}
    	    }
    		node = candidateListEnd.removeFirst();
	    	//System.out.println("visited " + node.name + " (2)");
    	    if (visitedListStart.contains(node))
    	    	return constructPath(startNode, endNode, node); // path found!
    	    else {
    	    	visitedListEnd.add(node);
    	    	// add neighbors to the candidate list
    	    	Iterator<Node> i = node.neighbors.iterator();
    	    	while (i.hasNext()) {
    	    		Node neighborNode = i.next();
    	    		if (!visitedListEnd.contains(neighborNode) && !candidateListEnd.contains(neighborNode)) {
    	    			neighborNode.parent2 = node;
    	    			//System.out.println(neighborNode.name + "->" + neighborNode.parent2.name + " (2)");
    	    			candidateListEnd.add(neighborNode);
    	    		}
    	    	}
    	    }
    	}
    	return null;
    }
    
	public static void main(String[] args) {
		UnweightedGraph graph = new UnweightedGraph();
		graph.addEdge("A", "0");
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "C");
		graph.addEdge("C", "D");
		graph.addEdge("B", "D");
		graph.addEdge("D", "E");
		graph.addEdge("C", "E");
		graph.addEdge("D", "G");
		graph.addEdge("E", "G");
		graph.addEdge("G", "H");
		graph.addEdge("B", "F");
		graph.addEdge("F", "D");
		graph.addEdge("G", "F");
		graph.addEdge("I", "H");
		graph.addEdge("K", "H");
		graph.addEdge("J", "H");
		graph.addEdge("J", "K");
		graph.addEdge("I", "J");
		graph.addEdge("E", "L");
		graph.addEdge("G", "L");
		graph.addEdge("L", "K");
		graph.addEdge("F", "M");
		graph.addEdge("M", "I");
		graph.addEdge("N", "F");
		graph.addEdge("O", "N");
		graph.addEdge("O", "B");
		graph.addEdge("O", "P");
		graph.addEdge("P", "Q");
		graph.addEdge("Q", "N");
		graph.addEdge("O", "F");
		
		List<Node> res = graph.BFS("P", "J");
		for (Node n: res)
			System.out.print(n.name + " ");
	}

}
