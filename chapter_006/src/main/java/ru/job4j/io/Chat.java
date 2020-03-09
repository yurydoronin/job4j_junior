package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Class Chat.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 2.0
 * @since 02.09.2019
 */
public class Chat {

    /**
     * A container with all the chatting data.
     */
    private List<String> log = new ArrayList<>();

    /**
     * A container with splitted lines from the file.
     */
    private Map<Integer, String> map = new HashMap<>();

    /**
     * Constants.
     */
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String FINISH = "закончить";

    /**
     * Method reads a bot's answers from a source file and writes all the chat to the target file.
     *
     * @param source, a source file.
     * @param target, a target file.
     */
    public void chatting(String source, String target) {
        String user;
        String botAnswer;
        boolean flag = true;
        this.readFile(source);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                user = reader.readLine();
                this.log.add(Objects.requireNonNull(user));
                if (!user.toLowerCase().equals(FINISH)) {
                    if (!user.toLowerCase().equals(STOP)) {
                        botAnswer = this.bot(this.map);
                        System.out.println(botAnswer);
                        this.log.add(Objects.requireNonNull(botAnswer));
                    } else {
                        while (flag) {
                            user = reader.readLine();
                            if (!user.toLowerCase().equals(CONTINUE)) {
                                this.log.add(Objects.requireNonNull(user));
                            } else {
                                this.log.add(Objects.requireNonNull(user));
                                botAnswer = this.bot(this.map);
                                System.out.println(botAnswer);
                                this.log.add(Objects.requireNonNull(botAnswer));
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            } while (!user.toLowerCase().equals(FINISH));
            this.log.add(String.join("/", STOP, CONTINUE, FINISH));
            this.writeAll(Objects.requireNonNull(log), target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a file.
     *
     * @param source, a source file.
     */
    private void readFile(String source) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(
                Objects.requireNonNull(Chat.class.getClassLoader().getResource(source)).toURI().getPath()))) {
            reader.lines().forEach(builder::append);
            String[] values = builder.toString().split("[,]");
            for (int index = 0; index < values.length; index++) {
                this.map.put(index, values[index]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a random line from the map.
     *
     * @param map, a container with the splitted lines from the file.
     * @return a random line.
     */
    private String bot(Map<Integer, String> map) {
        return !(map.isEmpty()) ? map.get(new Random().nextInt(map.size())) : "";
    }

    /**
     * Writes all the chatting data to the target file.
     *
     * @param targetFile, the target file.
     */
    private void writeAll(List<String> list, String targetFile) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                Objects.requireNonNull(Chat.class.getClassLoader().getResource(targetFile))
                        .toURI().getPath()), StandardCharsets.UTF_8))) {
            for (String line : list) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the chat.
     *
     * @param args, args.
     */
    public static void main(String[] args) {
        new Chat().chatting("phrases.txt", "log.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(Objects.requireNonNull(
                Chat.class.getClassLoader().getResource("log.txt")).toURI().getPath()))) {
            reader.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
