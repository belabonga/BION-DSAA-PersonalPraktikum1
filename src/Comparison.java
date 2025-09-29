import util.Bench;
import util.PrettyTable;

import java.util.*;

public class Comparison {

    private static int[] makeArray(int n, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(1_000_000);
        return a;
    }

    private static List<Integer> makeArrayListFrom(int[] a) {
        List<Integer> list = new ArrayList<>(a.length);
        for (int v : a) list.add(v);
        return list;
    }

    public static void main(String[] args) {
        final int N = 1_000;
        final int REPEAT = 5;
        final long SEED = 42L;

        int[] data = makeArray(N, SEED);
        final int key = data[N / 3];

        // ARRAY OPS
        long tTraverseArr = Bench.timeNanos(() -> ArrayOperations.traverse(data), REPEAT);

        long tLinArr = Bench.timeCall(() -> ArrayOperations.linearSearch(data, key), REPEAT).nanos;

        int[] sorted = ArrayOperations.copyAndSort(data);
        long tBinArr = Bench.timeCall(() -> ArrayOperations.binarySearch(sorted, key), REPEAT).nanos;

        long tInsertArr = Bench.timeNanos(() -> ArrayOperations.insertAt(data, N / 2, 123456), REPEAT);

        long tDeleteArr = Bench.timeNanos(() -> ArrayOperations.deleteFirst(data, key), REPEAT);

        // ARRAYLIST OPS (USING SEPARATE VARIABLES, NO REASSIGN)
        final List<Integer> listA = makeArrayListFrom(data);
        long tTraverseAL = Bench.timeNanos(() -> ArrayListOperations.traverse(listA), REPEAT);

        final List<Integer> listB = makeArrayListFrom(data);
        long tAddAL = Bench.timeNanos(() -> ArrayListOperations.add(listB, 123456), REPEAT);

        final List<Integer> listC = makeArrayListFrom(data);
        long tRemoveAL = Bench.timeNanos(() -> ArrayListOperations.removeFirst(listC, key), REPEAT);

        final List<Integer> listD = makeArrayListFrom(data);
        long tIndexAL = Bench.timeNanos(() -> ArrayListOperations.indexOf(listD, key), REPEAT);

        final List<Integer> listE = makeArrayListFrom(data);
        long tSortAL = Bench.timeNanos(() -> ArrayListOperations.sort(listE), REPEAT);

        // OUTPUT
        PrettyTable table = new PrettyTable("Operasi", "Array (ms)", "ArrayList (ms)");
        table.addRow("Traversal",  fmt(tMillis(tTraverseArr)), fmt(tMillis(tTraverseAL)));
        table.addRow("Search - Linear / indexOf", fmt(tMillis(tLinArr)), fmt(tMillis(tIndexAL)));
        table.addRow("Search - Binary (sorted)", fmt(tMillis(tBinArr)), "-");
        table.addRow("Insert (mid) / Add(end)",  fmt(tMillis(tInsertArr)), fmt(tMillis(tAddAL)));
        table.addRow("Delete First / remove",    fmt(tMillis(tDeleteArr)), fmt(tMillis(tRemoveAL)));
        table.addRow("Sort",                     "-",                      fmt(tMillis(tSortAL)));

        System.out.println("\n=== Perbandingan Kinerja (N=" + N + ", repeat=" + REPEAT + ") ===");
        table.print();

        int[] arrDemo = {10,20,30,40,50};
        java.util.List<Integer> alDemo = new java.util.ArrayList<>();
        ArrayListOperations.init(alDemo, 10,20,30,40,50);

        // TRANSVERSAL
        System.out.println("\nArray Traversal: " + java.util.Arrays.toString(arrDemo));
        System.out.println("ArrayList Traversal: " + alDemo);

        // SEARCH
        int idxArr = ArrayOperations.linearSearch(arrDemo, 30);
        int idxAL  = alDemo.indexOf(30);
        System.out.println("\nPencarian 30 dalam Array: " + 
            (idxArr >= 0 ? "Ditemukan di indeks " + idxArr : "Tidak ditemukan"));
        System.out.println("Pencarian 30 dalam ArrayList: " + 
            (idxAL >= 0 ? "Ditemukan di indeks " + idxAL : "Tidak ditemukan"));

        // INSERT
        int[] arrInserted = ArrayOperations.insertAt(arrDemo, 2, 25);
        java.util.List<Integer> alInserted = new java.util.ArrayList<>(alDemo);
        alInserted.add(2, 25);
        System.out.println("\nArray setelah penyisipan elemen 25: " + java.util.Arrays.toString(arrInserted));
        System.out.println("ArrayList setelah penyisipan elemen 25: " + alInserted);

        // EXECUTION TIME COMPARISON
        long tArrSearch = Bench.timeNanos(() -> ArrayOperations.linearSearch(arrDemo, 30), REPEAT);
        long tALSearch  = Bench.timeNanos(() -> alDemo.indexOf(30), REPEAT);
        System.out.println("\nWaktu eksekusi pencarian pada Array: " + fmt(tMillis(tArrSearch)) + " ms");
        System.out.println("Waktu eksekusi pencarian pada ArrayList: " + fmt(tMillis(tALSearch)) + " ms");
    }

    // HELPER METHODS
    private static double tMillis(long nanos) {
        return Bench.toMillis(nanos);
    }

    private static String fmt(double ms) {
        return String.format(java.util.Locale.US, "%.4f", ms);
    }

}
