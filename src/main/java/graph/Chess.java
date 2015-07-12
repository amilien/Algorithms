package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Mike
 * Chess.java is a collection of algorithms working with chess quizes.
 * 
 * 1. eightQueens(int n): prints all NxN board permutations with N queens not hitting each other
 * 2. shortestPathFigure(int n, int x1, int y1, int x2, int y2): prints the shortest path between 2
 * point on chess board for any chess figure (king/knight)
 * 3. solveKnightTour(int N): check if knight can visit every cell only once
 * 
 */

public class Chess {

	static class Cell {
		Cell parent; // needed for dijkstra
		boolean isVisited; // needed for dijkstra
		int x, y; // chessboard coords
		
		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
			parent = null;
			isVisited = false;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Cell) {
				Cell cell = (Cell) o;
				if (x == cell.x && y == cell.y)
					return true;
			}
			return false;
		}
	}
	
    // print all NxN board permutations with N queens not hitting each other
	// O(n!)
    static void eightQueens(int n) {
    	int[] a = new int[n];
    	eightQueensR(a, 0);
    }
    
    // recursive solution
    private static void eightQueensR(int[] q, int n) {
    	int N = q.length;
        if (n == N) printQueens(q);
        else {
            for (int i = 0; i < N; i++) {
                q[n] = i;
                if (isConsistent(q, n)) eightQueensR(q, n + 1);
            }
        }
    }
    
    // return true if queen placement q[n] does not conflict with other queens q[0] through q[n-1]
    private static boolean isConsistent(int[] q, int n) {
    	for (int i = 0; i < n; i++) {
            if (q[i] == q[n])             return false;   // same column
            if ((q[i] - q[n]) == (n - i)) return false;   // same major diagonal
            if ((q[n] - q[i]) == (n - i)) return false;   // same minor diagonal
        }
        return true;
    }

    // print out N-by-N placement of queens from permutation q in ASCII
    private static void printQueens(int[] q) {
    	int N = q.length;
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		if (q[i] == j) System.out.print("Q ");
                else           System.out.print("* ");
            }
            System.out.println();
        }  
        System.out.println();
    }

    // find the shortest path for King/Knight figure
    static void shortestPathFigure(int n, int x1, int y1, int x2, int y2) {
    	List<Cell> path = new ArrayList<Cell>();
    	Cell[][] board = new Cell[n][n];
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			board[i][j] = new Cell(i, j);
    	Queue<Cell> queue = new LinkedList<Cell>();
    	queue.add(board[x1][y1]);
    	board[x1][y1].isVisited = true;
    	while (!queue.isEmpty()) {
    		Cell cell = queue.poll();
    		int x = cell.x;
    		int y = cell.y;
    		if (x == x2 && y == y2) { // target cell found
    			for (Cell c = board[x2][y2]; c != null; c = c.parent)
      	            path.add(c);
      	        Collections.reverse(path);
      	        break;
    		}
    		for (Cell c: getAdjacentCellsKing/*Knight*/(board, n, x, y)) {
    			queue.remove(c);
    			c.parent = cell;
    			c.isVisited = true;
    			queue.add(c);
    		}
    	}
    	if (path.isEmpty())
    		System.out.println("There is no path.");
    	else
	    	for (Cell c : path)
	    		System.out.println("(" + c.x + "," + c.y + ")");
    }
    
    private static List<Cell> getAdjacentCellsKing(Cell[][] board, int n, int x, int y) {
    	List<Cell> adjacentCells = new ArrayList<Cell>();
		if (x > 0 && y > 0 && !board[x - 1][y - 1].isVisited)
			adjacentCells.add(board[x - 1][y - 1]);
		if (y > 0 && !board[x][y - 1].isVisited)
			adjacentCells.add(board[x][y - 1]);
		if (x < n - 1 && y > 0 && !board[x + 1][y - 1].isVisited)
			adjacentCells.add(board[x + 1][y - 1]);
		if (x > 0 && !board[x - 1][y].isVisited)
			adjacentCells.add(board[x - 1][y]);
		if (x < n - 1 && !board[x + 1][y].isVisited)
			adjacentCells.add(board[x + 1][y]);
		if (x > 0 && y < n - 1 && !board[x - 1][y + 1].isVisited)
			adjacentCells.add(board[x - 1][y + 1]);
		if (y < n - 1 && !board[x][y + 1].isVisited)
			adjacentCells.add(board[x][y + 1]);
		if (x < n - 1 && y < n - 1 && !board[x + 1][y + 1].isVisited)
			adjacentCells.add(board[x + 1][y + 1]);
		return adjacentCells;
    }
    
    private static List<Cell> getAdjacentCellsKnight(Cell[][] board, int n, int x, int y) {
    	List<Cell> adjacentCells = new ArrayList<Cell>();
		if (x > 1 && y > 0 && !board[x - 2][y - 1].isVisited)
			adjacentCells.add(board[x - 2][y - 1]);
		if (x > 0 && y > 1 && !board[x - 1][y - 2].isVisited)
			adjacentCells.add(board[x - 1][y - 2]);
		if (x < n - 1 && y > 1 && !board[x + 1][y - 2].isVisited)
			adjacentCells.add(board[x + 1][y - 2]);
		if (x < n - 2 && y > 0 && !board[x + 2][y - 1].isVisited)
			adjacentCells.add(board[x + 2][y - 1]);
		if (x < n - 2 && y < n - 1 && !board[x + 2][y + 1].isVisited)
			adjacentCells.add(board[x + 2][y + 1]);
		if (x < n - 1 && y < n - 2 && !board[x + 1][y + 2].isVisited)
			adjacentCells.add(board[x + 1][y + 2]);
		if (x > 0 && y < n - 2 && !board[x - 1][y + 2].isVisited)
			adjacentCells.add(board[x - 1][y + 2]);
		if (x > 1 && y < n - 1 && !board[x - 2][y + 1].isVisited)
			adjacentCells.add(board[x - 2][y + 1]);
		return adjacentCells;
    }
    
    // check if knight can visit every square only once
    static boolean solveKnightTour(int N) {
    	int[][] soln = new int[N][N];
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                soln[x][y] = -1;
        int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        soln[0][0] = 0;
        if (!solve(N, soln, 0, 0, 1, xMove, yMove)) {
            System.out.println("the solution does not exist");
            return false;
        } else
            printSolution(N, soln);
        return true;
    }
    
    private static boolean isSafe(int N, int[][] soln, int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N && soln[x][y] == -1)
            return true;
        return false;
    }
 
    private static void printSolution(int N, int[][] soln) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.print("  " + soln[x][y]);
            System.out.println();
        }
    }
 
    private static boolean solve(int N, int[][] soln, int x, int y, 
    		int movei, int xMove[],int yMove[]) {
        int k, next_x, next_y;
        if (movei == N * N)
            return true;
        for (k = 0; k < xMove.length/*N*/; k++) {
            next_x = x + xMove[k];
            next_y = y + yMove[k];
            if (isSafe(N, soln, next_x, next_y)) {
                soln[next_x][next_y] = movei;
                if (solve(N, soln, next_x, next_y, movei + 1, xMove, yMove))
                    return true;
                else
                    soln[next_x][next_y] = -1;
            }
        }
        return false;
    }
 
    public static void main(String[] args) {
    	//eightQueens(8);
    	//solveKnightTour(8);
    	//shortestPathFigure(8, 0, 0, 7, 7);
	}

}
