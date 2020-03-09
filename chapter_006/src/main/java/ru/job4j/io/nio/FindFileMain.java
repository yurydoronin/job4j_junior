package ru.job4j.io.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

/**
 * Class FindFileMain.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 10.09.2019
 */
public class FindFileMain {

    /**
     * Launches the app.
     *
     * @param args, args.
     */
    public static void main(String... args) {
        Args arguments = new Args(args);
        List<File> foundFiles = new FindFile().searchFiles(
                arguments.directory(), arguments.getFileName(), arguments.getKey(), arguments.output());
        foundFiles.forEach(f -> System.out.println(f.getName()));
        try (BufferedReader reader = new BufferedReader(new FileReader(Objects.requireNonNull(
                FindFileMain.class.getClassLoader().getResource("log.txt")).toURI().getPath()))) {
            reader.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}