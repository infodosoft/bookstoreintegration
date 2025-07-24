package book.store.api.v1.config;

import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApiConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
    public static String getEnv() {
        return get("env");
    }
}
