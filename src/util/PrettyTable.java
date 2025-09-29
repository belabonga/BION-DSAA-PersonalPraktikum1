package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrettyTable {
    private final List<String> headers = new ArrayList<>();
    private final List<List<String>> rows = new ArrayList<>();

    public PrettyTable(String... headers) {
        this.headers.addAll(Arrays.asList(headers));
    }

    public void addRow(Object... cells) {
        List<String> r = new ArrayList<>(cells.length);
        for (Object c : cells) r.add(String.valueOf(c));
        rows.add(r);
    }

    public void print() {
        int cols = headers.size();
        int[] w = new int[cols];
        for (int i = 0; i < cols; i++) w[i] = headers.get(i).length();
        for (List<String> r : rows) {
            for (int i = 0; i < cols; i++) {
                w[i] = Math.max(w[i], r.get(i).length());
            }
        }

        StringBuilder sb = new StringBuilder();

        // header
        for (int i = 0; i < cols; i++) {
            sb.append(pad(headers.get(i), w[i]));
            sb.append(i == cols - 1 ? "\n" : " | ");
        }

        // separator
        for (int i = 0; i < cols; i++) {
            sb.append(repeat("-", w[i]));
            sb.append(i == cols - 1 ? "\n" : "-+-");
        }

        // rows
        for (List<String> r : rows) {
            for (int i = 0; i < cols; i++) {
                sb.append(pad(r.get(i), w[i]));
                sb.append(i == cols - 1 ? "\n" : " | ");
            }
        }

        System.out.print(sb.toString());
    }

    private static String pad(String s, int w) {
        int len = s.length();
        if (len >= w) return s;
        return s + repeat(" ", w - len);
    }

    private static String repeat(String s, int n) {
        if (n <= 0) return "";
        StringBuilder b = new StringBuilder(s.length() * n);
        for (int i = 0; i < n; i++) b.append(s);
        return b.toString();
    }
}
