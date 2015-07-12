package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class GraphDeprecated {

    // from name to vertex, using HashMap
    private HashMap<String, Vertex> mVertices;
    
    // adjacent list is a set of vertices represented by a search tree (TreeSet<Vertex>)
    
    // from vertex to adjacent list, using HashMap
    private HashMap<Vertex, TreeSet<Vertex>> mAdjList;
    
    private int mNumVertices;
    private int mNumEdges;
    
    // EMPTY_SET is a constant:
    private static final TreeSet<Vertex> EMPTY_SET = new TreeSet<Vertex>();
    public static final int INFINITY = Integer.MAX_VALUE;

    // construct an empty graph
    public GraphDeprecated() {
        mAdjList = new HashMap<Vertex, TreeSet<Vertex>>();
        mVertices = new HashMap<String, Vertex>();
        mNumVertices = mNumEdges = 0;
    }

    // add a new vertex name with no neighbors (if vertex does not yet exist)
    public Vertex addVertex(String name) {
        Vertex v;
        v = mVertices.get(name);
        if (v == null) {
            v = new Vertex(name);
            mVertices.put(name, v);
            mAdjList.put(v, new TreeSet<Vertex>());
            mNumVertices += 1;
        }
        return v;
    }

    /**
     * Returns the Vertex matching v
     * @param name a String name of a Vertex that may be in
     * this Graph
     * @return the Vertex with a name that matches v or null
     * if no such Vertex exists in this Graph
     */
    public Vertex getVertex(String name) {
        return mVertices.get(name);
    }

    /**
     * Returns true iff v is in this Graph, false otherwise
     * @param name a String name of a Vertex that may be in
     * this Graph
     * @return true iff v is in this Graph
     */
    public boolean hasVertex(String name) {
        return mVertices.containsKey(name);
    }

    /**
     * Is from-to, an edge in this Graph. The graph is 
     * undirected so the order of from and to does not
     * matter.
     * @param from the name of the first Vertex
     * @param to the name of the second Vertex
     * @return true iff from-to exists in this Graph
     */
    public boolean hasEdge(String from, String to) {
        Vertex v1 = mVertices.get(from);
        Vertex v2 = mVertices.get(to);
        if (v1 == null || v2 == null) return false;
        return mAdjList.get(v1).contains(v2);
    }
    
    /**
     * Add to to from's set of neighbors, and add from to to's
     * set of neighbors. Does not add an edge if another edge
     * already exists
     * @param from the name of the first Vertex
     * @param to the name of the second Vertex
     */
    public void addEdge(String from, String to) {
        Vertex v, w;
        if (hasEdge(from, to)) return;  // no duplicate edges
        mNumEdges += 1;
        if ((v = getVertex(from)) == null)
            v = addVertex(from);
        if ((w = getVertex(to)) == null)
            w = addVertex(to);
        mAdjList.get(v).add(w);
        mAdjList.get(w).add(v);  // undirected graph only
    }

    /**
     * Return an iterator over the neighbors of Vertex named v
     * @param name the String name of a Vertex
     * @return an Iterator over Vertices that are adjacent
     * to the Vertex named v, empty set if v is not in graph
     */
    public Iterable<Vertex> adjacentTo(String name) {
        Vertex v = getVertex(name);
        if (v == null) return EMPTY_SET;
        return mAdjList.get(getVertex(name));
    }

    /**
     * Return an iterator over the neighbors of Vertex v
     * @param v the Vertex
     * @return an Iterator over Vertices that are adjacent
     * to the Vertex v, empty set if v is not in graph
     */
    public Iterable<Vertex> adjacentTo(Vertex v) {
        if (!mAdjList.containsKey(v)) return EMPTY_SET;
        return mAdjList.get(v);
    }

    /**
     * Returns an Iterator over all Vertices in this Graph
     * @return an Iterator over all Vertices in this Graph
     */
    public Iterable<Vertex> getVertices() {
        return mVertices.values();
    }

    public int numVertices() {
        return mNumVertices;
    }
    
    public int numEdges(){
        return mNumEdges;
    }
    

    /*
     * Returns adjacency-list representation of graph
     */
    public String toString() {
        String s = "";
        for (Vertex v : mVertices.values()) {
            s += v + ": ";
            for (Vertex w : mAdjList.get(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    private void initSearch() {
        for (Vertex v: getVertices()) {
            v.status = Vertex.NOT_VISITED;
            v.predecessor = null;
            v.distance = INFINITY;
        }
    }
    
    public void BFS(Vertex s) {
        initSearch();
        s.distance = 0;
        s.status = Vertex.VISITED;
        
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(s);
        while (!q.isEmpty()) {
            Vertex v = q.remove();
            System.out.println("visit " + v);
            for (Vertex w: adjacentTo(v))
                if (w.status == Vertex.NOT_VISITED) {
                    w.distance = v.distance + 1;
                    w.status = Vertex.VISITED;
                    w.predecessor = v;
                    q.add(w);
                }
            v.status = Vertex.PROCESSED;
        }
    }
    
    public void DFS(Vertex s) {
        initSearch();
        s.distance = 0;
        s.status = Vertex.VISITED;
        recDFS(s);
    }
    
    public void recDFS(Vertex v) {
        System.out.println("visit " + v);
        for (Vertex w : this.adjacentTo(v)) 
            if (w.status == Vertex.NOT_VISITED) {
                w.distance = v.distance + 1;
                w.status = Vertex.VISITED;
                w.predecessor = v;
                recDFS(w);
            }
        v.status = Vertex.PROCESSED;
    }
    
    public void degreeOfSeparation(Vertex a, Vertex b) {
        initSearch();
        a.distance = 0; b.distance = 0;
        a.status = Vertex.VISITED; b.status = Vertex.VISITED2;
        
        //Set<Vertex> visitedSet = new HashSet<Vertex>();
        Queue<Vertex> q1 = new LinkedList<Vertex>();
        Queue<Vertex> q2 = new LinkedList<Vertex>();
        q1.add(a); q2.add(b);
        int x = 0, y = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Vertex v1 = q1.remove();
            //visitedSet.add(v1);
            System.out.println("visit1 " + v1);
            if (v1.status == Vertex.PROCESSED2/* || v1.status == Vertex.VISITED2*/) {
            	//System.out.println("dist = " + v1.distance);
            	break;
            }
            for (Vertex w: adjacentTo(v1))
                if (w.status != Vertex.PROCESSED && w.status != Vertex.VISITED) {
                    w.distance = v1.distance + 1;
                    w.status = Vertex.VISITED;
                    w.predecessor = v1;
                    q1.add(w);
                    //visitedSet.add(w);
                    System.out.println("add1 " + w + " dist = " + w.distance);
                }
            v1.status = Vertex.PROCESSED;
            x = v1.distance + 1;
            
            Vertex v2 = q2.remove();
            System.out.println("visit2 " + v2);
            if (v2.status == Vertex.PROCESSED/* || v2.status == Vertex.VISITED*/) {
            	//System.out.println("dist = " + v2.distance);
            	break;
            }
            for (Vertex w: adjacentTo(v2)) {
                if (w.status != Vertex.PROCESSED2 && w.status != Vertex.VISITED2) {
                    w.distance = v2.distance + 1;
                    w.status = Vertex.VISITED2;
                    w.predecessor = v2;
                    q2.add(w);
                    System.out.println("add2 " + w + " dist = " + w.distance);
                }
            }
            v2.status = Vertex.PROCESSED2;
            y = v2.distance + 1;
        }
        // retrieve shortest path
        Vertex v = b.predecessor;
        while (v != a) {
        	System.out.println(v);
        	if (v == null)
        	v = v.predecessor;
        }
        System.out.println("dist = " + (x + y));
    }
    
    private List<Vertex> successor(Vertex a) {
    	List<Vertex> successorList = new ArrayList<Vertex>();
    	
    	return successorList;
    }
    
    public static void main(String[] args) {
        GraphDeprecated G = new GraphDeprecated();
        G.addEdge("A", "0");
        G.addEdge("A", "B");
        G.addEdge("A", "C");
        G.addEdge("B", "C");
        G.addEdge("C", "D");
        G.addEdge("D", "E");
        G.addEdge("D", "G");
        G.addEdge("E", "G");
        G.addEdge("G", "H");
        
        // print out graph
        //System.out.println(G);

        //G.BFS(G.getVertex("D"));
        G.degreeOfSeparation(G.getVertex("0"), G.getVertex("H"));
    }

    class Vertex implements Comparable<Vertex> {
    	
        // statuses of the vertex
    	public static final int NOT_VISITED = 0;
    	public static final int PROCESSED = 1;
    	public static final int PROCESSED2 = 3; // for bi-directional BFS
    	public static final int VISITED = 2;
    	public static final int VISITED2 = 4; // for bi-directional BFS
        // label 
        public String name;  
        // status of the vertex
        public int status;
        // previous vertex
        public Vertex predecessor;
        // the next variable for path length of shortest path from source
        public int distance; 
        // infinite distance indicates that there is no path from the source to this vertex
        public static final int INFINITY = Integer.MAX_VALUE;

        public Vertex(String v) {
            name = v;
            predecessor = null;
            status = NOT_VISITED;
            distance = INFINITY; // start as infinity away
        }

        /*
         * The name of the Vertex is assumed to be unique, so it is used as a HashCode
         * @see java.lang.Object#hashCode()
         */
        public int hashCode() {
            return name.hashCode();
        }
        
        public String toString(){ 
            return name;
        }
        
        /**
         * Compare on the basis of distance from source first and 
         * then lexicographically
         */
        public int compareTo(Vertex other) {
            int diff = distance - other.distance;
            if (diff != 0)
                return diff;
            else
                return name.compareTo(other.name);
        }
    }
}
