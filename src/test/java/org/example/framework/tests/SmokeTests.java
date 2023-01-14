package org.example.framework.tests;

import io.qameta.allure.Step;
import org.example.framework.enitities.forms.GoogleCalculatorForm;
import org.example.framework.testdata.CalculatorFormFactory;
import org.example.framework.enitities.forms.GoogleEstimateForm;
import org.example.framework.testdata.EstimateFormFactory;
import org.example.framework.pages.google.GoogleCloudHomePage;
import org.example.framework.pages.google.GooglePricingCalculatorEstimatePage;
import org.example.framework.pages.google.GooglePricingCalculatorFormPage;
import org.example.framework.testdata.Constants;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class SmokeTests extends BaseTest {

    @Test
    @Step("Smoke test: Assert that sum in estimate page corresponds to manual sum")
    public void smokeActualAndExpectedSumsShouldBeEqual() throws ParseException {
        GooglePricingCalculatorFormPage calculatorFormPage = new GoogleCloudHomePage(driver)
                .openHomePage(Constants.HOMEPAGE_URL)
                .searchFor(Constants.SEARCH_STRING)
                .goToCalculatorPageFromSearchResults();
        assertThat("Actual URL should match " + Constants.CALCULATOR_PAGE_URL, driver.getCurrentUrl(),
                is(Constants.CALCULATOR_PAGE_URL));

        double actualSum = calculatorFormPage.fillSmokeFields()
                .submitForm()
                .getSumFromEstimate();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(Constants.SMOKE_EXPECTED_SUM));
    }

    @Test
    @Step("Smoke test: Assert that fields in estimate and in calculator pages are equal")
    public void smokeResultingFieldsShouldContainExpectedValues() {
        GooglePricingCalculatorFormPage calculatorFormPage = new GoogleCloudHomePage(driver)
                .openHomePage(Constants.HOMEPAGE_URL)
                .searchFor(Constants.SEARCH_STRING)
                .goToCalculatorPageFromSearchResults();
        assertThat("Actual URL should match " + Constants.CALCULATOR_PAGE_URL, driver.getCurrentUrl(),
                is(equalTo(Constants.CALCULATOR_PAGE_URL)));

        List<String> actualTextFromEstimate = calculatorFormPage.fillSmokeFields()
                .submitForm()
                .getTextFromFieldsInEstimate();
        GoogleEstimateForm expectedForm = EstimateFormFactory.getEstimatesDefaultFields();
        assertThat("Expected Region should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getRegion()));
        assertThat("Expected Provisioning Model should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getProvisioningModel()));
        assertThat("Expected Instance Type should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getInstanceType()));
        assertThat("Expected Operation System should match actual", actualTextFromEstimate,
                hasItem(expectedForm.getOperatingSystem()));
    }
}
