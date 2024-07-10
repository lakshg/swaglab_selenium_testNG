
# Selenium Swaglabs Automation

This project automates the login functionality of the Swaglabs application using Selenium WebDriver, TestNG, and Java. The project uses a Page Object Model (POM) design pattern, and test data is read from an Excel file. The project also includes utility classes for managing WebDriver instances, reading properties, taking screenshots, and waiting for conditions.

## Project Structure
│
├── src
│ ├── main
│ │ ├── java
│ │ │ ├── com
│ │ │ │ ├── project
│ │ │ │ │ ├── pages
│ │ │ │ │ │ ├── InventoryPage.java
│ │ │ │ │ │ ├── LoginPage.java
│ │ │ │ │ ├── utils
│ │ │ │ │ │ ├── CustomWebElement.java
│ │ │ │ │ │ ├── DriverManager.java
│ │ │ │ │ │ ├── ExcelUtils.java
│ │ │ │ │ │ ├── PropertiesUtil.java
│ │ │ │ │ │ ├── ScreenshotUtil.java
│ │ │ │ │ │ ├── Waiter.java
│ │ ├── resources
│ │ │ ├── config.properties
│ │ │ ├── locators.properties
│ │ │ ├── testData.xlsx
│ ├── test
│ │ ├── java
│ │ │ ├── com
│ │ │ │ ├── project
│ │ │ │ │ ├── tests
│ │ │ │ │ │ ├── LoginTest.java
│
├── .gitignore
├── pom.xml
├── README.md


## Project Structure

- **src/main/java/com/project/pages:**
  Contains the Page Object Model (POM) classes for different pages of the application.

- **src/main/java/com/project/utils:**
  Contains utility classes for driver management, property file handling, Excel reading, waiting mechanisms, and screenshot capturing.

- **src/test/java/com/project/tests:**
  Contains the test classes.

- **src/main/resources:**
  Contains configuration and locator property files and the test data Excel file.

## Configuration

- **src/main/resources/config.properties:**
  Contains configuration properties like browser type and URL.

- **src/main/resources/locators.properties:**
  Contains the locators for the web elements.

- **src/main/resources/testData.xlsx:**
  Contains the test data used in the tests.

## Custom WebElement Implementation

This project includes a custom WebElement implementation (`CustomWebElement`) to add custom functionality to standard Selenium WebElement actions.

## Screenshots

Screenshots of failed tests are saved in the `screenshots` directory

### Prerequisites

- Java JDK (version 8 or above)
- Maven
- IntelliJ IDEA

### Setup

mvn clean install
mvn test

