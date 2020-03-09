package ru.job4j.generic.store;

/**
 * Class UserStore.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.07.2019
 */
public class UserStore extends AbstractStore<User> {

    public UserStore(int size) {
        super(size);
    }
}
