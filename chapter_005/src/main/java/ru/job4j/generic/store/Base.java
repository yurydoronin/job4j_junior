package ru.job4j.generic.store;

/**
 * Class Base.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.07.2019
 */
public abstract class Base {

    /**
     * A model's ID.
     */
    private final String id;

    /**
     * A constructor.
     *
     * @param id, ID.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * A getter.
     *
     * @return ID.
     */
    public String getId() {
        return id;
    }
}
