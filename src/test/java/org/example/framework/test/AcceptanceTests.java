package org.example.framework.test;

import org.example.framework.form.GoogleCalculatorForm;
import org.example.framework.form.GoogleCalculatorFormFactory;
import org.example.framework.form.GoogleEstimateForm;
import org.example.framework.form.GoogleEstimateFormFactory;
import org.example.framework.page.GoogleCloudHomePage;
import org.example.framework.page.GooglePricingCalculatorEstimatePage;
import org.example.framework.page.GooglePricingCalculatorFormPage;
import org.example.framework.page.YopmailHomePage;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class AcceptanceTests extends BaseTest {
    @Test(description = "Acceptance test, checks that sum in email should be the same as sum in the estimate page")
    public void acceptSumInEmailShouldBeEqualCalculatorSum() throws ParseException {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(),
                is(equalTo(CALCULATOR_PAGE_URL)));
        calculatorFormPage.setupFormPage();
        GoogleCalculatorForm calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
        calculatorFormPage.fillFullAcceptanceFields(calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        double expectedSum = estimatePage.getSumFromEstimateField();
        YopmailHomePage yopmailHomePage = estimatePage.createYopmailPage();
        yopmailHomePage.openEmailPageInNewTab()
                .createNewMailBoxWithRandomName()
                .switchToEstimatePage();
        estimatePage.sendEmailFromPage()
                .switchToYopmail();
        double actualSum = yopmailHomePage.waitForMail().getActualSum();
        assertThat("The Sum in email should be the same as the estimate sum", actualSum, is(equalTo(expectedSum)));
    }

    @Test(description = "Acceptance test, checks if sum in estimate page corresponds to manual sum")
    public void acceptActualAndExpectedSumsShouldBeEqual() throws ParseException {
        double expectedSum = 2275.48;
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(),
                is(CALCULATOR_PAGE_URL));
        calculatorFormPage.setupFormPage();
        GoogleCalculatorForm calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
        calculatorFormPage.fillFullAcceptanceFields(calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        double actualSum = estimatePage.getSumFromEstimateField();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
    }

    @Test(description = "Acceptance test, checks if fields in estimate and in calculator pages are equal")
    public void acceptParamResultingFieldsShouldContainExpectedValues() {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(),
                is(equalTo(CALCULATOR_PAGE_URL)));
        calculatorFormPage.setupFormPage();
        GoogleCalculatorForm calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
        calculatorFormPage.fillFullAcceptanceFields(calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        GoogleEstimateForm estimateForm = GoogleEstimateFormFactory.withUserFilledFields(calculatorForm);
        List<String> actualParameters = estimatePage.getActualTextFromField();
        assertThat("Expected Region should match actual", actualParameters,
                hasItem(estimateForm.getRegion()));
        assertThat("Expected Commitment term should match actual", actualParameters,
                hasItem(estimateForm.getCommitmentTerm()));
        assertThat("Expected Provisioning Model should match actual", actualParameters,
                hasItem(estimateForm.getProvisioningModel()));
        assertThat("Expected Instance Type should match actual", actualParameters,
                hasItem(estimateForm.getInstanceType()));
        assertThat("Expected Operation System should match actual", actualParameters,
                hasItem(estimateForm.getOperatingSystem()));
        assertThat("Expected GPU should match actual", actualParameters,
                hasItem(estimateForm.getGpuDies()));
        assertThat("Expected SSD should match actual", actualParameters,
                hasItem(estimateForm.getLocalSsd()));
    }
}
