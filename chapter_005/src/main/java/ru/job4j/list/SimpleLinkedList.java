package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleLinkedList.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 25.07.2019
 */
public class SimpleLinkedList<T> implements Iterable<T> {

    /**
     * A collection's size.
     */
    private int size = 0;

    /**
     * The number of times this list has been structurally modified.
     */
    private int modCount = 0;

    /**
     * Pointer to first node.
     */
    private Node<T> first;

    /**
     * Pointer to last node.
     */
    private Node<T> last;

    public int size() {
        return this.size;
    }

    /**
     * Addition data into the beginning of the list.
     *
     * @param data .
     */
    public void add(T data) {
        this.addElement(data);
    }

    private void addElement(T data) {
        final Node<T> last = this.last;
        final Node<T> newNode = new Node<>(last, data, null);
        this.last = newNode;
        if (last == null) {
            this.first = newNode;
        } else {
            last.next = newNode;
        }
        this.size++;
        this.modCount++;
    }

    /**
     * Getting the element by index.
     *
     * @param index .
     * @return .
     */
    public T get(int index) {
        if (!(index >= 0 && index < this.size)) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        return this.getNode(index).data;
    }

    private Node<T> getNode(int index) {
        Node<T> result = null;
        if (index < this.size / 2) {
            Node<T> temp = this.first;
            for (int i = 0; i < this.size / 2 + 1; i++) {
                if (i == index) {
                    result = temp;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        } else {
            Node<T> temp = this.last;
            for (int i = this.size - 1; i > this.size / 2 - 1; i--) {
                if (i == index) {
                    result = temp;
                    break;
                } else {
                    temp = temp.prev;
                }
            }
        }
        return result;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return .
     */
    public T removeLast() {
        final Node<T> l = this.last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    /**
     * Unlinks non-null last node l.
     */
    private T unlinkLast(Node<T> l) {
        final T element = l.data;
        final Node<T> prev = l.prev;
        l.data = null;
        l.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }


    /**
     * A class for keeping the data.
     *
     * @param <T> .
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    /**
     *
     * @return .
     */
    public Iterator<T> iterator() {

        return new Iterator<>() {

            /**
             * A current position.
             */
            int cursor;

            /**
             * A current element.
             */
            Node<T> current = first;

            /**
             * The modCount value that the iterator believes that the backing
             * List should have. If this expectation is violated, the iterator
             * has detected concurrent modification.
             */
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                checkForComodification();
                Node<T> result;
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    result = current;
                    this.current = result.next;
                    this.cursor++;
                }
                return result.data;
            }

            private void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
