package com.epam.training.test;

import com.epam.training.page.GoogleCloudHomePage;
import com.epam.training.page.GooglePricingCalculatorEstimatePage;
import com.epam.training.page.GooglePricingCalculatorFormPage;
import com.epam.training.page.YopmailHomePage;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GooglePricingCalculatorTest extends TestConditions {


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
                .initializeCalculatorForm(CURRENT_DATA_PRESET)
                .fillAllNecessaryFields(CURRENT_DATA_PRESET)
                .addToEstimate();
        double actualSum = estimatePage.getActualSumFromField();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
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
                .initializeCalculatorForm(CURRENT_DATA_PRESET)
                .fillAllNecessaryFields(CURRENT_DATA_PRESET)
                .addToEstimate();
        List<String> actualParameters = estimatePage.getActualTextFromField();
        assertThat("Resulting fields should contain expected parameters", actualParameters,
                hasItems(expectedParameters.toArray(new String[0])));
    }

    @Test(description = "The Sum in email should be the same as the estimate sum")
    public void sumInEmailShouldBeEqualCalculatorSum() throws ParseException, InterruptedException {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat("Expecting url should be https://cloud.google.com/products/calculator", driver.getCurrentUrl(), is(CALCULATOR_PAGE_URL));
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage
                .setupFormPage()
                .initializeCalculatorForm(CURRENT_DATA_PRESET)
                .fillAllNecessaryFields(CURRENT_DATA_PRESET)
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


}
