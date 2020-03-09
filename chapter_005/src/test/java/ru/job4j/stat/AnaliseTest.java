package ru.job4j.stat;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 15.08.2019
 */
public class AnaliseTest {

    private Analise analise;
    private List<Analise.User> previous;
    private List<Analise.User> current;

    @Before
    public void beforeTest() {
        this.analise = new Analise();
        this.previous = new ArrayList<>();
        this.previous.add(new Analise.User(1, "Masha"));
        this.previous.add(new Analise.User(2, "Dasha"));
        this.previous.add(new Analise.User(3, "Sasha"));
        this.current = new ArrayList<>();
        this.current.add(new Analise.User(1, "Julia"));
        this.current.add(new Analise.User(3, "Sasha"));
        this.current.add(new Analise.User(4, "Mike"));
    }
    /**
     * Test.
     */
    @Test
    public void whenThen() {
        Analise.Info info = this.analise.diff(this.previous, this.current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }
}