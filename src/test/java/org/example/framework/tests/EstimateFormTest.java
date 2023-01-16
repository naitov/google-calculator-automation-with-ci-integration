package org.example.framework.tests;

import io.qameta.allure.Step;
import org.example.framework.enitities.forms.CalculatorFormFactory;
import org.example.framework.enitities.forms.GoogleCalculatorForm;
import org.example.framework.pages.google.GoogleCalculatorFormPage;
import org.example.framework.tests.steps.EstimatePageSteps;
import org.example.framework.tests.steps.MainPageSteps;
import org.testng.annotations.Test;

import java.util.List;

public class EstimateFormTest extends BaseTest {
    @Test
    @Step("Smoke test: Assert that fields in estimate and in calculator pages are equal")
    public void testMinParameters() {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        List<String> actualTextFromEstimate = formPage.fillSmokeFields(CalculatorFormFactory.getFormWithMinElements())
                .submitForm()
                .goToEstimateForm()
                .getTextFromFieldsInEstimate();
        EstimatePageSteps.makeMinParamsAssertion(actualTextFromEstimate);
    }

    @Test
    @Step("Minimal Acceptance test: Assert that fields in estimate and in calculator pages are equal")
    public void testExtendedParameters() {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        GoogleCalculatorForm extendedForm = CalculatorFormFactory.getFormWithExtendedElements();
        List<String> actualTextFromEstimate = formPage.fillMinAcceptanceFields(extendedForm)
                .submitForm()
                .goToEstimateForm()
                .getTextFromFieldsInEstimate();
        EstimatePageSteps.makeExtendedParamsAssertion(extendedForm, actualTextFromEstimate);
    }

    @Test
    @Step("Acceptance test: Assert that fields in estimate and in calculator pages are equal")
    public void testMaxParameters() {
        GoogleCalculatorFormPage formPage = MainPageSteps.openCalculatorFormPageFromSearch();
        GoogleCalculatorForm maxForm = CalculatorFormFactory.getFormWithAllElements();
        List<String> actualTextFromEstimate = formPage.fillFullAcceptanceFields(maxForm)
                .submitForm()
                .goToEstimateForm()
                .getTextFromFieldsInEstimate();
        EstimatePageSteps.makeMaxParamsAssertion(maxForm, actualTextFromEstimate);
    }
}
