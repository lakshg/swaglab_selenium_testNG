package com.project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtil is a utility class that loads and provides access to properties
 * from configuration files (config.properties and locators.properties).
 */
public class PropertiesUtil {
    private static Properties properties;

    // Static block to load properties when the class is loaded
    static {
        properties = new Properties();
        try (InputStream configInput = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
             InputStream locatorInput = PropertiesUtil.class.getClassLoader().getResourceAsStream("locators.properties")) {

            // Load config.properties file
            if (configInput != null) {
                properties.load(configInput);
            } else {
                System.out.println("Sorry, unable to find config.properties");
            }
           // Load locators.properties file
            if (locatorInput != null) {
                properties.load(locatorInput);
            } else {
                System.out.println("Sorry, unable to find locators.properties");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves the property value associated with the specified key.
     *
     * @param key the key of the property
     * @return the property value as a String
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
