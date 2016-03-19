package utils;

import java.math.BigInteger;

/**
 * 
 * @author Mike
 * This class is a collection of algorithms working with numbers, arithmetic ops, bits, etc.
 * 
 * 1. multiply(int x, int y): multiplies 2 numbers
 * 2. karatsuba(BigInteger x, BigInteger y): multiplies 2 numbers (Karatsuba algo)
 * 3. divide(int x, int y): efficient division of 2 numbers
 * 4. fibonacci4(int n): finds the fibonacci number efficiently using matrixes
 * 5. fact2(int n): efficiently calculates factorial
 * 6. pow(long a, long n): efficiently calculates power
 * 7. get1Bits(int value): finds the number of '1' bits
 * 8. setRowAndColumnZero(int[][] arr): sets row and column to 0 if element is 0 
 * 9. rotateMatrix(int[][] arr): rotates matrix 90 degrees clockwise
 * 10. rotateMatrixInPlace(int[][] arr): rotates matrix 90 degrees clockwise in-place
 * 11. buildSumMatrix(int[][] arr): builds matrix where (i, j) = sum(i x j)
 * 12. printMatrixDiagonals(int[][] a): prints matrix diagonals with bottom left-top right direction
 * 13. findNumberInMatrix(int x, int[][] a): searches for a number in the matrix with sorted rows and columns
 *
 */

public class ArithmeticUtils {

	// O(log y)
    static int multiply(int x, int y) {
        if (y == 0)
            return 0;
        int z = multiply(x, y / 2);
        if (y % 2 == 0)
            return 2 * z;
        else
            return x + 2 * z;
    }

    // Input:  2 n-digit numbers
    // Output: O(n^log(2)3) = O(n^1.58)
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y); // optimize this parameter
        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);
        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));
        // compute sub-expressions
        BigInteger ac    = karatsuba(a, c);
        BigInteger bd    = karatsuba(b, d);
        BigInteger abcd  = karatsuba(a.add(b), c.add(d));
        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2*N));
    }
    
    // O(2*log(y)x)
    static int divide(int x, int y) {
        int mask = 1;
        int quotient = 0;
        while (y <= x) { 
            y <<= 1; 
            mask <<= 1; 
        } 
        while (mask > 1) { 
            y >>= 1; 
            mask >>= 1; 
            if (x >= y) { 
                x -= y; 
                quotient |= mask; 
            } 
        } 
        return quotient; 
    }
    
    // O(2^n)
    static int fibonacci1(int n) {
    	if (n == 0 || n == 1)
    		return n;
    	return fibonacci1(n - 2) + fibonacci1(n - 1);
    }
    
    // O(n)
    static int fibonacci2(int n) {
    	int[] f = new int[n + 1];
    	f[0] = 0; f[1] = 1;
    	if (n == 0 || n == 1)
    		return f[n];
    	for (int i = 2; i <= n; i++) {
    		f[i] = f[i - 2] + f[i - 1];
    	}
    	return f[n];
    }
    
    // O(n)
    static int fibonacci3(int n) {
    	int f0 = 0, f1 = 1, fn = 0;
    	if (n == 0 || n == 1)
    		return n;
    	for (int i = 2; i <= n; i++) {
    		fn = f0 + f1;
    		f0 = f1;
    		f1 = fn;
    	}
    	return fn;
    }
    
    // O(log n)
    // 0, 1, 1, 2, 3, 5, ...
    static int fibonacci4(int n) {
        int a11 = 1, a12 = 1, a21 = 1, a22 = 0;
        int r11 = 1, r12 = 0;
        int q11 = 0, q12 = 0, q21 = 0, q22 = 0;
        while (n > 0) {
            if ((n&1)==1) {
                q11 = (r11 * a11 + r12 * a21);
                q12 = (r11 * a12 + r12 * a22);
                r11 = q11;
                r12 = q12;
            }
            q11 = (a11 * a11 + a12 * a21);
            q12 = (a11 * a12 + a12 * a22);
            q21 = (a21 * a11 + a22 * a21);
            q22 = (a21 * a12 + a22 * a22);
            a11 = q11;
            a12 = q12;
            a21 = q21;
            a22 = q22;
            n >>= 1;
        }
        return r12;
    }
    
    // O(n)
    static int fact(int n) {
        if (n == 0 || n == 1)
            return 1;
        return fact(n - 1) * n;
    }

    // O(n)
    static long fact2(int n) {
        if (n == 0 || n == 1)
            return 1;
        long res = 1;
        for (int i = 2; i <= n; i++)
        	res *= i;
        return res;
    }
    
    // O(n)
    static int power(int a, int n) {
    	int res = 1;
    	for (int i = 0; i < n; i++)
    		res *= a;
    	return res;
    }
    
    // O(log n)
    static long powerR(int a, int n) {
    	if (n == 0)
    		return 1;
    	else if (n == 1)
    		return a;
    	else if (n % 2 == 0)
    		return powerR(a * a, n / 2);
    	else
    		return a * powerR(a, n - 1);
    }
    
    // O(log n)
    static long pow(long a, long n) {
        long result = 1;
        while (n > 0) {
          if ((n & 1) == 1)
              result *= a;
          n >>= 1;
          a *= a;
        }
        return result;
    }
    
    static int get1Bits(int value) {
    	int res = 0;
    	while (value != 0) {
    		if ((value & 1) == 1)
    			res++;
    		value = value >>> 1;
    	}
    	return res;
    }
    
    // O(m*n) - time, O(m+n) - space
    static int[][] setRowAndColumnZero(int[][] arr) {
    	int m = arr[0].length; // width
    	int n = arr.length; // height
    	boolean[] rowHasZero = new boolean[n];
    	boolean[] colHasZero = new boolean[m];
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < m; j++)
    			if (arr[i][j] == 0)
    				rowHasZero[i] = colHasZero[j] = true;
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < m; j++)
    			if (rowHasZero[i] || colHasZero[j])
					arr[i][j] = 0;
    	return arr;
    }
    
    // rotate matrix 90 clockwise
    static int[][] rotateMatrix(int[][] arr) {
    	int[][] tmp = new int[arr[0].length][arr.length];
    	for (int i = 0; i < arr[0].length; i++) {
    		for (int j = 0; j < arr.length; j++) {
    			tmp[i][j] = arr[arr.length - j - 1][i];
    		}
    	}
    	return tmp;
    }
    
    // rotate matrix 90 clockwise in-place
    static int[][] rotateMatrixInPlace(int[][] arr) {
    	int n = arr.length;
    	for (int layer = 0; layer < n / 2; layer++) {
    		int first = layer;
    		int last = n - 1 - layer;
    		for (int i = first; i < last; i++) {
    			int offset = i - first;
    			int top = arr[first][i]; // save top
    			// left -> top
    			arr[first][i] = arr[last - offset][first];
    			// bottom -> left
    			arr[last - offset][first] = arr[last][last - offset];
    			// right -> bottom
    			arr[last][last - offset] = arr[i][last];
    			// top -> right
    			arr[i][last] = top; 
    		}
    	}
    	return arr;
    }
    
    // build matrix where (i, j) = sum(i x j)
    static int[][] buildSumMatrix(int[][] arr) {
    	int[][] tmp = new int[arr.length][arr[0].length];
    	int[][] rowSum = new int[arr.length][arr[0].length - 1];
    	for (int i = 0; i < arr.length; i++)
    		for (int j = 0; j < arr[0].length - 1; j++)
    			rowSum[i][j] = arr[i][j] + (j == 0 ? 0 : rowSum[i][j - 1]);
    	for (int i = 0; i < arr.length; i++)
    		for (int j = 0; j < arr[0].length; j++)
    			tmp[i][j] = arr[i][j] + (j == 0 ? (i == 0 ? 0 : tmp[i - 1][j]) : (rowSum[i][j - 1] + (i == 0 ? 0 : tmp[i - 1][j])));
    	return tmp;
    }
    
    static void printMatrixDiagonals(int[][] a) { // print SW-NE
    	for (int k = 0; k < a.length * 2; k++) {
    		for (int j = 0; j <= k; j++) {
    			int i = k - j;
    			if (i < a.length && j < a[0].length)
    				System.out.print(a[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
    
    // search for a number in the matrix with sorted rows and columns
    static boolean findNumberInMatrix(int x, int[][] a) {
    	int row = 0, col = a[0].length - 1;
    	while (row < a.length && col >= 0) {
    		if (a[row][col] == x)
    			return true;
    		else if (a[row][col] > x)
    			--col;
    		else
    			++row;
    	}
    	return false;
    }
    
    static int getPascalTriangleElement(int row, int col) {
    	if (col > row)
    		return -1;
    	if (col == 0 || row == col)
    		return 1;
    	return getPascalTriangleElement(row - 1, col - 1) + getPascalTriangleElement(row - 1, col);
    }
    
    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
        	for (int j = 0; j < arr[i].length; j++) {
        		System.out.print(arr[i][j] + " ");
        	}
        	System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
    	//BigInteger a = new BigInteger("100000");BigInteger b = new BigInteger("200000");
        //System.out.println(karatsuba(a, b));
        //System.out.println(get1Bits(5559));
    	int[][] arr = new int[][] {{0, 1, 2, 3}, {2, 3, 4, 5}, {6, 0, 7, 8}, {9, 0, 0, 10}, {6, 7, 8, 9}};
    	//int[][] arr = new int[][] {{1, 2, 7, 13}, {3, 4, 10, 14}, {5, 6, 11, 15}, {8, 9, 12, 16}, {17, 18, 19, 20}};
    	//print(arr);
    	//print(buildSumMatrix(arr));
    	//printMatrixDiagonals(arr);
    	//print(rotateMatrix(arr));
    	//System.out.println(findNumberInMatrix(6, arr));
    	//System.out.println(pow(3, 20));
    	//print(setRowAndColumnZero(arr));
    	//System.out.println(getPascalTriangleElement(4,  2));
    	System.out.println(fact(10));
    }

}
