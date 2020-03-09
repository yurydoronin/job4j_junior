package ru.job4j.generic.store;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 20.07.2019
 */
public class AbstractStoreTest {

    /**
     * A container for users only.
     */
    private final UserStore users = new UserStore(2);

    /**
     * A container for roles only.
     */
    private final RoleStore roles = new RoleStore(2);

    /**
     * Test add and findByID.
     */
    @Test
    public void whenHasModelThenAddToContainer() {
        User user = new User("Max");
        Role role = new Role("Doctor");
        users.add(user);
        roles.add(role);
        assertThat(user, is(this.users.findById("Max")));
        assertThat(role, is(this.roles.findById("Doctor")));
    }

    /**
     * Test replace.
     */
    @Test
    public void whenHasIDThenReplaceModel() {
        User user1 = new User("Max");
        Role role1 = new Role("Doctor");
        users.add(user1);
        roles.add(role1);
        assertThat(true, is(users.replace("Max", new User("Alex"))));
        assertThat(true, is(roles.replace("Doctor", new Role("Racer"))));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenHasIDThenDeleteModel() {
        User user1 = new User("Max");
        Role role1 = new Role("Doctor");
        User user2 = new User("Alex");
        Role role2 = new Role("Racer");
        users.add(user1);
        users.add(user2);
        roles.add(role1);
        roles.add(role2);
        assertThat(true, is(users.delete("Max")));
        assertThat(true, is(roles.delete("Doctor")));
    }
}