package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import exception.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppConfig {
    private static final Logger logger = LogManager.getLogger(AppConfig.class);
    private static final AppConfig INSTANCE = new AppConfig();
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    private AppConfig() {
        Properties properties = new Properties();
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new ConfigurationException("Konfigurationsdatei db.properties nicht gefunden");
            }
            properties.load(input);
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
            if (dbUrl == null || dbUser == null || dbPassword == null) {
                throw new ConfigurationException("Erforderliche Datenbankkonfigurationen fehlen in db.properties");
            }
        } catch (IOException e) {
            logger.error("Fehler beim Laden der Konfigurationsdatei: {}", e.getMessage());
            throw new ConfigurationException("Fehler beim Laden der Konfigurationsdatei db.properties", e);
        }
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}