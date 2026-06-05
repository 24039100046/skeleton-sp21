package deque;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(ArrayDeque<Integer> Ns, ArrayDeque<Double> times, ArrayDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        ArrayDeque<Integer> Ns = new ArrayDeque<>();
        for (int N = 1000; N <= 10000000; N *= 2) {
            Ns.addLast(N);
        }
        ArrayDeque<Double> times = new ArrayDeque<>();
        for (int i = 0; i < Ns.size(); i++) {
            ArrayDeque<Integer> l = new ArrayDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < Ns.get(i); j++) {
                l.addLast(0);
            }
            times.addLast(sw.elapsedTime());
        }
        printTimingTable(Ns, times, Ns);
    }
}
