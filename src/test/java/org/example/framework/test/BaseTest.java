package org.example.framework.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.framework.utils.ScreenshotSaver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Listeners({ScreenshotSaver.class})
public class BaseTest {
    public static final String HOMEPAGE_URL = "https://cloud.google.com/";
    public static final String CALCULATOR_PAGE_URL = "https://cloud.google.com/products/calculator";
    public static final String SEARCH_TERM = "Google Cloud Pricing Calculator";
    public static final Logger logger = LogManager.getRootLogger();
    protected static WebDriver driver;

    //TODO in readme: problem desc, objectives desc, solution desc, tests estimated time

    @Step("New WebDriver object")
    public static WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("browser").toLowerCase()) {
                case "firefox" -> driver = new FirefoxDriver();
                case "safari" -> driver = new SafariDriver();
                case "chrome" -> driver = new ChromeDriver();
                default -> throw new IllegalArgumentException(String.format("This test suite do not support %s",
                        System.getProperty("browser")));
            }
        }
        return driver;
    }

    @BeforeClass()
    public void webDriverManagerSetup() {
        switch (System.getProperty("browser").toLowerCase()) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "safari" -> WebDriverManager.safaridriver().setup();
            case "chrome" -> WebDriverManager.chromedriver().setup();
            default -> throw new IllegalArgumentException(String.format("This test suite do not support %s",
                    System.getProperty("browser").toLowerCase()));
        }
    }

    @BeforeMethod()
    public void browserSetup() {
        getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
    }

    @Step("Quit and null driver")
    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
