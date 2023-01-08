package org.example.framework.test;

import io.qameta.allure.Step;
import org.example.framework.form.GoogleCalculatorForm;
import org.example.framework.form.GoogleCalculatorFormFactory;
import org.example.framework.form.GoogleEstimateForm;
import org.example.framework.form.GoogleEstimateFormFactory;
import org.example.framework.page.GoogleCloudHomePage;
import org.example.framework.page.GooglePricingCalculatorEstimatePage;
import org.example.framework.page.GooglePricingCalculatorFormPage;
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
        double expectedSum = 63.02;
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(),
                is(CALCULATOR_PAGE_URL));
        calculatorFormPage.setupFormPage();
        GoogleCalculatorForm calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithMinimumElements();
        calculatorFormPage.fillSmokeFields(calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        double actualSum = estimatePage.getSumFromEstimateField();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
    }

    @Test
    @Step("Smoke test: Assert that fields in estimate and in calculator pages are equal")
    public void smokeResultingFieldsShouldContainExpectedValues() {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(),
                is(equalTo(CALCULATOR_PAGE_URL)));
        calculatorFormPage.setupFormPage();
        GoogleCalculatorForm calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithMinimumElements();
        calculatorFormPage.fillSmokeFields(calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        GoogleEstimateForm estimateForm = GoogleEstimateFormFactory.withEstimatesDefaultFields();
        List<String> actualParameters = estimatePage.getActualTextFromField();
        assertThat("Expected Region should match actual", actualParameters,
                hasItem(estimateForm.getRegion()));
        assertThat("Expected Provisioning Model should match actual", actualParameters,
                hasItem(estimateForm.getProvisioningModel()));
        assertThat("Expected Instance Type should match actual", actualParameters,
                hasItem(estimateForm.getInstanceType()));
        assertThat("Expected Operation System should match actual", actualParameters,
                hasItem(estimateForm.getOperatingSystem()));
    }
}
