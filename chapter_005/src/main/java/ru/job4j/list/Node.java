package ru.job4j.list;

/**
 * Class Node.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 30.07.2019
 */
public class Node<T> {

    /**
     * A value.
     */
    T value;

    /**
     * A link to the next node.
     */
    Node<T> next;

    /**
     *
     * @param value .
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Checking whether there is a circular reference in the linked list.
     * Using Floyd's tortoise and the hare algorithm.
     *
     * @param first, a first node.
     * @return .
     */
    public boolean hasLoop(Node<T> first) {
        boolean result = false;
        Node<T> slow = first;
        Node<T> fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}
