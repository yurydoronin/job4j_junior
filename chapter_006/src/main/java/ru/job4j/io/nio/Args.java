package ru.job4j.io.nio;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class Args.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 06.09.2019
 */
public class Args {

    /**
     * The directory which the search should be started out in.
     */
    private Path directory;

    /**
     * A file name that the files will be searched with.
     */
    private String fileName;

    /**
     * -m - search by mask,
     * -f - a full match of the name.
     * -r - regEx.
     */
    private String key;

    /**
     * A file with paths of the found files.
     */
    private Path output;

    /**
     * Constants of keys.
     */
    private static final String DIR = "-d";
    private static final String NAME = "-n";
    private static final String KEY1 = "-m";
    private static final String KEY2 = "-f";
    private static final String KEY3 = "-r";
    private static final String LOG = "-o";

    /**
     * A constructor.
     *
     * @param args, a directory to start out, a file name, a search method keys
     * and a target file for writing paths of the found files.
     */
    public Args(String... args) {
        for (int index = 0; index < args.length; index++) {
            switch (args[index]) {
                case DIR:
                    this.directory = Paths.get(args[++index]);
                    break;
                case NAME:
                    this.fileName = args[++index];
                    break;
                case KEY1:
                    this.key = args[index];
                    break;
                case KEY2:
                    this.key = args[index];
                    break;
                case KEY3:
                    this.key = args[index];
                    break;
                case LOG:
                    try {
                        String fileName = args[++index];
                        this.output = Paths.get(Objects.requireNonNull(
                                Args.class.getClassLoader().getResource(fileName)).toURI());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    /* Do nothing */
            }
        }
    }

    /**
     * @return .
     */
    public File directory() {
        return this.directory.toFile();
    }

    /**
     * @return .
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * @return .
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return .
     */
    public File output() {
        return this.output.toFile();
    }
}
