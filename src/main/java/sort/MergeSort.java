package sort;

public class MergeSort {

	// merge 2 sorted arrays into 1
	static int[] merge(int[] a, int[] b) {
		int m = a.length, n = b.length;
		int[] res = new int[m + n];
		int i = 0, j = 0, k = 0;
		while (i < m || j < n) {
			if (i == m)
				res[k++] = b[j++];
			else if (j == n)
				res[k++] = a[i++];
			else if (a[i] < b[j])
				res[k++] = a[i++];
			else
				res[k++] = b[j++];
		}
		return res;
	}

	// mergesort
	public static void mergesort(int[] src) {
		mergesort(src, 0, src.length - 1);
	}
	
	private static void mergesort(int[] src, int lo, int hi) {
		if (lo < hi) {
			int mid = (lo + hi) / 2;
			mergesort(src, lo, mid);
			mergesort(src, mid + 1, hi);
			merge(src, lo, mid, hi);
		}
	}
	
	private static void merge(int[] src, int lo, int mid, int hi) {
		int[] tmp = new int[src.length];
		for (int i = 0; i < src.length; i++)
			tmp[i] = src[i];
		int i = lo, j = mid + 1, k = lo;
		while (i <= mid || j <= hi) {
			if (i > mid)
				src[k++] = tmp[j++];
			else if (j > hi)
				src[k++] = tmp[i++];
			else if (tmp[i] < tmp[j])
				src[k++] = tmp[i++];
			else
				src[k++] = tmp[j++];
		}
	}
	
    /*private static int[] merge(int[] arr, int l, int mid, int r) {
        int[] helper = new int[arr.length];
        for (int i = l; i <= r; i++)
            helper[i] = arr[i];
        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r)
        	arr[k++] = helper[helper[i] < helper[j] ? i++ : j++];
        while (i <= mid)
            arr[k++] = helper[i++];
        return arr;
    }
    
    private static int[] merge(int[] arr, int l, int mid, int r) {
        int[] helper = new int[arr.length];
        for (int i = l; i <= r; i++)
            helper[i] = arr[i];
        // copy the smallest values from either the left or the right side back to the original array
        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r) {
        	if (helper[i] < helper[j]) {
        		arr[k] = helper[i];
        		i++;
        	} else {
        		arr[k] = helper[j];
        		j++;
        	}
        	k++;
        }
        // copy the rest of the left side of the array into the target array
        while (i <= mid) {
            arr[k] = helper[i];
            k++;
            i++;
        }
        while (j <= r) {
            arr[k] = helper[j];
            k++;
            j++;
        }
        return arr;
    }*/
    
	public static void print(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		//int[] unsorted = new int[] {5, 3, 6, 1, 4, 8, 9, 2};
		int[] unsorted = {4, 5, 2, 9, 8, 0, 3, 7, 1, 6};
		int[] a = {1, 5, 7};
		int[] b = {0, 2, 3, 4, 6, 8, 9};
		//int[] c = merge(a, b);
		mergesort(unsorted);
		print(unsorted);
	}

}
