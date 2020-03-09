package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleArray.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 12.07.2019
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     *
     */
    private final Object[] objects;

    /**
     *
     */
    private int index = 0;

    /**
     *
     * @param size .
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     *
     * @param model .
     */
    public void add(T model) {
        if (index < objects.length) {
            this.objects[index++] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException("The collection is full. No elements can be added.");
        }
    }

    /**
     *
     * @param index .
     * @param model .
     */
    public void set(int index, T model) {
        if (index < this.index) {
            this.objects[index] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException("Out of limit.");
        }
    }

    /**
     *
     * @param index .
     */
    public void remove(int index) {
        System.arraycopy(this.objects, index + 1, this.objects, index, this.objects.length - index - 1);
        this.index -= 1;
    }

    /**
     *
     * @param index .
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) this.objects[index];
    }

    /**
     *
     * @return an array of all elements.
     */
    @SuppressWarnings("unchecked")
    public <T> T[] getAll() {
        return (T[]) Arrays.copyOf(this.objects, this.index);
    }

    /**
     *
     * @return .
     */
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {

        return new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < index;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) objects[i++];
            }
        };
    }
}
