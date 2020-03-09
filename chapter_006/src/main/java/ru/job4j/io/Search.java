package ru.job4j.io;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

/**
 * Class Search.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 3.0
 * @since 27.08.2019
 */
public class Search {

    /**
     * A container with target files with necessary extensions.
     */
    private List<File> target = new ArrayList<>();

    /**
     * A path to the directory from which the search should be started out.
     */
    private Path parent;

    /**
     * A constructor.
     *
     * @param parent, a path to the directory from which the search should be started out.
     */
    public Search(Path parent) {
        this.parent = parent;
    }

    /**
     * Finds the files with a set of the necessary extensions.
     *
     * @param exts, the file extensions that necessary to receive.
     * @return a list with target files.
     */
    public List<File> files(List<String> exts) {
        Queue<File> data = new LinkedList<>(
                Arrays.asList(Objects.requireNonNull(this.parent.toFile().listFiles())));
        while (!data.isEmpty()) {
            File file = data.poll();
            if (file.isDirectory()) {
                data.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
            } else {
                for (String ext : exts) {
                    if (file.getName().endsWith(ext)) {
                        this.target.add(file);
                    }
                }
            }
        }
        return this.target;
    }
}
