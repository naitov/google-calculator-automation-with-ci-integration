package org.example.framework.tests;

import io.qameta.allure.Step;
import org.example.framework.enitities.forms.GoogleEstimateForm;
import org.example.framework.pages.google.GoogleCloudHomePage;
import org.example.framework.pages.google.GooglePricingCalculatorEstimatePage;
import org.example.framework.pages.google.GooglePricingCalculatorFormPage;
import org.example.framework.pages.yopmail.YopmailHomePage;
import org.example.framework.testdata.Constants;
import org.example.framework.testdata.EstimateFormFactory;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class AcceptanceTests extends BaseTest {

    @Test
    @Step("Acceptance test: Assert that sum in email should be the same as sum in the estimate page")
    public void acceptSumInEmailShouldBeEqualCalculatorSum() throws ParseException {
        GooglePricingCalculatorFormPage formPage = new GoogleCloudHomePage(driver)
                .openHomePage(Constants.HOMEPAGE_URL)
                .searchFor(Constants.SEARCH_STRING)
                .goToCalculatorPageFromSearchResults();
        assertThat("Actual URL should match " + Constants.CALCULATOR_PAGE_URL, driver.getCurrentUrl(),
                is(equalTo(Constants.CALCULATOR_PAGE_URL)));

        GooglePricingCalculatorEstimatePage estimatePage = formPage.fillFullAcceptanceFields()
                .submitForm();
        double expectedSum = estimatePage.getSumFromEstimate();
        YopmailHomePage yopmailPage = estimatePage.openYopmailPageInNewTab()
                .acceptCookies()
                .createNewMailBoxWithRandomName()
                .switchToEstimatePage();
        estimatePage.sendEmail()
                .switchToYopmail();
        double actualSum = yopmailPage.waitForEmail()
                .getActualSumFromEmail();
        assertThat("Sum in email should be the same as the estimate sum", actualSum, is(equalTo(expectedSum)));
    }

    @Test
    @Step("Acceptance test: Assert that sum in estimate page corresponds to manual sum")
    public void acceptActualAndExpectedSumsShouldBeEqual() throws ParseException {
        GooglePricingCalculatorFormPage calculatorFormPage = new GoogleCloudHomePage(driver)
                .openHomePage(Constants.HOMEPAGE_URL)
                .searchFor(Constants.SEARCH_STRING)
                .goToCalculatorPageFromSearchResults();
        assertThat("Actual URL should match " + Constants.CALCULATOR_PAGE_URL, driver.getCurrentUrl(),
                is(Constants.CALCULATOR_PAGE_URL));

        double actualSum = calculatorFormPage.fillFullAcceptanceFields()
                .submitForm()
                .getSumFromEstimate();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(Constants.ACCEPTANCE_EXPECTED_SUM));
    }

    @Test
    @Step("Acceptance test: Assert that fields in estimate and in calculator pages are equal")
    public void acceptParamResultingFieldsShouldContainExpectedValues() {
        GooglePricingCalculatorFormPage calculatorFormPage = new GoogleCloudHomePage(driver)
                .openHomePage(Constants.HOMEPAGE_URL)
                .searchFor(Constants.SEARCH_STRING)
                .goToCalculatorPageFromSearchResults();
        assertThat("Actual URL should match " + Constants.CALCULATOR_PAGE_URL, driver.getCurrentUrl(),
                is(equalTo(Constants.CALCULATOR_PAGE_URL)));

        List<String> actualTextFromEstimate = calculatorFormPage.fillFullAcceptanceFields()
                .submitForm()
                .getTextFromFieldsInEstimate();
        GoogleEstimateForm expectedForm = EstimateFormFactory.getUserFilledFields(calculatorFormPage.getCalculatorForm());
        assertThat("Expected Region should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getRegion()));
        assertThat("Expected Commitment term should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getCommitmentTerm()));
        assertThat("Expected Provisioning Model should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getProvisioningModel()));
        assertThat("Expected Instance Type should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getInstanceType()));
        assertThat("Expected Operation System should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getOperatingSystem()));
        assertThat("Expected GPU should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getGpuDies()));
        assertThat("Expected SSD should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getLocalSsd()));
    }
}
