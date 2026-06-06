package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        Node(T item) {
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

    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<>(item);
        node.prev = sentinel;
        node.next = sentinel.next;
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> node = new Node<>(item);
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (Node<T> node = sentinel.next; node != sentinel; node = node.next) {
            System.out.print(node.item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = sentinel.next;
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
        return node.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = sentinel.prev;
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
        return node.item;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
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
        if (isEmpty()) {
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

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> p;

        LinkedListDequeIterator() {
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public T next() {
            T item = p.item;
            p = p.next;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> d = (Deque<T>) o;
        if (this.size() != d.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(this.get(i), d.get(i))) {
                return false;
            }
        }
        return true;
    }
}
