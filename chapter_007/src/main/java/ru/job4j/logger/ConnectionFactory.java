package ru.job4j.logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.logger.principle004.UsageLog4j2;

public class ConnectionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j2.class);

    private final BasicDataSource pool = new BasicDataSource();

    public static ConnectionFactory getInstance() {
        return Singleton.INSTANCE;
    }

    private static final class Singleton {
        private static final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private ConnectionFactory() {
        try (InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            pool.setUrl(config.getProperty("url"));
            pool.setUsername(config.getProperty("username"));
            pool.setPassword(config.getProperty("password"));
        } catch (IOException e) {
            LOG.info("info message", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().pool.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        ConnectionFactory.getInstance().getConnection();
    }
}