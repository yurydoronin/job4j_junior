package ru.job4j.io.zip;

/**
 * Class MainZip, to launch the app.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 29.08.2019
 */
public class MainZip {

    public static void main(String... args) {
        Args arguments = new Args(args);
        new Zip().pack(arguments.directory(), arguments.exclude(), arguments.output());
    }
}