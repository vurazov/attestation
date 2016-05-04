package com.issart.context;

import com.issart.datasource.entity.RsUser;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.h2.mvstore.ConcurrentArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(ApplicationContext.class);
    private JdbcPooledConnectionSource jdbcConnectionSource;
    private volatile PropertiesConfiguration configuration;
    private volatile ConcurrentHashMap<UUID, RsUser> sessions = new ConcurrentHashMap<UUID, RsUser>();

    @Override
    public void close() throws Exception {
        if(jdbcConnectionSource != null) {
            jdbcConnectionSource.close();
        }
    }

    public enum ConfigurationSettings {
        JDBC_PASSWORD,
        JDBC_URL,
        JDBC_NAME;

        @Override
        public String toString() {
            return name().toLowerCase().replace('_', '.');
        }
    }

    private ApplicationContext() {
        try {
            configuration = new PropertiesConfiguration("default.properties");
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    private static class ApplicationContextHolder {
        private static final ApplicationContext INSTANCE = new ApplicationContext();
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextHolder.INSTANCE;
    }

    public JdbcPooledConnectionSource getConnectionSource() throws ConfigurationException {
        if(jdbcConnectionSource == null) {
            try {
                this.jdbcConnectionSource = new JdbcPooledConnectionSource(
                        configuration.getString(ConfigurationSettings.JDBC_URL.toString()),
                        configuration.getString(ConfigurationSettings.JDBC_NAME.toString()),
                        configuration.getString(ConfigurationSettings.JDBC_PASSWORD.toString()));
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        return jdbcConnectionSource;
    }

    public void removeSession(UUID uuid) {
        sessions.remove(uuid);
    }

    public void putSession(UUID uuid, RsUser user) {
        sessions.put(uuid, user);
    }

    public boolean hasSession(UUID uuid) {
        return sessions.containsKey(uuid);
    }
}
