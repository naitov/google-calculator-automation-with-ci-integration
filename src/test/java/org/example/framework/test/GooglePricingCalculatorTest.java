package framework.example.org.test;

import framework.example.org.form.GoogleCalculatorForm;
import framework.example.org.form.GoogleCalculatorFormFactory;
import framework.example.org.form.GoogleEstimateForm;
import framework.example.org.form.GoogleEstimateFormFactory;
import framework.example.org.page.GoogleCloudHomePage;
import framework.example.org.page.GooglePricingCalculatorEstimatePage;
import framework.example.org.page.GooglePricingCalculatorFormPage;
import framework.example.org.page.YopmailHomePage;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GooglePricingCalculatorTest extends TestSettings {
    @Test(description = "Actual and expected sums should be equal")
    public void actualAndExpectedSumsShouldBeEqual() throws ParseException {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(), is(CALCULATOR_PAGE_URL));
        calculatorFormPage.setupFormPage();

        GoogleCalculatorForm calculatorForm = null;
        switch (TESTING_ENVIRONMENT) {
            case "dev" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithMinimumElements();
            case "staging" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
            case "qa" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElementsExcludeGpu();
        }
        calculatorFormPage.fillAllNecessaryFields(TESTING_ENVIRONMENT, calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        double expectedSum = 2275.48; //manual check in the estimate
        double actualSum = estimatePage.getSumFromEstimateField();
        assertThat("Actual and expected sums should be equal", actualSum, equalTo(expectedSum));
    }

    @Test(description = "Resulting fields should contain expected parameters")
    public void resultingFieldsShouldContainExpectedValues() {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(), is(equalTo(CALCULATOR_PAGE_URL)));
        calculatorFormPage.setupFormPage();

        GoogleCalculatorForm calculatorForm = null;
        switch (TESTING_ENVIRONMENT) {
            case "dev" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithMinimumElements();
            case "staging" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
            case "qa" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElementsExcludeGpu();
        }
        calculatorFormPage.fillAllNecessaryFields(TESTING_ENVIRONMENT, calculatorForm);
        GoogleEstimateForm estimateForm;
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        if ("dev".equals(TESTING_ENVIRONMENT)) {
            estimateForm = GoogleEstimateFormFactory.withDefaultFields();
            List<String> actualParameters = estimatePage.getActualTextFromField();
            assertThat("Expecting Region should match actual", actualParameters,
                    hasItems(estimateForm.getRegion()));
            assertThat("Expecting Provisioning Model should match actual", actualParameters,
                    hasItems(estimateForm.getProvisioningModel()));
            assertThat("Expecting Instance Type should match actual", actualParameters,
                    hasItems(estimateForm.getInstanceType()));
            assertThat("Expecting Operation System should match actual", actualParameters,
                    hasItems(estimateForm.getOperatingSystem()));
        } else {
            assertThat(calculatorForm, is(not(equalTo(null))));
            estimateForm = GoogleEstimateFormFactory.withUserFilledFields(calculatorForm);
            List<String> actualParameters = estimatePage.getActualTextFromField();
            assertThat("Expecting Region should match actual", actualParameters,
                    hasItems(estimateForm.getRegion()));
            assertThat("Expecting Commitment term should match actual", actualParameters,
                    hasItems(estimateForm.getCommitmentTerm()));
            assertThat("Expecting Provisioning Model should match actual", actualParameters,
                    hasItems(estimateForm.getProvisioningModel()));
            assertThat("Expecting Instance Type should match actual", actualParameters,
                    hasItems(estimateForm.getInstanceType()));
            assertThat("Expecting Operation System should match actual", actualParameters,
                    hasItems(estimateForm.getOperatingSystem()));
            assertThat("Expecting GPU should match actual", actualParameters,
                    hasItems(estimateForm.getGpuDies()));
            assertThat("Expecting SSD should match actual", actualParameters,
                    hasItems(estimateForm.getLocalSsd()));
        }
    }

    @Test(description = "The Sum in email should be the same as the estimate sum")
    public void sumInEmailShouldBeEqualCalculatorSum() throws ParseException, InterruptedException {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(), is(equalTo(CALCULATOR_PAGE_URL)));
        calculatorFormPage.setupFormPage();

        GoogleCalculatorForm calculatorForm = null;
        switch (TESTING_ENVIRONMENT) {
            case "dev" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithMinimumElements();
            case "staging" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElements();
            case "qa" -> calculatorForm = GoogleCalculatorFormFactory.getCalcFormWithAllElementsExcludeGpu();
        }
        calculatorFormPage.fillAllNecessaryFields(TESTING_ENVIRONMENT, calculatorForm);
        GooglePricingCalculatorEstimatePage estimatePage = calculatorFormPage.addToEstimate();
        YopmailHomePage yopmailHomePage = estimatePage.createYopmailPage();
        yopmailHomePage.openEmailPageInNewTab()
                .createNewMailBoxWithRandomName()
                .switchToEstimatePage();
        double expectedSum = estimatePage.getSumFromEstimateField();
        estimatePage.sendEmailFromPage()
                .switchToYopmail();
        double actualSum = yopmailHomePage.waitForMail().getActualSum();
        assertThat("The Sum in email should be the same as the estimate sum", actualSum, IsEqual.equalTo(expectedSum));
    }
}
