package it.nfdev.ggk.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    private static final String RESOURCE_FILE_NAME = "application.properties";
    
    private static Application instance;

    private Properties properties;

    private Application() {
        properties = new Properties();
        try {
            InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(RESOURCE_FILE_NAME);
            properties.load(resourceStream);
        } catch (IOException e) {
            // Error on loading application resources
            throw new RuntimeException("Error on loading application resources", e);
        }
    }

    public static Application instance() {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    instance = new Application();
                }
            }
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

}
