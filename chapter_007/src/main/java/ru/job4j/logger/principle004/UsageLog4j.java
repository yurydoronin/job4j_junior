package ru.job4j.logger.principle004;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class);

    public static void main(String[] args) {
        String name1 = "Diana Volkova";
        int age1 = 33;
        String name2 = "Pavel Smith";
        int age2 = 25;
        String name3 = "Olga Stevens";
        int age3 = 47;
        String name4 = "Misha Ivanov";
        int age4 = 33;
        String name5 = "Yu Jin Lee";
        int age5 = 13;
        String name6 = "Bruce Lee";
        int age6 = 20;
        String name7 = "Brice Jackson";
        int age7 = 18;
        String name8 = "Katya Petrova";
        int age8 = 56;
        LOG.debug("User info name : {}, age : {}", name1, age1);
        LOG.debug("User info name : {}, age : {}", name2, age2);
        LOG.debug("User info name : {}, age : {}", name3, age3);
        LOG.debug("User info name : {}, age : {}", name4, age4);
        LOG.debug("User info name : {}, age : {}", name5, age5);
        LOG.debug("User info name : {}, age : {}", name6, age6);
        LOG.debug("User info name : {}, age : {}", name7, age7);
        LOG.debug("User info name : {}, age : {}", name8, age8);
    }
}
