package com.project.tests;

import com.project.pages.InventoryPage;
import com.project.pages.LoginPage;
import com.project.utils.DriverManager;
import com.project.utils.ExcelUtils;
import com.project.utils.PropertiesUtil;
import com.project.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.ITestResult;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class contains test cases for the login functionality of the application.
 */
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private List<Map<String, String>> testData;
    private PropertiesUtil propertiesUtil;

    /**
     * Setup method to initialize the WebDriver and page objects, and load test data.
     */
    @BeforeClass
    public void setup() throws IOException {
        // Set up the WebDriver based on the browser specified in the properties file
        DriverManager.getInstance().setDriver(propertiesUtil.getProperty("browser"));
        driver = DriverManager.getInstance().getDriver();

        // Initialize page objects
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        // Load test data from the Excel file
        testData = ExcelUtils.getTestData("src\\main\\resources\\testData.xlsx", "LoginData");
    }

    /**
     * Data provider method to supply login test data from the Excel file.
     *
     * @return a two-dimensional array of test data
     * @throws IOException if there is an error reading the Excel file
     */
    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        Object[][] data = new Object[testData.size()][3];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i).get("username");
            data[i][1] = testData.get(i).get("password");
            data[i][2] = testData.get(i).get("expectedResult");
        }
        return data;
    }
    /**
     * Test method for verifying login functionality with different sets of test data.
     *
     * @param username       the username to be used for login
     * @param password       the password to be used for login
     * @param expectedResult the expected result of the login attempt ("success" or "failure")
     */

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {
        // Navigate to the login page
        driver.get(propertiesUtil.getProperty("url"));

        // Display logo text and perform login
        loginPage.displayLogoText();
        loginPage.login_Display_ButtonText(username,password);

        // Verify the outcome based on the expected result
        if (expectedResult.equals("success")) {
            Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page should be displayed");
            Assert.assertEquals(inventoryPage.getCookieValue("session-username"), username, "Session username should match");
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        }
    }

    /**
     * Method to take a screenshot on test failure.
     *
     * @param result the result of the test method
     */

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(DriverManager.getInstance().getDriver(), result.getName());
        }
    }

    /**
     * Teardown method to close the WebDriver after all tests have run.
     */
    @AfterClass
    public void teardown() {
        DriverManager.getInstance().closeDriver();
    }
}
