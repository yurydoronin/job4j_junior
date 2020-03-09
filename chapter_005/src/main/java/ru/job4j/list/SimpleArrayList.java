package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleArrayList.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 2.0
 * @since 24.07.2019
 */
public class SimpleArrayList<T> implements Iterable<T> {

    /**
     * A container.
     */
    private T[] objects;

    /**
     * A pointer.
     */
    private int index = 0;

    /**
     * The number of times this list has been structurally modified.
     */
    private int modCount = 0;

    /**
     *
     * @param size .
     */
    @SuppressWarnings("unchecked")
    public SimpleArrayList(int size) {
        this.objects = (T[]) new Object[size];
    }

    /**
     *
     * @return a container's size.
     */
    public int size() {
        return this.objects.length;
    }

    /**
     *
     * @param value .
     */
    public void add(T value) {
        if (this.index > this.objects.length - 1) {
            this.increase();
        }
        this.objects[index++] = value;
        this.modCount++;
    }

    /**
     * To double the container's size.
     */
    private void increase() {
        this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
    }

    /**
     *
     * @param index .
     */
    public T get(int index) {
        return this.objects[index];
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
            int cursor = 0;

            /**
             * The modCount value that the iterator believes that the backing
             * List should have. If this expectation is violated, the iterator
             * has detected concurrent modification.
             */
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < index;
            }

            @Override
            public T next() {
                checkForComodification();
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return objects[cursor++];
            }

            private void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
