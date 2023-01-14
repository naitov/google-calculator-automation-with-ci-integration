package org.example.framework.pages.google;

import io.qameta.allure.Step;
import lombok.Getter;
import org.example.framework.testdata.CalculatorFormFactory;
import org.example.framework.enitities.forms.GoogleCalculatorForm;
import org.example.framework.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.framework.utils.Logger.LOGGER;

public class GooglePricingCalculatorFormPage extends AbstractPage {
    @Getter
    private GoogleCalculatorForm calculatorForm;

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


    private void setupFormPage() {
        cookieOkButton.click();
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
    }

    public GooglePricingCalculatorFormPage fillSmokeFields() {
        setupFormPage();
        calculatorForm = CalculatorFormFactory.getCalcFormWithMinimumElements();
        this.setNumberOfInstances(calculatorForm)
                .selectDataCenterLocation(calculatorForm);
        LOGGER.info("Selecting fields according to Smoke scope");
        return this;
    }

    public GooglePricingCalculatorFormPage fillMinAcceptanceFields() {
        setupFormPage();
        calculatorForm = CalculatorFormFactory.getCalcFormWithAllElementsExcludeGpu();
        this.setNumberOfInstances(calculatorForm)
                .selectOperatingSystem(calculatorForm)
                .selectProvisioningModel(calculatorForm)
                .selectSeries(calculatorForm)
                .selectMachineType(calculatorForm)
                .selectDataCenterLocation(calculatorForm)
                .selectCommittedUsage(calculatorForm);
        LOGGER.info("Selecting fields according to Minimal acceptance test scope");
        return this;
    }

    public GooglePricingCalculatorFormPage fillFullAcceptanceFields() {
        setupFormPage();
        calculatorForm = CalculatorFormFactory.getCalcFormWithAllElements();
        this.setNumberOfInstances(calculatorForm)
                .selectOperatingSystem(calculatorForm)
                .selectProvisioningModel(calculatorForm)
                .selectSeries(calculatorForm)
                .selectMachineType(calculatorForm)
                .activateCheckboxAddGPU()
                .selectGpuType(calculatorForm)
                .selectNumberOfGpus(calculatorForm)
                .selectLocalSsd(calculatorForm)
                .selectDataCenterLocation(calculatorForm)
                .selectCommittedUsage(calculatorForm);
        LOGGER.info("Selecting fields according to Full acceptance test scope");
        return this;
    }

    @Step("Select field: Number Of Instances")
    private GooglePricingCalculatorFormPage setNumberOfInstances(GoogleCalculatorForm form) {
        numberOfInstancesInputField.sendKeys(form.getNumberOfInstances().getValue());
        return this;
    }

    @Step("Select field: Operating System")
    private GooglePricingCalculatorFormPage selectOperatingSystem(GoogleCalculatorForm form) {
        operationSystemList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getOperatingSystem().getValue()))
                .click();
        return this;
    }

    @Step("Select field: Provisioning Model")
    private GooglePricingCalculatorFormPage selectProvisioningModel(GoogleCalculatorForm form) {
        provisioningModelList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getProvisioningModel().getValue()))
                .click();
        return this;
    }

    @Step("Select field: Series")
    private GooglePricingCalculatorFormPage selectSeries(GoogleCalculatorForm form) {
        seriesList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getSeries().getValue()))
                .click();
        return this;
    }

    @Step("Select field: Machine Type")
    private GooglePricingCalculatorFormPage selectMachineType(GoogleCalculatorForm form) {
        machineTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s']", form.getMachineType().getValue()))
                .click();
        return this;
    }

    @Step("Activate checkbox 'Add GPU'")
    private GooglePricingCalculatorFormPage activateCheckboxAddGPU() {
        addGpuCheckbox.click();
        return this;
    }

    @Step("Select field: GPU Type")
    private GooglePricingCalculatorFormPage selectGpuType(GoogleCalculatorForm form) {
        gpuTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s']", form.getGpuType().getValue())).click();
        return this;
    }

    @Step("Select field: Number of GPUs")
    private GooglePricingCalculatorFormPage selectNumberOfGpus(GoogleCalculatorForm form) {
        numberOfGpusList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s'][contains(@ng-repeat, 'listingCtrl.computeServer.gpuType')]",
                        form.getNumberOfGpu().getValue())).click();
        return this;
    }

    @Step("Select field: Local SSD")
    private GooglePricingCalculatorFormPage selectLocalSsd(GoogleCalculatorForm form) {
        localSsdList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s'][@ng-repeat='item in listingCtrl.dynamicSsd.computeServer']",
                        form.getLocalSsd().getValue())).click();
        return this;
    }

    @Step("Select field: Datacenter Location")
    private GooglePricingCalculatorFormPage selectDataCenterLocation(GoogleCalculatorForm form) {
        datacenterLocationList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, String.format("//*[@id='%s']",
                form.getDatacenterLocation().getValue())).click();
        return this;
    }

    @Step("Select field: Committed Usage")
    private void selectCommittedUsage(GoogleCalculatorForm form) {
        committedUsageList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, String.format("//*[@id='%s']",
                form.getCommittedUsage().getValue())).click();
    }

    @Step("Add selected to estimate")
    public GooglePricingCalculatorEstimatePage submitForm() {
        addToEstimateButton.click();
        LOGGER.info("Created new estimate page");
        return new GooglePricingCalculatorEstimatePage(driver);
    }
}
