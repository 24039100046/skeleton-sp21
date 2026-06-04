package deque;

public class ArrayDeque<T> {
    private T[] array;
    private int prev;
    private int next;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        prev = -1;
        next = 0;
    }

    private int mod(int a, int b) {
        return ((a % b) + b) % b;
    }

    private int validateIndex(int n) {
        return mod(n, array.length);
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = prev + 1; i <= next - 1; i++) {
            newArray[mod(i, capacity)] = array[validateIndex(i)];
        }
        array = newArray;
    }

    public void addFirst(T item) {
        if (size() == array.length) {
            resize(array.length * 2);
        }
        array[validateIndex(prev)] = item;
        prev--;
    }

    public void addLast(T item) {
        if (size() == array.length) {
            resize(array.length * 2);
        }
        array[validateIndex(next)] = item;
        next++;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return next - prev - 1;
    }

    public void printDeque() {
        for (int i = prev + 1; i <= next - 1; i++) {
            System.out.print(array[validateIndex(i)] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size() > 0) {
            if (size() >= 4 && array.length / size() >= 4) {
                resize(array.length / 2);
            }
            T item = array[validateIndex(prev + 1)];
            array[validateIndex(prev + 1)] = null;
            prev++;
            return item;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (size() > 0) {
            if (size() >= 4 && array.length / size() >= 4) {
                resize(array.length / 2);
            }
            T item = array[validateIndex(next - 1)];
            array[validateIndex(next - 1)] = null;
            next--;
            return item;
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (index >= 0 && index < size()) {
            return array[validateIndex(prev + 1 + index)];
        } else {
            return null;
        }
    }
}
