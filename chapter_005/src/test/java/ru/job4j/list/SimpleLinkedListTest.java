package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

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
 * @since 25.07.2019
 */
public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
    }

    /**
     * Test add and get.
     */
    @Test
    public void whenNewElementThenAddToList() {
        this.list.add(3);
        assertThat(list.get(2), is(3));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenHasNextThenTrue() {
        Iterator<Integer> it = this.list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
    }

    /**
     * Test NoSuchElementException.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenNextThenThrowException() {
        Iterator<Integer> it = this.list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        it.next();
    }

    /**
     * Test ConcurrentModificationException.
     */
    @Test (expected = ConcurrentModificationException.class)
    public void whenCollectionModifiedThenThrowException() {
        Iterator<Integer> itr = this.list.iterator();
        itr.next();
        this.list.add(4);
        itr.next();
    }
}