package ru.job4j.io.nio;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Class FindFile.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 06.09.2019
 */
public class FindFile {

    /**
     * A string separator.
     */
    private final String separator = System.lineSeparator();

    /**
     * A container with target files.
     */
    private List<File> target = new ArrayList<>();

    /**
     * A list of paths of the found files.
     */
    private List<String> filesPaths = new ArrayList<>();

    /**
     * Constants of a search method keys.
     */
    private static final String MASK = "-m";
    private static final String FULL = "-f";
    private static final String REGEX = "-r";

    /**
     * A search of files.
     *
     * @param directory, the directory which the search should be started out in.
     * @param fileName,  a file name that the files will be searched with.
     * @param key,       the search by mask, full name, regEx.
     * @param log,       a file with paths of the found files.
     * @return list of paths of the found files.
     */
    /* Java 8 */
    public List<File> searchFiles(File directory, String fileName, String key, File log) {
        try (Stream<Path> paths = Files.walk(directory.toPath())) {
            paths.filter(Files::isRegularFile).forEach(
                    f -> this.searchValidation(f.toFile(), fileName, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.writeToLog(this.filesPaths, log);
        return this.target;
    }

    /**
     * Searching files by specific way of searching depending on the key.
     *
     * @param file,     a file.
     * @param fileName, a file name that the files will be searched with.
     * @param key,      the search by mask, full name, regEx.
     */
    public void searchValidation(File file, String fileName, String key) {
        Pattern pattern = Pattern.compile(".+\\.(txt|docx|pdf)");
        Matcher matcher = pattern.matcher(fileName);
        switch (key) {
            case MASK:
                if (file.getName().toLowerCase().startsWith(fileName.toLowerCase())
                        || file.getName().toLowerCase().endsWith(
                                fileName.toLowerCase().replaceAll("\\*", ""))) {
                    this.target.add(file);
                    this.filesPaths.add(String.format("%s%s", file.getParent(), this.separator));
                }
                break;
            case FULL:
                if (file.getName().toLowerCase().equals(fileName.toLowerCase())) {
                    this.target.add(file);
                    this.filesPaths.add(String.format("%s%s", file.getParent(), this.separator));
                }
                break;
            case REGEX:
                if (matcher.matches()) {
                    this.target.add(file);
                    this.filesPaths.add(String.format("%s%s", file.getParent(), this.separator));
                }
                break;
            default:
                /* Do nothing */
        }
    }

    /**
     * Writes all the paths of the found files in the log file.
     *
     * @param filesPaths, a list of paths of the found files.
     * @param log,        a file with paths of the found files.
     */
    public void writeToLog(List<String> filesPaths, File log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(log))) {
            for (String line : filesPaths) {
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
