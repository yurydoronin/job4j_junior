package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 13.07.2019
 */
public class SimpleArrayTest {

    private final SimpleArray<String> simpleArray = new SimpleArray<>(3);

    /**
     * Test add and get.
     */
    @Test
    public void whenNewElementThenAddToArray() {
        simpleArray.add("Masha");
        simpleArray.add("Sasha");
        simpleArray.add("Dasha");
        assertThat("Sasha", is(simpleArray.get(1)));
    }

    /**
     * Test set.
     */
    @Test
    public void whenNewElementThenRewriteTheOldElement() {
        simpleArray.add("Masha");
        simpleArray.add("Sasha");
        simpleArray.add("Dasha");
        simpleArray.set(1, "Max");
        assertThat("Max", is(simpleArray.get(1)));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenHaveIndexThenRemoveTheElement() {
        simpleArray.add("Masha");
        simpleArray.add("Sasha");
        simpleArray.add("Dasha");
        String[] expected = {"Sasha", "Dasha"};
        simpleArray.remove(0);
        assertThat(simpleArray.getAll(), is(expected));
    }

    /**
     * Test ArrayIndexOutOfBoundsException.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenHaveIndexThenGetException() {
        simpleArray.add("Masha");
        simpleArray.add("Sasha");
        simpleArray.add("Dasha");
        simpleArray.add("Julia");
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenHasNextThenTrue() {
        simpleArray.add("Masha");
        simpleArray.add("Sasha");
        simpleArray.add("Dasha");
        Iterator<String> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Masha"));
    }

    /**
     * Test NoSuchElementException.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenNextThenThrowException() {
        simpleArray.add("Masha");
        simpleArray.add("Sasha");
        simpleArray.add("Dasha");
        Iterator<String> it = simpleArray.iterator();
        assertThat(it.next(), is("Masha"));
        assertThat(it.next(), is("Sasha"));
        assertThat(it.next(), is("Dasha"));
        it.next();
    }
}