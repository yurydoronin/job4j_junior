package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixIteratorTest {

    private Iterator<Integer> it;
    private Iterator<Integer> iter;

    @Before
    public void setUp() {
        it = new MatrixIterator(new int[][]{{1, 2, 3}, {4, 5, 6}});
        iter = new MatrixIterator(new int[][]{{1}, {3, 4}, {7}});
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        it = new MatrixIterator(new int[][]{});
        it.next();
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation2() {
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(3));
        assertThat(iter.next(), is(4));
        assertThat(iter.next(), is(7));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder2() {
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(3));
        assertThat(iter.next(), is(4));
        assertThat(iter.next(), is(7));
    }

    @Test
    public void hasNextNextSequentialInvocation2() {
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(4));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(7));
        assertThat(iter.hasNext(), is(false));
    }
}