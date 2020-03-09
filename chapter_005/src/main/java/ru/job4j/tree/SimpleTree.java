package ru.job4j.tree;

import java.util.Optional;

/**
 * Class SimpleTree.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.08.2019
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Addition a child element into parent.
     * Parent can have a list of children.
     *
     * @param parent parent.
     * @param child  child.
     * @return true or false.
     */
    boolean add(E parent, E child);

    /**
     * The searches of a tree node by value.
     *
     * @param value, .
     * @return .
     */
    Optional<Tree.Node<E>> findBy(E value);

    /**
     * Checking the tree is binary or not.
     *
     * @return true or false.
     */
    boolean isBinary();
}
