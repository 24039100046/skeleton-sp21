package deque;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCounts = new LinkedListDeque<>();
        for (int N = 1000; N <= 128000; N *= 2) {
            Ns.addLast(N);
            opCounts.addLast(10000);
        }
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        for (int i = 0; i < Ns.size(); i++) {
            LinkedListDeque<Integer> l = new LinkedListDeque<>();
            for (int j = 0; j < Ns.get(i); j++) {
                l.addLast(0);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < 10000; j++) {
                l.get(l.size() - 1);
            }
            times.addLast(sw.elapsedTime());
        }
        printTimingTable(Ns, times, opCounts);
    }

}
