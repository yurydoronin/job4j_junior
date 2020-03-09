package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.08.2019
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * A tree's root.
     */
    private Node<E> root;

    /**
     * A tree's size.
     */
    private int size = 0;

    /**
     * The number of times this list has been structurally modified.
     */
    private int modCount = 0;

    /**
     * A constructor.
     *
     * @param root, a tree root.
     */
    public Tree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * A node in a tree.
     *
     * @param <E> a child node.
     */
    protected static class Node<E extends Comparable<E>> {

        /**
         * A list of children of a particular node.
         */
        private final List<Node<E>> children = new ArrayList<>();

        /**
         * A value.
         */
        private final E value;

        /**
         * A constructor.
         *
         * @param value, a value.
         */
        public Node(final E value) {
            this.value = value;
        }

        /**
         * Gets a node's value.
         *
         * @return a value.
         */
        public E getValue() {
            return value;
        }

        /**
         * Inserts a child into a list of children of the node.
         *
         * @param child, a child node.
         */
        public void add(Node<E> child) {
            this.children.add(child);
        }

        /**
         * Returns a list of children of the parent.
         *
         * @return a list of children of the parent.
         */
        public List<Node<E>> leaves() {
            return this.children;
        }

        /**
         * Compares the nodes.
         *
         * @param that, a node.
         * @return true or false.
         */
        public boolean eqValue(E that) {
            return this.value.compareTo(that) == 0;
        }
    }

    /**
     * Addition a child element into parent.
     *
     * @param parent, a parent.
     * @param child,  a child.
     * @return true or false.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (parent.compareTo(child) != 0) {
            if (findBy(child).isEmpty()) {
                Optional<Node<E>> element = findBy(parent);
                result = element.isPresent();
                if (result) {
                    element.get().add(new Node<>(child));
                    this.size++;
                    modCount++;
                }
            }
        }
        return result;
    }

    /**
     * Looking for a tree node by value.
     *
     * @param value, a value.
     * @return the Optional.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Checking the tree is binary or not.
     *
     * @return true or false.
     */
    @Override
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            List<Node<E>> children = el.leaves();
            if (children.size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : children) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * An iterator to iterate over a tree.
     *
     * @return a value of the tree node.
     */
    public Iterator<E> iterator() {

        return new Iterator<>() {

            /**
             * A container for all elements in the tree.
             */
            private Queue<Node<E>> data = new LinkedList<>(List.of(root));

            /**
             * A tree node.
             */
            Node<E> node;

            /**
             * A current position.
             */
            int cursor = 0;

            /**
             * The modCount value that the iterator believes that the backing
             * List should have. If this expectation is violated, the iterator
             * has detected concurrent modification.
             */
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                checkForComodification();
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.node = data.poll();
                for (Node<E> child : node.leaves()) {
                    data.offer(child);
                }
                this.cursor++;
                return this.node.getValue();
            }

            private void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
