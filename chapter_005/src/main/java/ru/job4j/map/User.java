package ru.job4j.map;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class User.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 31.07.2019
 */
public class User {

    private String name;
    private int children;
    private LocalDate birthday;

    public User(String name, int children, LocalDate birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return String.format("User: %s, children %s, birthday %s",
                this.name,
                this.children,
                this.birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.name, user.name)
                && Objects.equals(this.birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.birthday);
    }
}
