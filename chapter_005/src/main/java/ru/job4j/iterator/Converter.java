package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Converter, returning the sequential order of values.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 12.07.2019
 */
public class Converter {

    /**
     * @param it .
     * @return .
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<>() {

            private Iterator<Integer> current = it.next();

            @Override
            public boolean hasNext() {
                while (!current.hasNext() && it.hasNext()) {
                    current = it.next();
                }
                return current.hasNext();
            }

            @Override
            public Integer next() throws NoSuchElementException {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return current.next();
            }
        };
    }
}
