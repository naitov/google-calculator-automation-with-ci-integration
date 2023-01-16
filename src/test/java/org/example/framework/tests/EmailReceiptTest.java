package org.example.framework.tests;

import io.qameta.allure.Step;
import org.example.framework.enitities.forms.CalculatorFormFactory;
import org.example.framework.pages.google.GoogleCalculatorEstimatePage;
import org.example.framework.pages.google.GoogleCalculatorFormPage;
import org.example.framework.pages.yopmail.YopmailHomePage;
import org.example.framework.tests.steps.MainPageSteps;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmailReceiptTest extends BaseTest {

    @Test
    @Step("Minimal Acceptance test: Assert that sum in email should be the same as sum in the estimate page")
    public void testExtendedParameters() throws ParseException {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        GoogleCalculatorEstimatePage estimatePage = formPage
                .fillMinAcceptanceFields(CalculatorFormFactory.getFormWithExtendedElements())
                .submitForm();
        double expectedSum = estimatePage.goToEstimateForm().getSumFromEstimate();
        YopmailHomePage yopmailPage = estimatePage.openYopmailPageInNewTab()
                .createNewMailBoxWithRandomName()
                .switchToEstimatePage();
        estimatePage.sendEmail()
                .switchToYopmail();
        double actualSum = yopmailPage.waitForEmail()
                .getActualSumFromEmail();
        assertThat("Sum in email should be the same as the estimate sum", actualSum, is(expectedSum));
    }

    @Test()
    @Step("Acceptance test: Assert that sum in email should be the same as sum in the estimate page")
    public void testMaxParameters() throws ParseException {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        GoogleCalculatorEstimatePage estimatePage = formPage
                .fillFullAcceptanceFields(CalculatorFormFactory.getFormWithAllElements())
                .submitForm();
        double expectedSum = estimatePage.goToEstimateForm()
                .getSumFromEstimate();
        YopmailHomePage yopmailPage = estimatePage.openYopmailPageInNewTab()
                .createNewMailBoxWithRandomName()
                .switchToEstimatePage();
        estimatePage.sendEmail()
                .switchToYopmail();
        double actualSum = yopmailPage.waitForEmail()
                .getActualSumFromEmail();
        assertThat("Sum in email should be the same as the estimate sum", actualSum, is(expectedSum));
    }
}
