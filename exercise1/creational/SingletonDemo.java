package creational;

import java.util.Properties;

// Singleton Pattern - Configuration Manager
class ConfigurationManager {
    private static ConfigurationManager instance;
    private Properties config;
    
    private ConfigurationManager() {
        config = new Properties();
        config.setProperty("database.url", "localhost:3306/mydb");
        config.setProperty("app.version", "1.0.0");
        config.setProperty("api.key", "secret-key-123");
        System.out.println("ConfigurationManager instance created");
    }
    
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }
    
    public String getConfig(String key) {
        return config.getProperty(key);
    }
    
    public void setConfig(String key, String value) {
        config.setProperty(key, value);
    }
}

public class SingletonDemo {
    public static void demonstrate() {
        System.out.println("\n=== SINGLETON PATTERN DEMO ===");
        
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        
        System.out.println("Database URL: " + config1.getConfig("database.url"));
        System.out.println("App Version: " + config1.getConfig("app.version"));
        System.out.println("Same instance? " + (config1 == config2));
        
        // Demonstrate it's the same instance
        config1.setConfig("theme", "dark");
        System.out.println("Theme from config2: " + config2.getConfig("theme"));
    }
}