package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int nextFront;
    private int nextBack;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        nextFront = 4;
        nextBack = 5;
    }

    private static int mod(int a, int b) {
        return ((a % b) + b) % b;
    }

    private int validateIndex(int n) {
        return mod(n, array.length);
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = nextFront + 1; i <= nextBack - 1; i++) {
            newArray[mod(i, capacity)] = array[validateIndex(i)];
        }
        array = newArray;
    }

    @Override
    public void addFirst(T item) {
        if (size() == array.length) {
            resize(array.length * 2);
        }
        array[validateIndex(nextFront)] = item;
        nextFront--;
    }

    @Override
    public void addLast(T item) {
        if (size() == array.length) {
            resize(array.length * 2);
        }
        array[validateIndex(nextBack)] = item;
        nextBack++;
    }

    @Override
    public int size() {
        return nextBack - nextFront - 1;
    }

    @Override
    public void printDeque() {
        for (int i = nextFront + 1; i <= nextBack - 1; i++) {
            System.out.print(array[validateIndex(i)] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size() >= 4 && array.length / size() >= 4) {
            resize(array.length / 2);
        }
        T item = array[validateIndex(nextFront + 1)];
        array[validateIndex(nextFront + 1)] = null;
        nextFront++;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size() >= 4 && array.length / size() >= 4) {
            resize(array.length / 2);
        }
        T item = array[validateIndex(nextBack - 1)];
        array[validateIndex(nextBack - 1)] = null;
        nextBack--;
        return item;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index >= 0 && index < size()) {
            return array[validateIndex(nextFront + 1 + index)];
        } else {
            return null;
        }
    }
}
