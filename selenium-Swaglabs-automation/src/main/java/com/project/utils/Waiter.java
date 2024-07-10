package com.project.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Waiter is a utility class that provides methods for waiting for specific conditions to be met.
 */
public class Waiter {
    private WebDriver driver;
    /**
     * Constructor to initialize the Waiter with a WebDriver instance.
     *
     * @param driver the WebDriver instance
     */
    public Waiter(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Waits for a specific condition on a WebElement for a given amount of time.
     *
     * @param conditionType     the type of condition to wait for (clickable, visible, invisible)
     * @param element           the WebElement to wait for
     * @param timeoutInSeconds  the maximum time to wait in seconds
     */
    public void waitForCondition(String conditionType, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        switch (conditionType.toLowerCase()) {
            case "clickable":
                wait.until(ExpectedConditions.elementToBeClickable(element));
                break;
            case "visible":
                wait.until(ExpectedConditions.visibilityOf(element));
                break;
            case "invisible":
                wait.until(ExpectedConditions.invisibilityOf(element));
                break;
            default:
                throw new IllegalArgumentException("Invalid condition type: " + conditionType);
        }
    }


    /**
     * Waits for a custom ExpectedCondition for a given amount of time.
     *
     * @param condition         the ExpectedCondition to wait for
     * @param timeoutInSeconds  the maximum time to wait in seconds
     */
    public void waitForCondition(ExpectedCondition<Boolean> condition, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(condition);
    }
}
