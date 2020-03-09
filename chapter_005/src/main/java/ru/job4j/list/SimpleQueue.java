package ru.job4j.list;

/**
 * Class SimpleQueue.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 27.07.2019
 */
public class SimpleQueue<T> {

    /**
     * An input stack based on LinkedList.
     */
    private SimpleStack<T> in = new SimpleStack<>();

    /**
     * An output stack based on LinkedList.
     */
    private SimpleStack<T> out = new SimpleStack<>();

    /**
     * The queue's size.
     * @return .
     */
    public int size() {
        return this.in.size() + this.out.size();
    }

    /**
     * Addition an element to a queue.
     * @param value an element.
     */
    public void push(T value) {
        this.in.push(value);
    }

    /**
     * Removing an element from a queue.
     * @return the removed element.
     */
    public T poll() {
        if (this.out.size() == 0) {
            while (!(this.in.size() == 0)) {
                this.out.push(this.in.pop());
            }
        }
        return this.out.pop();
    }
}
