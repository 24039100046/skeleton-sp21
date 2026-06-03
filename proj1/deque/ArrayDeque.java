package deque;

public class ArrayDeque<T> {
    private static class ArrayList<T> {
        T[] array;
        int size;

        public ArrayList() {
            array = (T[]) new Object[4];
            size = 0;
        }

        public void addLast(T item) {
            if (size == array.length) {
                resize(array.length * 2);
            }
            array[size] = item;
            size++;
        }

        public int size() {
            return size;
        }

        public T removeLast() {
            if (size >= 4 && array.length / size >= 4) {
                resize(array.length / 2);
            }
            T item = array[size - 1];
            array[size - 1] = null;
            size--;
            return item;
        }

        public T get(int index) {
            return array[index];
        }

        private void resize(int capacity) {
            T[] newArray = (T[]) new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
        }
    }

    ArrayList<T> prev;
    ArrayList<T> next;

    public ArrayDeque() {
        prev = new ArrayList<>();
        next = new ArrayList<>();
    }

    public void addFirst(T item) {
        prev.addLast(item);
    }

    public void addLast(T item) {
        next.addLast(item);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return prev.size() + next.size();
    }

    public void printDeque() {
        for (int i = prev.size() - 1; i > 0; i--) {
            System.out.print(prev.get(i) + " ");
        }
        for (int i = 0; i < next.size(); i++) {
            System.out.print(next.get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        return prev.removeLast();
    }

    public T removeLast() {
        return next.removeLast();
    }

    /**
     * index                          return
     * 0                              prev.get(prev.size() - 1)
     * prev.size() - 1                prev.get(0)
     * prev.size()                    next.get(0)
     * prev.size() + next.size() - 1  next.get(next.size() - 1)
     */
    public T get(int index) {
        if (index < prev.size()) {
            return prev.get(prev.size() - 1 - index);
        } else {
            return next.get(index - prev.size());
        }
    }
}
