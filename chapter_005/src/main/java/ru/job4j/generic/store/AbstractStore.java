package ru.job4j.generic.store;

import ru.job4j.generic.SimpleArray;

/**
 * Class AbstractStore.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.07.2019
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * A container.
     */
    private final SimpleArray<T> simpleArray;

    /**
     * The size of a container.
     */
    private final int size;

    /**
     * A constructor.
     *
     * @param size, the container's size.
     */
    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<>(size);
        this.size = size;
    }

    /**
     * Addition an element to the container.
     *
     * @param model, a model.
     */
    @Override
    public void add(T model) {
        this.simpleArray.add(model);
    }

    /**
     * Replacing the element in the container by ID.
     *
     * @param id,    the model's ID.
     * @param model, a model.
     * @return true or false.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int index = 0; index < this.size; index++) {
            if (this.simpleArray.get(index).getId().equals(id)) {
                this.simpleArray.set(index, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Removing the element from the container by ID.
     *
     * @param id, the model's ID.
     * @return true or false.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index < this.size; index++) {
            if (this.simpleArray.get(index).getId().equals(id)) {
                this.simpleArray.remove(index);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Finding the element by ID.
     *
     * @param id, the model's ID.
     * @return the element.
     */
    @Override
    public T findById(String id) {
        T model = null;
        for (int index = 0; index < this.size; index++) {
            if (this.simpleArray.get(index).getId().equals(id)) {
                model = this.simpleArray.get(index);
                break;
            }
        }
        return model;
    }
}
