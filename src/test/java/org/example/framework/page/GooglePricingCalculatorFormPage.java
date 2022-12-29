package org.example.framework.page;

import org.example.framework.form.GoogleCalculatorForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePricingCalculatorFormPage extends AbstractPage {

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iFrameElement;

    @FindBy(xpath = "//button[@class='devsite-snackbar-action']")
    private WebElement cookieOkButton;

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement numberOfInstancesInputField;

    @FindBy(xpath = "//label[@for='select_103']/..")
    private WebElement operationSystemList;

    @FindBy(xpath = "//label[@for='select_107']/..")
    private WebElement provisioningModelList;

    @FindBy(xpath = "//label[@for='select_115']/..")
    private WebElement seriesList;

    @FindBy(xpath = "//label[@for='select_117']/..")
    private WebElement machineTypeList;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(xpath = "//label[@for='select_466']/..")
    private WebElement gpuTypeList;

    @FindBy(xpath = "//label[@for='select_468']/..")
    private WebElement numberOfGpusList;

    @FindBy(xpath = "//label[@for='select_423']/..")
    private WebElement localSsdList;

    @FindBy(xpath = "//label[@for='select_123']/..")
    private WebElement datacenterLocationList;

    @FindBy(xpath = "//label[@for='select_130']/..")
    private WebElement committedUsageList;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    public GooglePricingCalculatorFormPage(WebDriver driver) {
        super(driver);
    }

    public void setupFormPage() {
        cookieOkButton.click();
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
    }

    public void fillAllNecessaryFields(String environment, GoogleCalculatorForm form) {
        switch (environment) {
            case "dev" -> this.setNumberOfInstances(form);
//                    .selectSeries(form)
//                    .selectMachineType(form);
            case "staging" -> this.setNumberOfInstances(form)
                    .selectOperatingSystem(form)
                    .selectProvisioningModel(form)
                    .selectSeries(form)
                    .selectMachineType(form)
                    .activateCheckboxAddGPU()
                    .selectGpuType(form)
                    .selectNumberOfGpus(form)
                    .selectLocalSsd(form)
                    .selectDataCenterLocation(form)
                    .selectCommittedUsage(form);
            case "qa" -> this.setNumberOfInstances(form)
                    .selectOperatingSystem(form)
                    .selectProvisioningModel(form)
                    .selectSeries(form)
                    .selectMachineType(form)
                    .selectDataCenterLocation(form)
                    .selectCommittedUsage(form);
        }
    }

    private GooglePricingCalculatorFormPage setNumberOfInstances(GoogleCalculatorForm form) {
        numberOfInstancesInputField.sendKeys(form.getNumberOfInstances().getValue());
        return this;
    }

    private GooglePricingCalculatorFormPage selectOperatingSystem(GoogleCalculatorForm form) {
        operationSystemList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getOperatingSystem().getValue()))
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectProvisioningModel(GoogleCalculatorForm form) {
        provisioningModelList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getProvisioningModel().getValue()))
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectSeries(GoogleCalculatorForm form) {
        seriesList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getSeries().getValue()))
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectMachineType(GoogleCalculatorForm form) {
        machineTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s']", form.getMachineType().getValue()))
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage activateCheckboxAddGPU() {
        addGpuCheckbox.click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectGpuType(GoogleCalculatorForm form) {
        gpuTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s']", form.getGpuType().getValue())).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectNumberOfGpus(GoogleCalculatorForm form) {
        numberOfGpusList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s'][contains(@ng-repeat, 'listingCtrl.computeServer.gpuType')]",
                        form.getNumberOfGpu().getValue())).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectLocalSsd(GoogleCalculatorForm form) {
        localSsdList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s'][@ng-repeat='item in listingCtrl.dynamicSsd.computeServer']",
                        form.getLocalSsd().getValue())).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectDataCenterLocation(GoogleCalculatorForm form) {
        datacenterLocationList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, String.format("//*[@id='%s']",
                form.getDatacenterLocation().getValue())).click();
        return this;
    }

    private void selectCommittedUsage(GoogleCalculatorForm form) {
        committedUsageList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, String.format("//*[@id='%s']",
                form.getCommittedUsage().getValue())).click();
    }

    public GooglePricingCalculatorEstimatePage addToEstimate() {
        addToEstimateButton.submit();
        return new GooglePricingCalculatorEstimatePage(driver);
    }
}
