package ru.job4j.map;


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
 * @since 07.08.2019
 */
public class SimpleMapTest {

    private SimpleMap<String, String> map = new SimpleMap<>();

    @Before
    public void beforeTest() {
        map.insert("Smith", "Alex");
        map.insert("Black", "John");
        map.insert("McDonald", "Mike");
    }

    /**
     * Test insert and get.
     */
    @Test
    public void whenNewElementThenAddToMap() {
        map.insert("Smith", "Julia");
        assertThat(map.size(), is(3));
        assertThat(this.map.get("Smith"), is("Julia"));
        assertThat(this.map.get("Black"), is("John"));
        assertThat(this.map.get("McDonald"), is("Mike"));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenElementThenDeleteByKey() {
        map.delete("Black");
        assertThat(map.size(), is(2));
    }

    /**
     * Test iterator.
     */
    @Ignore
    @Test
    public void whenHasNextThenTrue() {
        Iterator it = this.map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("..."));
    }

    /**
     * Test NoSuchElementException.
     */
    @Ignore
    @Test (expected = NoSuchElementException.class)
    public void whenNextThenThrowException() {
        Iterator it = this.map.iterator();
        assertThat(it.next(), is("..."));
        assertThat(it.next(), is("..."));
        assertThat(it.next(), is("..."));
        it.next();
    }

    /**
     * Test ConcurrentModificationException.
     */
    @Test (expected = ConcurrentModificationException.class)
    public void whenMapModifiedThenThrowException() {
        Iterator it = this.map.iterator();
        it.next();
        this.map.insert("Lee", "Linda");
        it.next();
    }
}