package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Interstellar {

    static final int X_END = 1000;
    static final int Y_END = 1000;

    class Vertex implements Comparable<Vertex> {
        public final String name;
        public Edge[] adjacencies;
        public int minDistance = Integer.MAX_VALUE;
        public Vertex previous;

        public Vertex(String argName) {
            name = argName;
        }

        public String toString() {
            return name;
        }

        public int compareTo(Vertex other) {
            return Integer.compare(minDistance, other.minDistance);
        }

        public void addEdge(Edge e) {
            Edge[] temp = adjacencies;
            adjacencies = new Edge[temp.length + 1];
            for (int i = 0; i < temp.length; i++)
                adjacencies[i] = temp[i];
            adjacencies[temp.length] = e;
        }

    }

    class Edge {
        public final Vertex target;
        public final int weight;

        public Edge(Vertex argTarget, int argWeight) {
            target = argTarget;
            weight = argWeight;
        }
    }

    public int dijkstra(Vertex source, Vertex target) {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);
        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();
            if (u == target) {
                List<Vertex> path = new ArrayList<Vertex>();
                for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                    path.add(vertex);
                Collections.reverse(path);
                return target.minDistance; //return path;
            }
            // visit each edge exiting u
            for (Edge e: u.adjacencies) {
                Vertex v = e.target;
                int weight = e.weight;
                int distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
        return 0;
    }

    private void init(Vertex[][] vertexes) {
        // create vertexes
        for (int i = 0; i <= Y_END; i++) {
            for (int j = 0; j <= X_END; j++) {
                vertexes[i][j] = new Vertex(i + "" + j);
            }
        }
        // create edges
        // 1. corners
        vertexes[0][0].adjacencies = new Edge[] {
                new Edge(vertexes[0][1], 1),
                new Edge(vertexes[1][0], 1) };
        vertexes[0][X_END].adjacencies = new Edge[] {
                new Edge(vertexes[0][X_END - 1], 1),
                new Edge(vertexes[1][X_END], 1) };
        vertexes[Y_END][0].adjacencies = new Edge[] {
                new Edge(vertexes[Y_END - 1][0], 1),
                new Edge(vertexes[Y_END][1], 1) };
        vertexes[Y_END][X_END].adjacencies = new Edge[] {
                new Edge(vertexes[Y_END][X_END - 1], 1),
                new Edge(vertexes[Y_END - 1][X_END], 1) };
        // 2. borders
        for (int j = 1; j < X_END; j++) {
            vertexes[0][j].adjacencies = new Edge[] {
                    new Edge(vertexes[0][j - 1], 1),
                    new Edge(vertexes[0][j + 1], 1),
                    new Edge(vertexes[1][j], 1) };
            vertexes[Y_END][j].adjacencies = new Edge[] {
                    new Edge(vertexes[Y_END][j - 1], 1),
                    new Edge(vertexes[Y_END][j + 1], 1),
                    new Edge(vertexes[Y_END - 1][j], 1) };
        }
        for (int i = 1; i < Y_END; i++) {
            vertexes[i][0].adjacencies = new Edge[] {
                    new Edge(vertexes[i - 1][0], 1),
                    new Edge(vertexes[i + 1][0], 1),
                    new Edge(vertexes[i][1], 1) };
            vertexes[i][X_END].adjacencies = new Edge[] {
                    new Edge(vertexes[i - 1][X_END], 1),
                    new Edge(vertexes[i + 1][X_END], 1),
                    new Edge(vertexes[i][X_END - 1], 1) };
        }
        // 3. center
        for (int i = 1; i < Y_END; i++) {
            for (int j = 1; j < X_END; j++) {
                vertexes[i][j].adjacencies = new Edge[] {
                        new Edge(vertexes[i][j - 1], 1),
                        new Edge(vertexes[i][j + 1], 1),
                        new Edge(vertexes[i - 1][j], 1),
                        new Edge(vertexes[i + 1][j], 1) };
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Interstellar graph = new Interstellar();
        Scanner sc = new Scanner(new File("test.txt"));
        Vertex[][] vertexes = new Vertex[Y_END + 1][X_END + 1];
        graph.init(vertexes);
        int wormholesNumber = sc.nextInt();
        int startX = sc.nextInt();
        int startY = sc.nextInt();
        int endX = sc.nextInt();
        int endY = sc.nextInt();
        for (int i = 0; i < wormholesNumber; i++) {
            int wStartX = sc.nextInt();
            int wStartY = sc.nextInt();
            int wEndX = sc.nextInt();
            int wEndY = sc.nextInt();
            int weight = sc.nextInt();
            vertexes[wStartY][wStartX].addEdge(graph.new Edge(vertexes[wEndY][wEndX], weight));
            vertexes[wEndY][wEndX].addEdge(graph.new Edge(vertexes[wStartY][wStartX], weight));
        }
        System.out.println("Time: " + graph.dijkstra(vertexes[startY][startX], vertexes[endY][endX]));
    }
}
