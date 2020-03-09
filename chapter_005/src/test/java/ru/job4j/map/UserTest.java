package ru.job4j.map;

import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 31.07.2019
 */
public class UserTest {

    @Test
    public void whenNonOverrideEqualsThen() {
        Map<User, Object> map = new HashMap<>();
        User first = new User("Alex", 3, LocalDate.of(1988, 4, 15));
        User second = new User("Alex", 3, LocalDate.of(1988, 4, 15));
        map.put(first, "one");
        map.put(second, "two");
        System.out.println(map);
    }
}