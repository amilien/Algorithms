package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Mike
 * This class is a collection of algorithms working with integer arrays.
 * 
 * 1. int[] reverse(int[] a): reverses array in place
 * 2. int[] intersect(int[] a, int[] b): finds the intersection of 2 arrays including duplicates
 * 3. int[] difference(int[] a, int[] b): finds the unique elements in both arrays
 * 4. List<List<Integer>> findIncreasingSubarrays(int[] a): creates monolithic sublists from integer array in increasing order
 * 5. void getSumPairs(int[] a, int sum): prints all array pairs with sum equal to given
 * 6. List<List<Integer>> getSumSubarray(int[] a, int sum): finds all contigious subarrays with given sum
 * 7. void sumSubsets(int[] a, int sum): prints all subsets of array whose sum equals to given number
 * 8. int largestSubarray(int[] a): finds subarray with largest sum
 * 9. findLongestIncreasingSubarray(int[] a): finds the longest increasing contigious subarray
 * 10. getLIS(int[] x): finds the longest increasing subsequence
 * 11. find2ndLargest(int[] a): finds 2nd largest element
 * 12. findKthLargest(int[] list, int k): finds Kth largest element
 * 13. findKLargest(int[] list, int k): finds K largest elements
 * 14. coinsChange(int amount): finds all possible combinations with denominations matching the given number
 * 15. minChangeNumber(int n, int[] coins): finds a minimum number of coins required to make a change for a given number
 * 16. permute(Integer[] num): finds all permutations
 * 17. permuteUnique(int[] num): finds all unique permutations
 * 18. getMissingElement(int[] a): finds a missing item in 1..N-1 array
 * 19. getUniqueElement(int[] a): finds the only unique element among duplicates
 * 20. increaseByOne(int[] a): increments by one big integers represented as arrays
 */

public class ArrayUtils {

	// reverses array in place
	// [1, 2, 3, 4] -> [4, 3, 2, 1]
	// O(n)
	static int[] reverse(int[] a) {
		for (int i = 0, j = a.length - 1; i < j; i++, j--) {
	        int swap = a[i];
	        a[i] = a[j];
	        a[j] = swap;
	    }
		return a;
	}

	// finds the intersection of 2 arrays including duplicates
	// [1, 2, 3, 4, 2, 3], [2, 3, 4, 2, 5, 6] -> [2, 3, 4, 2]
	// O(n)
	static int[] intersect(int[] a, int[] b) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) {
			if (map.containsKey(a[i]))
				map.put(a[i], map.get(a[i]) + 1);
			else
				map.put(a[i], 1);
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int k: b) {
			if (map.containsKey(k) && map.get(k) > 0) {
				list.add(k);
				map.put(k, map.get(k) - 1);
			}
		}
		Integer[] resObj = list.toArray(new Integer[0]);
		int[] res = new int[resObj.length];
		for (int i = 0; i < resObj.length; i++)
			res[i] = resObj[i].intValue();
		return res;
	}
	
	// finds the unique elements in both arrays
	// [1, 2, 3, 4, 2, 3], [2, 3, 4, 2, 5, 6] -> [1, 3, 5, 6]
	// O(n)
	static int[] difference(int[] a, int[] b) {
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) {
			if (map1.containsKey(a[i]))
				map1.put(a[i], map1.get(a[i]) + 1);
			else
				map1.put(a[i], 1);
		}
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		for (int i = 0; i < b.length; i++) {
			if (map2.containsKey(b[i]))
				map2.put(b[i], map2.get(b[i]) + 1);
			else
				map2.put(b[i], 1);
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i: a) {
			if (map2.containsKey(i)) {
				int value = map2.get(i);
				if (value > 1)
					map2.put(i, value - 1);
				else
					map2.remove(i);
			} else
				list.add(i);
		}
		for (int i: b) {
			if (map1.containsKey(i)) {
				int value = map1.get(i);
				if (value > 1)
					map1.put(i, value - 1);
				else
					map1.remove(i);
			} else
				list.add(i);
		}
		Integer[] resObj = list.toArray(new Integer[0]);
		int[] res = new int[resObj.length];
		for (int i = 0; i < resObj.length; i++)
			res[i] = resObj[i].intValue();
		return res;
	}
	
	// creates monolithic sublists from integer array in increasing order
	// [0, 1, 3, 5, 2, 4, 9, 6, 12, 8] -> [[0, 1, 3, 5], [2, 4, 9], [6, 12], [8]]
	// O(n)
	static List<List<Integer>> findIncreasingSubarrays(int[] a) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> subList = new ArrayList<Integer>();
		int prev = a[0];
		subList.add(a[0]);
		for (int i = 1; i < a.length; i++) {
			if (a[i] >= prev) {
				subList.add(a[i]);
			} else {
				list.add(subList);
				subList = new ArrayList<Integer>();
				subList.add(a[i]);
			}
			prev = a[i];
		}
		if (!subList.isEmpty())
			list.add(subList);
		return list;
	}
	
	// prints all array pairs with sum equal to given
	// ([1, 4, 2, 3, 3, 4], 5) -> [(1, 4), (2, 3)]
	// O(n)
	public static void getSumPairs(int[] a, int sum) {
		// remove duplicates in array
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < a.length; i++)
			set.add(a[i]);
		// fill hashmap with all elements, print only qualifying ones
	    Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
	    for (Integer value: set)
	        if (pairs.containsKey(value))
	            System.out.println(value + ", " + pairs.get(value));
	        else
	            pairs.put(sum - value, value);
	}

	// finds all contigious subarrays with given sum
	// ([1, 3, 8, 5, 4, 1, 9, 1, 4, 3, 2], 10) -> [[5, 4, 1], [1, 9], [9, 1], [1, 4, 3, 2]]
	// O(n)
	static List<List<Integer>> getSumSubarray(int[] a, int sum) {
		List<List<Integer>> list2d = new ArrayList<List<Integer>>();
		int n = a.length;
		int s = 0, l = 0, r = 0;
		while (r <= n) {
			if (s == sum) {
				List<Integer> list = new ArrayList<Integer>();
				for (int j = l; j < r; j++)
					list.add(a[j]);
				list2d.add(list);
				s -= a[l++];
			} else if (s > sum) {
				s -= a[l++];
			} else {
				if (r < n)
					s += a[r];
				r++;
			}
		}
		return list2d;
	}
	
	// prints all subsets of array whose sum equals to given number
	// ([1, 3, 5, 2, 7], 10) -> [[1, 2, 7], [3, 5, 2], [3, 7]]
	// O(2^n)
	static void sumSubsets(int[] a, int sum) {
		subset(a, sum, 0, 0, "");
	}
	
	private static void subset(int[] a, int targetSum, int sum, int index, String output) {
		if (sum > targetSum)
			return;
		if (sum == targetSum) {
			System.out.println(output);
			return;
		}
		for (int i = index; i < a.length; i++)
			subset(a, targetSum, sum + a[i], i + 1, output + " " +  a[i] + " ");
	}
	
	// finds subarray with largest sum (Kadane's algorithm)
	// [-1, 3, -4, 8, 5, 2, -3, -4] -> 15
	// O(n)
	static int largestSubarray(int[] a) {
		int newsum = a[0], max = a[0];
		for (int i = 1; i < a.length; i++) {
			newsum = Math.max(newsum + a[i], a[i]);
	        max = Math.max(max, newsum);
		}
	    return max;
	}

	// gets the longest increasing contigious subarray
	// [0, 1, 3, 5, 2, 4, 9, 6, 12, 8, 10, 7, 11, 12, 13, 14, 12, 13] -> [7, 11, 12, 13, 14]
	// O(n)
	static List<Integer> findLongestIncreasingSubarray(int[] a) {
		List<Integer> res = new ArrayList<Integer>();
		int prev = a[0];
		int start = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] - prev > 0) {
				if (i == a.length - 1)
					res = getSubarray(a, res, start, i + 1);
			} else {
				res = getSubarray(a, res, start, i);
				start = i;
			}
			prev = a[i];
		}
		return res;
	}
	
	private static List<Integer> getSubarray(int[] a, List<Integer> res, int start, int end) {
		int length = res.size();
		if (length == 0 || length < end - start) {
			res.clear();
			for (int i = start; i < end; i++)
				res.add(a[i]);
		}
		return res;
	}
	
	// gets the longest increasing subsequence
	// [0, 1, 3, 5, 2, 4, 9, 6, 12, 8, 10, 7, 11, 12, 13, 14, 12, 13] -> [0, 1, 3, 5, 6, 8, 10, 11, 12, 13, 14]
	// O(n^2)
	static int[] getLIS(int[] x) {
	    int n = x.length;
	    int[] len = new int[n];
	    Arrays.fill(len, 1);
	    int[] pred = new int[n];
	    Arrays.fill(pred, -1);
	    for (int i = 1; i < n; i++) {
	    	for (int j = 0; j < i; j++) {
	    		if (x[j] < x[i] && len[i] < len[j] + 1) {
	    			len[i] = len[j] + 1;
	    			pred[i] = j;
	    		}
	    	}
	    }
	    int bi = 0;
	    for (int i = 1; i < n; i++) {
	    	if (len[bi] < len[i])
	    		bi = i;
	    }
	    int cnt = len[bi];
	    int[] res = new int[cnt];
	    for (int i = bi; i != -1; i = pred[i])
	    	res[--cnt] = x[i];
	    return res;
	}
	
	static Integer firstUniqueInt(int[] a) {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) {
			Integer value = map.get(a[i]);
			map.put(a[i], value == null ? 1 : value + 1);
		}
		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			if (entry.getValue() == 1)
				return entry.getKey();
		}
		return null;
	}
	
	public static int find2ndLargest(int[] a) {
		if (a == null)
			return -1;
		if (a.length == 1)
			return a[0];
		int max = a[0] < a[1] ? a[1] : a[0];
		int max2 = a[0] < a[1] ? a[0] : a[1];
		for (int i = 2; i < a.length; i++) {
			if (a[i] > max) {
				max2 = max;
				max = a[i];
			} else if (a[i] > max2)
				max2 = a[i];
		}
		return max2;
	}
	
	// O(n) in average
	static int findKthLargest(int[] list, int k) {
		int left = 0;
		int right = list.length - 1;
		while (true) {
			int pivotIndex = (left + right) / 2;
			int newPivot = partition(list, left, right, pivotIndex);
			if (newPivot == k - 1)
				return list[newPivot];
			else if (newPivot < k - 1)
				left = newPivot + 1;
			else
				right = newPivot - 1;
		}
	}
	
	static int[] findKLargest(int[] list, int k) {
		int left = 0;
		int right = list.length - 1;
		while (true) {
			int pivotIndex = (left + right) / 2;
			int newPivot = partition(list, left, right, pivotIndex);
			if (newPivot == k - 1) {
				int[] res = new int[k];
				for (int i = 0; i < k; i++)
					res[i] = list[i];
				return res;
			} else if (newPivot < k - 1)
				left = newPivot + 1;
			else
				right = newPivot - 1;
		}
	}
	
	private static int partition(int[] list, int left, int right, int pivot) {
		int pivotValue = list[pivot];
		swap(list, pivot, right); // put pivot value on the end
		int storePos = left;
		for (int i = left; i < right; i++) {
			if (list[i] > pivotValue) { // in case of smallest: use '<'
				swap(list, i, storePos);
				storePos++;
			}
		}
		swap(list, storePos, right);
		return storePos;
	}
	
	private static void swap(int[] list, int a, int b) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

	// finds all possible combinations with denominations matching the given number
	static final int[] DENOMINATIONS = {1,5,10,18};//{18, 10, 5, 1};

	public static void coinsChange(int amount) {
		change(amount, new ArrayList<Integer>(), 0);
	}

	private static void change(int remaining, List<Integer> coins, int pos) {
	    if (remaining == 0)
	    	System.out.println(coins);
	    else {
	    	if (remaining >= DENOMINATIONS[pos]) {
	    		coins.add(DENOMINATIONS[pos]);
	    		change(remaining - DENOMINATIONS[pos], coins, pos);
	    		coins.remove(coins.size() - 1);
	    	}
	    	if (pos + 1 < DENOMINATIONS.length)
	    		change(remaining, coins, pos + 1);
	    }
	}
	
	// finds minimum number of coins required to make a change for a given number
	private static int mem[] = new int[10001]; // can support up to 10000 dollar value
    
	public static int minChangeNumber(int n, int[] coins) {
        if (n < 0)
            return Integer.MAX_VALUE - 1;
        else if (n == 0)
            return 0;
        else if (mem[n] != 0) // if solved previously already
            return mem[n];
        else {
            // look for the minimal among the different denominations
            mem[n] = 1 + minChangeNumber(n - coins[0], coins);
            for (int i = 1; i < coins.length; i++)
            	mem[n] = Math.min(mem[n], 1 + minChangeNumber(n - coins[i], coins));
            return mem[n];
        }
    }
	
	// non-recursive solution
	public static int[] minChangeNumber2(int changeAmount, int[] denom) {
        int n = denom.length;
        int[] count = new int[changeAmount + 1];
        int[] from = new int[changeAmount + 1];
        count[0] = 1;
        for (int i = 0 ; i < changeAmount; i++)
            if (count[i] > 0)
                for (int j = 0; j < n; j++) {
                    int p = i + denom[j];
                    if (p <= changeAmount) {
                        if (count[p] == 0 || count[p] > count[i] + 1) {
                            count[p] = count[i] + 1;
                            from[p] = j;
                        }
                    }
                }
        if (count[changeAmount] == 0) // no solutions
            return null;
        // build answer
        int[] result = new int[count[changeAmount] - 1];
        int k = changeAmount;
        while (k > 0) {
            result[count[k] - 2] = denom[from[k]];
            k = k - denom[from[k]];
        }
        return result;
    }
	
	// permutations
	// O(n*n!)
	static ArrayList<ArrayList<Integer>> permute(Integer[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute(num, 0, result);
		return result;
	}
	 
	private static void permute(Integer[] num, int start, ArrayList<ArrayList<Integer>> result) {
		if (start >= num.length) {
			ArrayList<Integer> item = new ArrayList<Integer>(Arrays.asList(num));
			result.add(item);
		}
		for (int j = start; j <= num.length - 1; j++) {
			swap(num, start, j);
			permute(num, start + 1, result);
			swap(num, start, j);
		}
	}
	 
	private static void swap(Integer[] list, int a, int b) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

	// unique permutations
	static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		returnList.add(new ArrayList<Integer>());
		for (int i = 0; i < num.length; i++) {
			Set<ArrayList<Integer>> currentSet = new HashSet<ArrayList<Integer>>();
			for (List<Integer> l: returnList) {
				for (int j = 0; j < l.size() + 1; j++) {
					l.add(j, num[i]);
					ArrayList<Integer> T = new ArrayList<Integer>(l);
					l.remove(j);
					currentSet.add(T);
				}
			}
			returnList = new ArrayList<ArrayList<Integer>>(currentSet);
		}
		return returnList;
	}
	
	static int getMissingElement(int[] a) {
		int sum = 0;
		int n = a.length;
		for (int i = 0; i < n; i++)
			sum += a[i];
		int m = n + 1;
		int missing = m * (m + 1) / 2 - sum;
		return missing;
	}
	
	static int getUniqueElement(int[] a) {
		int xor = 0;
		for (int i = 0; i < a.length; i++)
		    xor ^= a[i];
		return xor;
	}
	
	static int getDuplicateElement(int[] a) {
		for (int i = 0; i < a.length; i++)
		    if (a[Math.abs(a[i])] >= 0)
		    	a[Math.abs(a[i])] = -a[Math.abs(a[i])];
		    else
		    	return Math.abs(a[i]);
		return -1;
	}
	
	static int[] increaseByOne(int[] a) {
		if (a == null)
			return null;
		List<Integer> list = new ArrayList<Integer>();
		for (Integer i: a) {
			if (i < 0 || i > 9)
				throw new IllegalArgumentException();
			list.add(i);
		}
		int n = a.length;
		if (n < 1)
			return null;
		for (int i = n - 1; i >= 0; i--) {
			if (list.get(i) == 9) {
				list.set(i, 0);
				if (i == 0)
					list.add(0, 1);
			} else {
				list.set(i, list.get(i) + 1);
				break;
			}
		}
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		return arr;
	}
	
	static void print(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]+ " ");
	}
	
	static void print(List<Integer> a) {
		for (Integer i: a)
			System.out.print(i + " ");
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 3, 5, 2, 4, 9, 6, 12, 8, 10, 7, 11, 12, 13, 14, 12, 13};
		//int[] array = {0, 1, 3, 5, 2, 4, 1, 3, 4, 3, 5, 2, 6};
		//int[] subArray = {3, 5, 2, 6};
		//int[] a = {3, 5, 2, 6, 3, 5, 2};
		//System.out.println(findSubArray(array, subArray));
		//System.out.println(find2ndLargest(array));
		
		//print(findKLargest(array, 5));
		
		/*ArrayList<ArrayList<Integer>> res = permuteUnique(new int[] {1, 1, 2, 2});
		for (ArrayList<Integer> a: res) {
			for (Integer i: a) {
				System.out.print(i);
			}
			System.out.println();
		}*/
		//System.out.println(largestSubarray(new int[] {-1, 3, -4, 8, 5, 2, -3, -4}));
		//print(difference(new int[] {1, 2, 3, 4, 2, 3}, new int[] {2, 3, 4, 2, 5, 6}));
		//getSumPairs(new int[] {1,4,2,3,3,4}, 5);
		//print(findLongestIncreasingSubarray(array));
		//print(getLIS(new int[] {5,6,1,7,2,3,4}));
		//int[] res = reverse(array);

		/*List<List<Integer>> res = findIncreasingSubArrays(array);
		for (List<Integer> a: res) {
			for (Integer i: a) {
				System.out.print(i + " ");
			}
			System.out.println();
		}*/
		
		//System.out.print(minChangeNumber(34, DENOMINATIONS));
		//System.out.print(getUniqueElement(new int[] {4,1,5,2,3,1,4,5,2}));
		/*List<List<Integer>> list2d = getSumSubarray(new int[] {1, 3, 5, 2, 1, 1, 1, 1, 1, 1, 3}, 4);
		for (List<Integer> list: list2d) {
			for (Integer v: list)
				System.out.print(v + " ");
			System.out.println();
		}*/
		//sumSubsets(new int[] {1, 3, 5, 2, 7}, 10);
		
		int[] arr = minChangeNumber2(20, DENOMINATIONS);
		for (int i: arr)
			System.out.print(i + " ");
		
		/*List<List<Integer>> res = getSumSubarray(new int[] {1, 3, 8, 5, 4, 1, 9, 1, 4, 3, 2}, 10);
		for (List<Integer> l: res) {
			for (Integer i: l) {
				System.out.print(i + " ");
			}
			System.out.println();
		}*/
		//System.out.print(getDuplicateElement(new int[] {1, 2, 3, 1, 4, 5, -6, -1}));
	}

}
