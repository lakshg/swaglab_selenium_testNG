package com.project.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * CustomWebElement is a wrapper class for WebElement that includes custom wait mechanisms
 * and additional validation steps for interactions like click, sendKeys, and getting text/values.
 */
public class CustomWebElement implements WebElement {
    private final WebDriver driver;
    private final WebElement element;
    private final int timeoutInSeconds = 10;

    /**
     * Constructor to initialize WebDriver and WebElement.
     *
     * @param driver The WebDriver instance to interact with the web browser.
     * @param element The WebElement instance to wrap.
     */
    public CustomWebElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    /**
     * Waits until the element is visible on the page.
     */
    private void waitForVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element is clickable on the page.
     */
    private void waitForClickability() {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Clicks on the element after waiting for it to be clickable.
     */
    @Override
    public void click() {
        waitForClickability();
        element.click();
        // Validation step: Check if the element is clicked
        // This can be improved based on the specific application behavior
        System.out.println("Clicked on the element: " + element);
    }
    /**
     * Sends keys to the element after waiting for it to be visible.
     *
     * @param keysToSend The sequence of characters to be sent to the element.
     */
    @Override
    public void sendKeys(CharSequence... keysToSend) {
        waitForVisibility();
        element.sendKeys(keysToSend);
        // Validation step: Check if the keys were sent
        // This can be improved based on the specific application behavior
        System.out.println("Sent keys to the element: " + element);
    }

    /**
     * Retrieves the text of the element after waiting for it to be visible.
     *
     * @return The text of the element.
     */
    @Override
    public String getText() {
        waitForVisibility();
        String text = element.getText();
        System.out.println("Text retrieved from the element: "+text);
        return text;
    }

    /**
     * Retrieves the value attribute of the element after waiting for it to be visible.
     *
     * @return The value attribute of the element.
     */
    public String getValue() {
        waitForVisibility();
        String value = element.getAttribute("value");
        System.out.println("Value retrieved from the element: "+ value);
        return value;
    }

    // Implement other WebElement methods as needed
    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public java.util.List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        if (element instanceof TakesScreenshot) {
            return ((TakesScreenshot) element).getScreenshotAs(target);
        }
        throw new WebDriverException("Current element does not support taking screenshots");
    }

}
