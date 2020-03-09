package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Class OneWayLinkedList.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 24.07.2019
 */
public class OneWayLinkedList<T> {

    /**
     * A collection's size.
     */
    private int size;

    /**
     * Pointer to first node.
     */
    private Node<T> first;

    /**
     * Addition data into the beginning of the list.
     *
     * @param data .
     */
    public void add(T data) {
        Node<T> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Removing the first element from the list.
     *
     * @return the removed element.
     */
    public T delete() {
        final Node<T> f = this.first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * Unlinks non-null first node f.
     *
     * @param f node.
     * @return the removed element.
     */
    private T unlinkFirst(Node<T> f) {
        final T element = f.data;
        final Node<T> next = f.next;
        f.data = null;
        f.next = null;
        first = next;
        size--;
        return element;
    }

    /**
     * Getting the element by index.
     *
     * @param index .
     * @return .
     */
    public T get(int index) {
        Node<T> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Getting the collection's size.
     *
     * @return .
     */
    public int getSize() {
        return this.size;
    }

    /**
     * A class for keeping the data.
     *
     * @param <T> .
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
}
