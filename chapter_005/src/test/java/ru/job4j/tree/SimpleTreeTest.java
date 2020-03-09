package ru.job4j.tree;

import org.junit.Before;
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
 * @since 14.08.2019
 */
public class SimpleTreeTest {

    private SimpleTree<Integer> tree;

    @Before
    public void beforeTest() {
        this.tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 5);
        tree.add(5, 6);
    }

    /**
     * Test add and findBy.
     */
    @Test
    public void whenSixElementsFindLastThenReturnSix() {
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    /**
     * Test.
     */
    @Test
    public void when6ElementsFindNotExistThenOptionEmpty() {
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    /**
     * Test isBinary.
     */
    @Test
    public void whenTreeBinaryThenTrue() {
        assertThat(tree.isBinary(), is(true));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenHasNextThenTrue() {
        Iterator<Integer> it = this.tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    /**
     * Test NoSuchElementException.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenNextThenThrowException() {
        Iterator<Integer> it = this.tree.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        it.next();
    }

    /**
     * Test ConcurrentModificationException.
     */
    @Test (expected = ConcurrentModificationException.class)
    public void whenTreeModifiedThenThrowException() {
        Iterator<Integer> itr = this.tree.iterator();
        itr.next();
        this.tree.add(6, 7);
        itr.next();
    }
}