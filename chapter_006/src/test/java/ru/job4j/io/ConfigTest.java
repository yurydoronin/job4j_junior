package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 20.08.2019
 */
public class ConfigTest {

    private Config config;

    @Before
    public void setUp() {
        this.config = new Config(ConfigTest.class.getClassLoader().getResourceAsStream("app.properties"));
        this.config.load();
    }

    /**
     * Test.
     */
    @Test
    public void whenKeyExistsThenReturnsValue() {
        assertThat(this.config.value("hibernate.connection.username"), is("macintosh"));
    }

    /**
     * Test.
     */
    @Test
    public void whenKeyNotExistsThenThrowException() {
        assertThat(this.config.value("key.does.not.exist"), is("unknown key"));
    }
}