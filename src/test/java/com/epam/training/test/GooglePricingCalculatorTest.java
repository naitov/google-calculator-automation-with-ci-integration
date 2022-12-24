package com.epam.training.test;

import com.epam.training.page.GooglePricingCalculatorFormPage;
import com.epam.training.page.YopmailHomePage;
import com.epam.training.page.GoogleCloudHomePage;
import com.epam.training.page.GooglePricingCalculatorEstimatePage;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GooglePricingCalculatorTest {
    public static final String HOMEPAGE_URL = "https://cloud.google.com/";
    public static final String CALCULATOR_PAGE_URL = "https://cloud.google.com/products/calculator";
    public static final String SEARCH_TERM = "Google Cloud Pricing Calculator";
    private WebDriver driver;

    @Test(description = "Actual and expected sums should be equal")
    public void actualAndExpectedSumsShouldBeEqual() throws ParseException {
        double expectedSum = 2275.48;
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat("Expecting url should be https://cloud.google.com/products/calculator", driver.getCurrentUrl(), is(CALCULATOR_PAGE_URL));
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage
                .setupFormPage()
                .initializeCalculatorForm(FormPresets.PRESET_FULL)
                .fillAllNecessaryFields()
                .addToEstimate();
        double actualSum = estimatePage.getActualSumFromField();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
    }

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

    @Test(description = "Resulting fields should contain expected parameters")
    public void resultingFieldsShouldContainExpectedParameters() {
        List<String> expectedParameters = Arrays.asList(
                "Provisioning model: Regular",
                "Instance type: n1-standard-8",
                "Local SSD: 2x375 GiB",
                "Region: Frankfurt",
                "Commitment term: 1 Year");
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat("Expecting url should be https://cloud.google.com/products/calculator", driver.getCurrentUrl(), is(CALCULATOR_PAGE_URL));
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage
                .setupFormPage()
                .initializeCalculatorForm(FormPresets.PRESET_FULL)
                .fillAllNecessaryFields()
                .addToEstimate();
        List<String> actualParameters = estimatePage.getActualTextFromField();
        assertThat("Resulting fields should contain expected parameters", actualParameters,
                hasItems(expectedParameters.toArray(new String[0])));
    }

    @Test(description = "The Sum in email should be the same as the estimate sum")
    public void ActualAndExpectedSumShouldBeEqual() throws ParseException, InterruptedException {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat("Expecting url should be https://cloud.google.com/products/calculator", driver.getCurrentUrl(), is(CALCULATOR_PAGE_URL));
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage
                .setupFormPage()
                .initializeCalculatorForm(FormPresets.PRESET_LIGHT)
                .fillAllNecessaryFields()
                .addToEstimate();
        YopmailHomePage yopmailHomePage = estimatePage.createYopmailPage();
        yopmailHomePage.openEmailPageInNewTab()
                .createNewMailBoxWithRandomName()
                .switchToEstimatePage();
        double expectedSum = estimatePage.getEstimateSum();
        estimatePage.sendEmailFromPage()
                .switchToYopmail();
        double actualSum = yopmailHomePage.waitForMail().getActualSum();
        assertThat("The Sum in email should be the same as the estimate sum", actualSum, IsEqual.equalTo(expectedSum));
    }

    public enum FormPresets {
        PRESET_LIGHT,
        PRESET_FULL,
        PRESET_WITHOUT_GPU
    }
}
