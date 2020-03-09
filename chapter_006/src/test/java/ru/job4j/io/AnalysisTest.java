package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 25.08.2019
 */
public class AnalysisTest {

    private String line1 = "";
    private String line2 = "";

    @Before
    public void setUp() {
        boolean flag = true;
        new Analysis().unavailable("server.stat", "unavailable.csv");
        try (BufferedReader reader = new BufferedReader(
                new FileReader(Objects.requireNonNull(
                        Analysis.class.getClassLoader().getResource("unavailable.csv")).getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!flag) {
                    this.line2 = line;
                }
                if (this.line1.isEmpty()) {
                    this.line1 = line;
                    flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test unavailable.
     */
    @Test
    public void whenReadFileThenWriteToFile() {
        assertThat(this.line1, is("10:57:01;10:59:01"));
        assertThat(this.line2, is("11:01:02;11:02:02"));
    }
}