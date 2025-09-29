package util;

import java.util.concurrent.Callable;

public final class Bench {
    private Bench() {}

    public static long timeNanos(Runnable job, int repeat) {
        if (repeat < 1) repeat = 1;
        long best = Long.MAX_VALUE;
        for (int i = 0; i < repeat; i++) {
            long t0 = System.nanoTime();
            job.run();
            long t1 = System.nanoTime();
            best = Math.min(best, t1 - t0);
        }
        return best;
    }

    public static <T> Result<T> timeCall(Callable<T> job, int repeat) {
        if (repeat < 1) repeat = 1;
        long best = Long.MAX_VALUE;
        T value = null;
        for (int i = 0; i < repeat; i++) {
            long t0 = System.nanoTime();
            try {
                value = job.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            long t1 = System.nanoTime();
            best = Math.min(best, t1 - t0);
        }
        return new Result<>(value, best);
    }

    public static final class Result<T> {
        public final T value;
        public final long nanos;
        Result(T value, long nanos) { this.value = value; this.nanos = nanos; }
    }

    public static double toMillis(long nanos) {
        return nanos / 1_000_000.0;
    }
}
