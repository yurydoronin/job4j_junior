package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 24.07.2019
 */
public class OneWayLinkedListTest {

    private OneWayLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new OneWayLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * Test get.
     */
    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    /**
     * Test getSize.
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteElementThenDeleteFirstElement() {
        list.delete();
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(2));
    }
}