package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 27.07.2019
 */
public class SimpleStackTest {

    /**
     * Test push.
     */
    @Test
    public void whenNewElementThenPushToStack() {
        SimpleStack<String> simStack = new SimpleStack<>();
        simStack.push("Masha");
        simStack.push("Dasha");
        assertThat(simStack.size(), is(2));
    }

    /**
     * Test pop.
     */
    @Test
    public void whenLastElementThenPopFromStack() {
        SimpleStack<String> simStack = new SimpleStack<>();
        simStack.push("Masha");
        simStack.push("Dasha");
        simStack.push("Sasha");
        assertThat(simStack.pop(), is("Sasha"));
        assertThat(simStack.size(), is(2));
    }
}