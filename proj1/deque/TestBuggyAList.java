package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        LinkedListDeque<Integer> list1 = new LinkedListDeque<>();
        ArrayDeque<Integer> list2 = new ArrayDeque<>();
        for (int i = 4; i <= 6; i++) {
            list1.addLast(i);
            list2.addLast(i);
        }
        for (int i = 0; i < 3; i++) {
            Integer item1 = list1.removeLast();
            Integer item2 = list2.removeLast();
            assertEquals(item1, item2);
        }
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber) {
                case 0 -> {
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    L1.addLast(randVal);
                    L2.addLast(randVal);
                    System.out.println("addLast(" + randVal + ")");
                }
                case 1 -> {
                    // size
                    int size1 = L1.size();
                    int size2 = L2.size();
                    System.out.println("size()");
                    assertEquals(size1, size2);
                }
                case 2 -> {
                    // getLast
                    if (L1.size() > 0 && L2.size() > 0) {
                        int last1 = L1.get(L1.size() - 1);
                        int last2 = L2.get(L2.size() - 1);
                        System.out.println("getLast()");
                        assertEquals(last1, last2);
                    }
                }
                case 3 -> {
                    // removeLast
                    if (L1.size() > 0 && L2.size() > 0) {
                        int last1 = L1.removeLast();
                        int last2 = L2.removeLast();
                        System.out.println("removeLast()");
                        assertEquals(last1, last2);
                    }
                }
            }
        }
    }
}
