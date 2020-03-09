package ru.job4j.io.nio;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 07.09.2019
 */
public class FindFileTest {

    private FindFile ff;
    private File directory;
    private File log;
    private String property = System.getProperty("user.dir");
    private String sep = File.separator;

    @Before
    public void setUp() {
        this.ff = new FindFile();
        this.directory = Paths.get(this.property, "src", "main", "java", "io", "tmpdir", "test").toFile();
        try {
            this.log = Paths.get(Objects.requireNonNull(
                    FindFileTest.class.getClassLoader().getResource("log.txt")).toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String getPath() {
        return String.join(this.sep, this.property, "src", "main", "java", "io", "tmpdir", "test");
    }

    private String getPath(String fileName) {
        return String.join(this.sep, this.property, "src", "main", "java", "io", "tmpdir", "test", fileName);
    }

    private String getLogPath(String fileName) {
        return String.join(this.sep, this.property, "target", "classes", fileName);
    }

    /**
     * Test searchFiles.
     */
    @Test
    public void whenFoundFilesThenGetFiles() {
        List<File> result = ff.searchFiles(this.directory, "*.txt", "-m", this.log);
        File expected = Paths.get(this.getPath("статьи — копия.txt")).toFile();
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(expected));
    }

    /**
     * Test writeToLog.
     */
    @Test
    public void whenFoundFilesThenWriteToLog() {
        ff.searchFiles(this.directory, "*.txt", "-m", this.log);
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.getLogPath("log.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(this.getPath()));
    }
}