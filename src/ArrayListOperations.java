import java.util.*;

public class ArrayListOperations {

    public static List<Integer> init(List<Integer> list, int... values) {
        list.clear();
        for (int v : values) list.add(v);
        return list;
    }

    public static String traverse(List<Integer> list) {
        return list.toString();
    }

    public static void add(List<Integer> list, int value) {
        list.add(value); // AMORTIZED O(1)
    }

    public static boolean removeFirst(List<Integer> list, int value) {
        return list.remove((Integer) value); // O(n) MOVE ELEMENT
    }

    public static int indexOf(List<Integer> list, int value) {
        return list.indexOf(value); // O(n)
    }

    public static void sort(List<Integer> list) {
        Collections.sort(list); // O(n log n)
    }
}
