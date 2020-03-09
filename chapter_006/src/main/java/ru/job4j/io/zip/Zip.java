package ru.job4j.io.zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class Zip.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 29.08.2019
 */
public class Zip {

    /**
     * A container with target files.
     */
    private List<File> sources = new ArrayList<>();

    /**
     * Finds all the files except files with the specific extension.
     *
     * @param directory, the directory which should be zipped.
     * @param ext,       a file extension that the files with will be excluded.
     * @return a list with target files.
     */
    public List<File> seekBy(File directory, String ext) {
        try (Stream<Path> paths = Files.walk(directory.toPath())) {
            paths.filter(Files::isRegularFile).filter(p -> !p.toFile().getName().endsWith(ext))
                    .forEach(p -> this.sources.add(p.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.sources;
    }

    /**
     * Zipping the found files.
     *
     * @param directory, the directory which should be zipped.
     * @param ext,       a file extension that the files with will be excluded.
     * @param output,    an archive.
     */
    public void pack(File directory, String ext, File output) {
        List<File> result = this.seekBy(directory, ext);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(output)))) {
            for (File file : result) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}