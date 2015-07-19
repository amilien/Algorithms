package interviews.google;

import java.util.ArrayList;
import java.util.List;

public class PhoneScreen {

    // task 1: find a missing item in 1..N-1 array
    static int getMissingElement(int[] a) {
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++)
            sum += a[i];
        int m = n + 1;
        int missing = m * (m + 1) / 2 - sum;
        return missing;
    }

    // task 2: increment by one a big integer represented as an input array
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

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 5, 6, 7, 8, 9};
        System.out.println(getMissingElement(a));
    }
}
