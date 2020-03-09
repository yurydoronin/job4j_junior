package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import javax.annotation.Nonnull;
import java.util.Iterator;

/**
 * Class SimpleSet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 2.0
 * @since 26.07.2019
 */
public class SimpleSet<T> implements Iterable<T> {

    /**
     * A container which is implemented in the SimpleArrayList class,
     * using the composition approach.
     */
    private SimpleArrayList<T> container;

    /**
     * A constructor.
     *
     * @param size .
     */
    public SimpleSet(int size) {
        this.container = new SimpleArrayList<>(size);
    }

    /**
     *
     * @return a container's size.
     */
    public int size() {
        return this.container.size();
    }

    /**
     *
     * @param value .
     * @return .
     */
    private boolean contains(T value) {
        boolean duplicate = false;
        for (int index = 0; index < this.size(); index++) {
            if (value.equals(this.container.get(index))) {
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }

    /**
     *
     * @param value .
     */
    public void add(T value) {
        if (!this.contains(value)) {
            this.container.add(value);
        }
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return this.container.iterator();
    }
}
