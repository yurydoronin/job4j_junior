package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 30.07.2019
 */
public class SimpleQueueTest {

    /**
     * Test push.
     */
    @Test
    public void whenNewElementThenPushToQueue() {
        SimpleQueue<String> simQueue = new SimpleQueue<>();
        simQueue.push("Masha");
        simQueue.push("Dasha");
        assertThat(simQueue.size(), is(2));
    }

    /**
     * Test pop.
     */
    @Test
    public void whenLastElementThenPopFromStack() {
        SimpleQueue<String> simQueue = new SimpleQueue<>();
        simQueue.push("Masha");
        simQueue.push("Dasha");
        simQueue.push("Sasha");
        assertThat(simQueue.poll(), is("Masha"));
        assertThat(simQueue.size(), is(2));
    }
}