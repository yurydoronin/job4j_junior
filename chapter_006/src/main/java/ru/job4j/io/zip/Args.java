package ru.job4j.io.zip;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class Args.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 29.08.2019
 */
public class Args {

    /**
     * The directory which should be zipped.
     */
    private Path directory;

    /**
     * A file extension that the files with will be excluded.
     */
    private String extension;

    /**
     * A specific file for zipping.
     */
    private Path output;

    /**
     * A constructor.
     *
     * @param args, a directory to start out, an extension and a file for zipping.
     */
    public Args(String... args) {
        this.directory = Paths.get(args[1]);
        this.extension = args[3];
        this.output = Paths.get(args[5]);
    }

    /**
     *
     * @return .
     */
    public File directory() {
        return this.directory.toFile();
    }

    /**
     *
     * @return .
     */
    public String exclude() {
        return this.extension;
    }

    /**
     *
     * @return .
     */
    public File output() {
        return this.output.toFile();
    }
}
