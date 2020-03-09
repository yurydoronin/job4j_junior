package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Class Config2.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 25.08.2019
 */
public class Config2 {

    /**
     * A file's path.
     */
    private String path;

    /**
     * A container with values indexed by keys.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * A constructor.
     *
     * @param path, a file's path.
     */
    public Config2(String path) {
        try {
            this.path = Objects.requireNonNull(Config2.class.getClassLoader().getResource(path)).toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads all the keys into the map.
     * The file may contain empty lines and comments which must be skipped.
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().filter(string -> !string.isEmpty() && string.charAt(0) != '#')
                    .forEach(
                            s -> {
                                String[] values = s.split("=");
                                this.values.put(values[0], values[1]);
                            }
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the key's value.
     *
     * @param key, a key.
     * @return the key's value.
     */
    public String value(String key) {
        return this.values.computeIfAbsent(key, s -> "unknown key");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
