package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 3.0
 * @since 27.08.2019
 */
public class SearchTest {

    private Search search;
    private String property = System.getProperty("user.dir");
    private String sep = File.separator;

    @Before
    public void setUp() {
        Path parent = Paths.get(this.property, "src", "main", "java", "io", "tmpdir");
        this.search = new Search(parent);
    }

    private String getPath(String fileName) {
        return String.join(this.sep, this.property, "src", "main", "java", "io", "tmpdir", fileName);
    }

    private String getPath(String... dirsName) {
        return this.getPath(Arrays.stream(dirsName).collect(Collectors.joining(this.sep)));
    }

    /**
     * Test search.
     */
    @Test
    public void whenFilesFoundThenTrue() {
        List<File> result = this.search.files(List.of("csv", "txt", "html", "css"));
        assertThat(result.size(), is(11));
        assertThat(result.contains(new File(this.getPath("dir2", "file.html"))), is(true));
        assertThat(result.contains(new File(this.getPath("dir1", "dir3", "file.css"))), is(true));
        assertThat(result.contains(new File(this.getPath("dir1", "dir3", "test.txt"))), is(true));
        assertThat(result.contains(new File(this.getPath("dir1", "file4.csv"))), is(true));
    }

    /**
     * Test search.
     */
    @Test
    public void whenFileNotFoundThenTrue() {
        List<File> result = this.search.files(List.of("dll"));
        assertThat(result.isEmpty(), is(true));
    }
}