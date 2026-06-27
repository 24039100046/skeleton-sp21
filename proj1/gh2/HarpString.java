package gh2;

import deque.ArrayDeque;
import deque.Deque;

public class HarpString implements MusicString {
    private static final int SR = 44100;
    private static final double DECAY = .996;

    private Deque<Double> buffer;

    public HarpString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        capacity *= 2;
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast((double) 0);
        }
    }

    @Override
    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            buffer.addLast(Math.random() - 0.5);
        }
    }

    @Override
    public void tic() {
        double first1 = buffer.get(0), first2 = buffer.get(1);
        double last = (first1 + first2) * 0.5 * DECAY;
        last *= -1;
        buffer.removeFirst();
        buffer.addLast(last);
    }

    @Override
    public double sample() {
        return buffer.get(0);
    }
}
