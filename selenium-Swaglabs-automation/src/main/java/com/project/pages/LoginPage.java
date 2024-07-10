package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.project.utils.Waiter;
import org.openqa.selenium.WebElement;
import com.project.utils.CustomWebElement;
import com.project.utils.PropertiesUtil;

/**
 * Represents the Login page of the application.
 */
    public class LoginPage {
    private WebDriver driver;
    private Waiter waiter;

    /**
     * Constructor to initialize WebDriver and Waiter instance.
     *
     * @param driver The WebDriver instance to interact with the web browser.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
    }
    /**
     * Displays the text and value of the login logo.
     */
    public void displayLogoText()
    {
        CustomWebElement loginLogo = new CustomWebElement(driver,driver.findElement(By.cssSelector(PropertiesUtil.getProperty("login_logo"))));
        loginLogo.getValue();
        loginLogo.getText();
    }
    /**
     * Logs in app with the provided username and password, then displays the text and value of the login button.
     *
     * @param username The username to login with.
     * @param password The password to login with.
     */
    public void login_Display_ButtonText(String username,String password)
    {
        CustomWebElement loginButton = new CustomWebElement(driver,driver.findElement(By.id(PropertiesUtil.getProperty("login_button"))));
        CustomWebElement usernameField= new CustomWebElement(driver,driver.findElement(By.id(PropertiesUtil.getProperty("username_field"))));
        CustomWebElement passwordField = new CustomWebElement(driver,driver.findElement(By.id(PropertiesUtil.getProperty("password_field"))));

        waiter.waitForCondition("visible", usernameField, 10);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.getText();
        loginButton.getValue();
        loginButton.click();
    }
    /**
     * Checks if the error message is displayed.
     *
     * @return true if the error message is displayed, false otherwise.
     */
    public boolean isErrorMessageDisplayed() {
        WebElement errorMessage = driver.findElement(By.xpath(PropertiesUtil.getProperty("error_message")));

        return errorMessage.isDisplayed();
    }
}
