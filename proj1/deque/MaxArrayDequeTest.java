package deque;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Comparator;


public class MaxArrayDequeTest {
    private static class MaxIntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    private static class MinIntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    @Test
    public void intComparatorTest() {
        MaxArrayDeque<Integer> m = new MaxArrayDeque<>(new MaxIntComparator());
        m.addLast(4);
        m.addLast(8);
        m.addLast(4);
        assertEquals(8, (int) m.max());
        assertEquals(4, (int) m.max(new MinIntComparator()));
    }

    @Test
    public void stringComparatorTest() {
        MaxArrayDeque<String> m = new MaxArrayDeque<>(new StringComparator());
        m.addLast("I");
        m.addLast("love");
        m.addLast("CS61B");
        assertEquals("love", m.max());
    }
}
