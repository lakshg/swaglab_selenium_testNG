package com.project.pages;

import com.project.utils.PropertiesUtil;
import com.project.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents the Inventory page of the application.
 */
    public class InventoryPage {
    private WebDriver driver;
    private Waiter waiter;

    /**
     * Constructor to initialize WebDriver and Waiter instance.
     *
     * @param driver The WebDriver instance to interact with the web browser.
     */
    public InventoryPage(WebDriver driver) {

        this.driver = driver;
        this.waiter = new Waiter(driver);
    }

    /**
     * Checks if the Inventory page is displayed.
     *
     * @return true if the Inventory page is displayed, false otherwise.
     */
    public boolean isInventoryPageDisplayed() {
        WebElement inventoryPage = driver.findElement(By.cssSelector(PropertiesUtil.getProperty("inventory_page")));

        waiter.waitForCondition("visible", inventoryPage, 10);
        return inventoryPage.isDisplayed();
    }

    /**
     * Retrieves the value of a specified cookie.
     *
     * @param cookieName The name of the cookie.
     * @return The value of the specified cookie.
     */
    public  String getCookieValue(String cookie_name)
    {
        return driver.manage().getCookieNamed(cookie_name).getValue();
    }
}
