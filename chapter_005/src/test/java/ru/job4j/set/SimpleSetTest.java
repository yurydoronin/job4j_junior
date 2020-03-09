package ru.job4j.set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 26.07.2019
 */
public class SimpleSetTest {

    private SimpleSet<String> simSet;

    @Before
    public void beforeTest() {
        this.simSet = new SimpleSet<>(2);
        this.simSet.add("Sasha");
        this.simSet.add("Masha");
    }

    /**
     * Test add.
     */
    @Test
    @Ignore
    public void whenNewElementThenAddToSetWithoutDuplicates() {
        this.simSet.add("Dasha");
        System.out.println(this.simSet.size());
        this.simSet.add("Dasha");
        assertThat(this.simSet.size(), is(3));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenHasNextThenTrue() {
        Iterator<String> it = this.simSet.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Sasha"));
    }

    /**
     * Test NoSuchElementException.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenNextThenThrowException() {
        Iterator<String> it = this.simSet.iterator();
        assertThat(it.next(), is("Sasha"));
        assertThat(it.next(), is("Masha"));
        it.next();
    }

    /**
     * Test ConcurrentModificationException.
     */
    @Test (expected = ConcurrentModificationException.class)
    public void whenCollectionModifiedThenThrowException() {
        Iterator<String> itr = this.simSet.iterator();
        itr.next();
        this.simSet.add("Dasha");
        itr.next();
    }
}