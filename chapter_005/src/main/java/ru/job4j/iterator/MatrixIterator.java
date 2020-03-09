package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class MatrixIterator, returning the sequential numbers' order.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 27.06.2019
 */
public class MatrixIterator implements Iterator<Integer> {

    private final int[][] values;
    private int row = 0;
    private int column = 0;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    /**
     * Checking the existence of the next value in the matrix.
     *
     * @return true or false.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (row < values.length && column < values[row].length) {
            result = true;
        }
        return result;
    }

    /**
     * Returning the value of the matrix.
     *
     * @return the value.
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int value = values[row][column++];
        if (column > values[row].length - 1) {
            row++;
            column = 0;
        }
        return value;
    }
}
