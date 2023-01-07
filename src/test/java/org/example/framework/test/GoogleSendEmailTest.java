package org.example.framework.test;

import org.example.framework.form.GoogleCalculatorForm;
import org.example.framework.form.GoogleCalculatorFormFactory;
import org.example.framework.page.GoogleCloudHomePage;
import org.example.framework.page.GooglePricingCalculatorEstimatePage;
import org.example.framework.page.GooglePricingCalculatorFormPage;
import org.example.framework.page.YopmailHomePage;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GoogleSendEmailTest extends BaseTest {

    @Test(description = "The Sum in email should be the same as the estimate sum")
    public void sumInEmailShouldBeEqualCalculatorSum() throws ParseException {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver)
                .openHomePage(HOMEPAGE_URL)
                .searchForTerm();
        GooglePricingCalculatorFormPage calculatorFormPage = homePage.getCalculatorPageFromSearch();
        assertThat(String.format("Expecting url should be %s", CALCULATOR_PAGE_URL), driver.getCurrentUrl(), is(equalTo(CALCULATOR_PAGE_URL)));
        calculatorFormPage.setupFormPage();
        GoogleCalculatorForm calculatorForm = new GoogleCalculatorForm();
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
        estimatePage.sendEmailFromPage()
                .switchToYopmail();
        double actualSum = yopmailHomePage.waitForMail().getActualSum();
        assertThat("The Sum in email should be the same as the estimate sum", actualSum, is(equalTo(expectedSum)));
    }
}
