package ru.job4j.list;

import org.junit.Before;
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
public class NodeTest {

    Node<Integer> first;
    Node<Integer> second;
    Node<Integer> third;
    Node<Integer> fourth;

    @Before
    @SuppressWarnings("unchecked")
    public void beforeTest() {
        this.first = new Node(1);
        this.second = new Node(2);
        this.third = new Node(3);
        this.fourth = new Node(4);
    }

    @Test
    public void whenLastLoopOnFirstThenTrue() {
        this.first.next = second;
        this.second.next = third;
        this.third.next = fourth;
        this.fourth.next = first;
        assertThat(first.hasLoop(first), is(true));
    }

    @Test
    public void whenLoopInTheMiddleThenTrue() {
        this.first.next = second;
        this.second.next = third;
        this.third.next = second;
        assertThat(first.hasLoop(first), is(true));
    }

    @Test
    public void whenNoLoopThenFalse() {
        this.first.next = second;
        this.second.next = third;
        this.third.next = fourth;
        assertThat(first.hasLoop(first), is(false));
    }
}