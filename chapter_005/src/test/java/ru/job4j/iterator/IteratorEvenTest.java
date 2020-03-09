package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 30.05.2019
 */
public class IteratorEvenTest {

    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new IteratorEven(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new IteratorEven(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void allNumbersAreEven() {
        it = new IteratorEven(new int[]{2, 4, 6, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
    }

    @Test
    public void whenThen() {
        IteratorEven it = new IteratorEven(new int[] {4, 1, 1, 1});
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenIteratorNextThenEven() {
        IteratorEven it = new IteratorEven(new int[]{4, 2, 1, 1});
        it.next();
        int result = it.next();
        assertThat(result, is(2));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorNextThenZero() {
        IteratorEven it = new IteratorEven(new int[]{4, 2, 1, 1});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void whenIteratorHasNextThenTrue() {
        IteratorEven it = new IteratorEven(new int[]{4, 2, 1, 1});
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(true));
    }

    @Test
    public void whenIteratorHasNextThenFalse() {
        IteratorEven it = new IteratorEven(new int[]{4, 2, 1, 1});
        it.next();
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }

}