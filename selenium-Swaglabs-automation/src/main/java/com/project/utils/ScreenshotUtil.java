package com.project.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ScreenshotUtil is a utility class that provides functionality to capture and save screenshots.
 */
public class ScreenshotUtil {
    /**
     * Takes a screenshot of the current browser window and saves it to the specified directory.
     *
     * @param driver   the WebDriver instance
     * @param testName the name of the test (used for naming the screenshot file)
     */
    public static void takeScreenshot(WebDriver driver, String testName) {
        // Capture the screenshot and store it as a file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        try {
            // Create the screenshots directory if it doesn't exist
            Files.createDirectories(Paths.get("screenshots"));
            // Copy the screenshot file to the screenshots directory with a unique filename
            Files.copy(srcFile.toPath(), Paths.get("screenshots/" + testName + "_" + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
