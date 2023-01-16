package org.example.framework.tests;

import io.qameta.allure.Step;
import org.example.framework.enitities.forms.CalculatorFormFactory;
import org.example.framework.models.Constants;
import org.example.framework.pages.google.GoogleCalculatorFormPage;
import org.example.framework.tests.steps.MainPageSteps;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SumComparisonTest {

    @Test
    @Step("Smoke test: Assert that sum in estimate page corresponds to manual sum")
    public void testMinParameters() throws ParseException {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        double actualSum = formPage.fillSmokeFields(CalculatorFormFactory.getFormWithMinElements())
                .submitForm()
                .goToEstimateForm()
                .getSumFromEstimate();
        assertThat("Actual and expected sums should be equal", actualSum, is(Constants.SMOKE_EXPECTED_SUM));
    }

    @Test
    @Step("Minimal Acceptance test: Assert that sum in estimate page corresponds to manual sum")
    public void testExtendedParameters() throws ParseException {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        double actualSum = formPage.fillMinAcceptanceFields(CalculatorFormFactory.getFormWithExtendedElements())
                .submitForm()
                .goToEstimateForm()
                .getSumFromEstimate();
        assertThat("Actual and expected sums should be equal", actualSum, is(Constants.MIN_ACCEPTANCE_EXPECTED_SUM));
    }

    @Test
    @Step("Acceptance test: Assert that sum in estimate page corresponds to manual sum")
    public void testMaxParameters() throws ParseException {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        double actualSum = formPage.fillFullAcceptanceFields(CalculatorFormFactory.getFormWithAllElements())
                .submitForm()
                .goToEstimateForm()
                .getSumFromEstimate();
        assertThat("Actual and expected sums should be equal", actualSum, is(Constants.ACCEPTANCE_EXPECTED_SUM));
    }
}
