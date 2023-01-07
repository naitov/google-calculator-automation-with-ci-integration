package org.example.framework.test;

import io.github.bonigarcia.wdm.WebDriverManager;
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
    public static final String TESTING_ENVIRONMENT = TestEnvironmentReader.getTestData("env.settings");
    public static final Logger logger = LogManager.getRootLogger();
    protected static WebDriver driver;
    protected double expectedSum = -1d;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("browser")) {
                case "firefox" -> driver = new FirefoxDriver();
                case "safari" -> driver = new SafariDriver();
                default -> driver = new ChromeDriver();
            }
        }
        return driver;
    }

    @BeforeClass()
    public void webDriverManagerSetup() {
        switch (System.getProperty("browser")) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "safari" -> WebDriverManager.safaridriver().setup();
            default -> WebDriverManager.chromedriver().setup();
        }
    }

    @BeforeMethod()
    public void browserSetup() {
        getDriver();
        setExpectedSum();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

    private void setExpectedSum() {
        switch (TESTING_ENVIRONMENT) {
            case "dev" -> expectedSum = 48.92;
            case "staging" -> expectedSum = 2275.48;
            case "qa" -> expectedSum = 113.44;
        }
    }
}
