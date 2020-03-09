package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class IteratorEven, returning the even numbers only.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 30.05.2019
 */
public class IteratorEven implements Iterator<Integer> {

    private final int[] values;
    private int index = 0;

    public IteratorEven(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return values[index++];
    }
}
