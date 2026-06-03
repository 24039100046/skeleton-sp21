package deque;

public class LinkedListDeque<T> {
    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<>(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> node = new Node<>(item);
        node.prev = sentinel;
        node.next = sentinel.next;
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }

    public void addLast(T item) {
        Node<T> node = new Node<>(item);
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node<T> node = sentinel.next; node != sentinel; node = node.next) {
            System.out.print(node.item + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> node = sentinel.next;
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
        return node.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> node = sentinel.prev;
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
        return node.item;
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        }
        Node<T> node = sentinel.next;
        int i = 0;
        while (node != sentinel && i < index) {
            node = node.next;
            i++;
        }
        if (i == index) {
            return node.item;
        } else {
            return null;
        }
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, Node<T> node) {
        if (node == sentinel) {
            return null;
        }
        if (index == 0) {
            return node.item;
        } else {
            return getRecursive(index - 1, node.next);
        }
    }

    //实现了iterator应该容易一点
    //public boolean equals(Object o) {
    //    if (!(o instanceof LinkedListDeque)) {
    //        return false;
    //    }
    //}
}
