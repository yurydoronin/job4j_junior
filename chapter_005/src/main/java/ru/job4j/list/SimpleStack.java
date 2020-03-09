package ru.job4j.list;

/**
 * Class SimpleStack.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 27.07.2019
 */
public class SimpleStack<T> {

    /**
     * A container based on LinkedList.
     */
    private SimpleLinkedList<T> container = new SimpleLinkedList<>();

    public int size() {
        return this.container.size();
    }

    /**
     *
     * @param value .
     */
    public void push(T value) {
        this.container.add(value);
    }

    /**
     *
     * @return .
     */
    public T pop() {
        return this.container.removeLast();
    }
}
