package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleMap.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 07.08.2019
 */
public class SimpleMap<K, V> {

    /**
     * A container.
     */
    @SuppressWarnings("unchecked")
    private Node<K, V>[] objects = new Node[DEFAULT_CAPACITY];

    /**
     * The default initial capacity.
     */
    static final int DEFAULT_CAPACITY = 16;

    /**
     * The load factor used when none specified in constructor.
     */
    static final double DEFAULT_LOAD_FACTOR = 0.75;

    /**
     * A container's fullness.
     */
    private int position = 0;

    /**
     * The next size value at which to resize (capacity * load factor).
     */
    private int threshold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);

    /**
     * The number of times this list has been structurally modified.
     */
    private int modCount = 0;

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public int size() {
        return this.position;
    }

    /**
     * Computes a hash of the key.hashCode().
     *
     * @param key, a value's key.
     * @return a hash.
     */
    private static int hash(Object key) {
        int h = key.hashCode();
        return key == null ? 0 : (h ^ h >>> 16);
    }

    /**
     * Inserting a value by the key.
     *
     * @param key,   a value's key.
     * @param value, a value.
     * @return true or false.
     */
    public boolean insert(K key, V value) {
        boolean result = true;
        if (this.position == this.threshold) {
            this.increase();
        }
        int index = indexFor(key);
        if (key != null
                && this.objects[index] != null
                && key.equals(this.objects[index].getKey())) {
            this.objects[index].setValue(value);
            this.modCount++;
            result = false;

        }
        if (result) {
            final Node<K, V> newNode = new Node<>(hash(key), key, value, null);
            this.objects[index] = newNode;
            this.position++;
            this.modCount++;
        }
        return result;
    }

    /**
     *
     * @param key .
     * @return .
     */
    private int indexFor(K key) {
        return hash(key) & (this.objects.length - 1);
    }

    /**
     * Getting a value.
     *
     * @param key, a value's key.
     */
    public V get(K key) {
        V value = null;
        int index = indexFor(key);
        if (key.equals(this.objects[index].getKey())) {
            value = this.objects[index].value;
        }
        return value;
    }

    /**
     * Removing an element by the key.
     *
     * @param key, a value's key.
     * @return true or false.
     */
    public boolean delete(K key) {
        boolean result = false;
        int i = indexFor(key);
        if (key.equals(this.objects[i].getKey())) {
            this.objects[i] = null;
            result = true;
        }
        this.position--;
        this.modCount++;
        return result;
    }

    /**
     * To double a container's size.
     */
    private void increase() {
        int newCapacity = this.objects.length * 2;
        @SuppressWarnings("unchecked") Node<K, V>[] newTable = new Node[newCapacity];
        transfer(newTable);
        this.objects = newTable;
        threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);
    }

    /**
     * Transfers nodes from an old table into the new one.
     *
     * @param newTable, the resized table.
     */
    private void transfer(Node[] newTable) {
        for (Node node : this.objects) {
            if (node != null) {
                int index = hash(node.getKey()) & (newTable.length - 1);
                newTable[index] = node;
            }
        }
    }

    /**
     *
     * @return .
     */
    public Iterator<Node<K, V>> iterator() {

        return new Iterator<>() {

            Node<K, V> node;

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
                return cursor < position;
            }

            @Override
            public Node<K, V> next() {
                checkForComodification();
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int i = cursor; i < objects.length; i++) {
                    if (objects[i] != null) {
                        this.node = objects[i];
                        cursor++;
                        break;
                    }
                }
                return this.node;
            }

            private void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
