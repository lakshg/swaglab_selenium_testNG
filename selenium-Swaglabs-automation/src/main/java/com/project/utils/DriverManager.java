package com.project.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.time.Duration;

/**
 * DriverManager is a singleton class responsible for managing WebDriver instances.
 * It supports multiple browsers and ensures thread safety with ThreadLocal.
 */

public class DriverManager {
    // Singleton instance of DriverManager
    private static DriverManager instance = new DriverManager();
    // ThreadLocal to manage WebDriver instances per thread
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverManager() {}

    /**
     * Returns the singleton instance of DriverManager.
     *
     * @return the instance of DriverManager
     */
    public static DriverManager getInstance() {
        return instance;
    }

    /**
     * Sets up the WebDriver instance based on the specified browser.
     *
     * @param browser the name of the browser to be used (chrome, firefox, edge)
     */
    public void setDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver.set(new FirefoxDriver(options));
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver.set(new EdgeDriver(options));
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get().manage().window().maximize();
    }

    /**
     * Retrieves the current WebDriver instance for the current thread.
     *
     * @return the WebDriver instance
     */
    public WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Closes the WebDriver instance for the current thread and removes it from ThreadLocal.
     */
    public void closeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
