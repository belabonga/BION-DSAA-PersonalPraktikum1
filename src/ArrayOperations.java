import java.util.Arrays;

public class ArrayOperations {

    // TRAVERSAL : RETURN STRING REPRESENTATION OF ARRAY FOR EASY CHECK/PRINT
    public static String traverse(int[] a) {
        return Arrays.toString(a);
    }

    // LINEAR SEARCH 0(n) : RETURN INDEX OR -1
    public static int linearSearch(int[] a, int key) {
        for (int i = 0; i < a.length; i++) if (a[i] == key) return i;
        return -1;
    }

    // BINARY SEARCH 0(log n) : NEED SORTED ARRAY, RETURN INDEX OR -1
    public static int binarySearch(int[] sorted, int key) {
        int lo = 0, hi = sorted.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int v = sorted[mid];
            if (v == key) return mid;
            if (v < key) lo = mid + 1; else hi = mid - 1;
        }
        return -1;
    }

    // INSERT AT INDEX idx (0..n), RETURN NEW ARRAY (USING System.arraycopy → EFFICIENT)
    public static int[] insertAt(int[] a, int idx, int value) {
        if (idx < 0 || idx > a.length) throw new IndexOutOfBoundsException();
        int[] res = new int[a.length + 1];
        System.arraycopy(a, 0, res, 0, idx);
        res[idx] = value;
        System.arraycopy(a, idx, res, idx + 1, a.length - idx);
        return res;
    }

    // DELETE FIRST OCCURRENCE OF VALUE, RETURN NEW ARRAY
    public static int[] deleteFirst(int[] a, int value) {
        int idx = linearSearch(a, value);
        if (idx < 0) return a; // tidak ada, return asli
        int[] res = new int[a.length - 1];
        System.arraycopy(a, 0, res, 0, idx);
        System.arraycopy(a, idx + 1, res, idx, a.length - idx - 1);
        return res;
    }

    // UTILITY: COPY & SORT (DOES NOT MUTATE INPUT → SAFE FOR COMPARISON)
    public static int[] copyAndSort(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        return b;
    }
}
