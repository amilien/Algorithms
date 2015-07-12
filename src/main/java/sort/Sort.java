package sort;

public class Sort {

    static int[] array = {3, 2, 6, 9, 8, 1, 7, 4, 5, 0};
    static int[] sortedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10};
    
    static void quickSort(int[] arr, int l, int r) {
        int index = partition(arr, l, r);
        //System.out.println("index=" + index);
        if (index > l + 1)
            quickSort(array, l, index - 1);
        if (index < r)
            quickSort(array, index, r);
    }
    
    static int partition(int[] arr, int l, int r) {
    	//System.out.println("partition: l=" + l + ", r=" + r);
        int left = l, right = r;
        int pivot = arr[(right + left) / 2];
        while (left <= right) {
            while (arr[left] < pivot)
                left++;
            while (arr[right] > pivot)
                right--;
            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }
    
    static boolean binarySearch(int[] arr, int v) {
    	int start = 0, end = arr.length - 1;
    	while (start <= end) {
        	int mid = (end + start) / 2;
    		if (v > arr[mid])
    			start = mid + 1;
    		else if (v < arr[mid])
    			end = mid - 1;
    		else
    			return true;
    	}
    	return false;
    } // 0 2 4 6 8 10

    static boolean binarySearchR(int[] arr, int v) {
    	return search(arr, v, 0, arr.length - 1);
    }
    
    private static boolean search(int[] arr, int v, int lo, int hi) {
    	if (lo > hi)
    		return false;
    	int mid = (lo + hi) / 2;
    	if (v > arr[mid])
    		return search(arr, v, mid + 1, hi);
    	else if (v < arr[mid])
    		return search(arr, v, lo, mid - 1);
    	else
    		return true;
    }
    
    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    public static void main(String[] args) {
        //quickSort(array, 0, array.length - 1);
        print(array);
    	//System.out.println(binarySearchR(sortedArray, 10));
    }

}
