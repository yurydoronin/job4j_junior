package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Class Analysis.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 25.08.2019
 */
public class Analysis {

    /**
     * A container with lines when server was not active or reachable.
     */
    private List<String> targetValues = new ArrayList<>();

    /**
     * Defines the time when server was not active or reachable.
     *
     * @param source, the source file.
     * @param target, the target file.
     */
    public void unavailable(String source, String target) {
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(
                Objects.requireNonNull(Analysis.class.getClassLoader().getResource(source)).getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (flag && (line.startsWith("400") || line.startsWith("500"))) {
                    builder.append(line.substring(4));
                    builder.append(";");
                    flag = false;
                }
                if (!flag && (line.startsWith("200") || line.startsWith("300"))) {
                    builder.append(line.substring(4));
                    builder.append(System.lineSeparator());
                    this.targetValues.add(builder.toString());
                    builder.setLength(0);
                    flag = true;
                }
            }
            this.writeAll(this.targetValues, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes all the lines, when server was not been active or reachable, to the target file.
     *
     * @param target, the target file.
     */
    private void writeAll(List<String> list, String target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                Objects.requireNonNull(Analysis.class.getClassLoader().getResource(target)).toURI().getPath()))) {
            for (String line : list) {
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
