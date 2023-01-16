package org.example.framework.tests.steps;

import io.qameta.allure.Step;
import org.example.framework.enitities.forms.EstimateFormFactory;
import org.example.framework.enitities.forms.GoogleCalculatorForm;
import org.example.framework.enitities.forms.GoogleEstimateForm;
import org.example.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class EstimatePageSteps extends AbstractPage {
    public EstimatePageSteps(WebDriver driver) {
        super(driver);
    }

    public static void makeMinParamsAssertion(List<String> actualTextFromEstimate) {
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

    public static void makeExtendedParamsAssertion(GoogleCalculatorForm extendedForm, List<String> actualTextFromEstimate) {
        GoogleEstimateForm expectedForm = EstimateFormFactory.getUserFilledFields(extendedForm);
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
    }

    public static void makeMaxParamsAssertion(GoogleCalculatorForm maxForm, List<String> actualTextFromEstimate) {
        GoogleEstimateForm expectedForm = EstimateFormFactory.getUserFilledFields(maxForm);
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

    @Step("Get actual text from field")
    public List<String> getTextFromFieldsInEstimate() {
        List<String> tempElements = driver.findElements(By.xpath("//md-card-content[@id='resultBlock']//div[contains(@class,'list-item')]"))
                .stream().map(WebElement::getText).toList();
        List<String> webElementTextList = new ArrayList<>();
        for (String row : tempElements) {
            String[] temp = row.split("\\n");
            webElementTextList.addAll(List.of(temp));
        }
        return webElementTextList;
    }

    @Step("Get sum from estimate field")
    public double getSumFromEstimate() throws ParseException {
        String estimateSummaryString = getElementWithPresenceWait(
                WaitTimeouts.THREE_SEC,
                "//*[@id='compute']/descendant::b[contains(text(), 'Estimated Component Cost')]")
                .getText();
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(estimateSummaryString);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            return numberFormat.parse(matcher.group()).doubleValue();
        } else {
            throw new AssertionError("Sum in estimate not found");
        }
    }
}
