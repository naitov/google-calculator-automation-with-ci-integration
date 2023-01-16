package org.example.framework.pages.google;

import io.qameta.allure.Step;
import org.example.framework.enitities.forms.GoogleCalculatorForm;
import org.example.framework.pages.AbstractPage;
import org.example.framework.tests.steps.EstimatePageSteps;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.framework.utils.Logger.LOGGER;

public class GoogleCalculatorFormPage extends AbstractPage {

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iFrameElement;

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

    public GoogleCalculatorFormPage(WebDriver driver) {
        super(driver);
    }

    private void getInIframe() {
        try {
            getElementWithPresenceWait(WaitTimeouts.TEN_SEC, "//button[@class='devsite-snackbar-action']").click();
        } catch (TimeoutException e) {
            LOGGER.warn(String.format("Cookies alert window is missing, caught exception, %s", e.getMessage()));
        }
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
    }

    public GoogleCalculatorFormPage fillSmokeFields(GoogleCalculatorForm form) {
        getInIframe();
        this.setNumberOfInstances(form)
                .selectDataCenterLocation(form);
        LOGGER.info("Selecting fields according to Smoke scope");
        return this;
    }

    public GoogleCalculatorFormPage fillMinAcceptanceFields(GoogleCalculatorForm form) {
        getInIframe();
        this.setNumberOfInstances(form)
                .selectOperatingSystem(form)
                .selectProvisioningModel(form)
                .selectSeries(form)
                .selectMachineType(form)
                .selectDataCenterLocation(form)
                .selectCommittedUsage(form);
        LOGGER.info("Selecting fields according to Minimal acceptance test scope");
        return this;
    }

    public GoogleCalculatorFormPage fillFullAcceptanceFields(GoogleCalculatorForm form) {
        getInIframe();
        this.setNumberOfInstances(form)
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
        LOGGER.info("Selecting fields according to Full acceptance test scope");
        return this;
    }

    @Step("Select field: Number Of Instances")
    public GoogleCalculatorFormPage setNumberOfInstances(GoogleCalculatorForm form) {
        numberOfInstancesInputField.sendKeys(form.getNumberOfInstances().getValue());
        return this;
    }

    @Step("Select field: Operating System")
    public GoogleCalculatorFormPage selectOperatingSystem(GoogleCalculatorForm form) {
        operationSystemList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getOperatingSystem().getValue()))
                .click();
        return this;
    }

    @Step("Select field: Provisioning Model")
    public GoogleCalculatorFormPage selectProvisioningModel(GoogleCalculatorForm form) {
        provisioningModelList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getProvisioningModel().getValue()))
                .click();
        return this;
    }

    @Step("Select field: Series")
    public GoogleCalculatorFormPage selectSeries(GoogleCalculatorForm form) {
        seriesList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC,
                String.format("//md-option[@value='%s']", form.getSeries().getValue()))
                .click();
        return this;
    }

    @Step("Select field: Machine Type")
    public GoogleCalculatorFormPage selectMachineType(GoogleCalculatorForm form) {
        machineTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s']", form.getMachineType().getValue()))
                .click();
        return this;
    }

    @Step("Activate checkbox 'Add GPU'")
    public GoogleCalculatorFormPage activateCheckboxAddGPU() {
        addGpuCheckbox.click();
        return this;
    }

    @Step("Select field: GPU Type")
    public GoogleCalculatorFormPage selectGpuType(GoogleCalculatorForm form) {
        gpuTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s']", form.getGpuType().getValue())).click();
        return this;
    }

    @Step("Select field: Number of GPUs")
    public GoogleCalculatorFormPage selectNumberOfGpus(GoogleCalculatorForm form) {
        numberOfGpusList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s'][contains(@ng-repeat, 'listingCtrl.computeServer.gpuType')]",
                        form.getNumberOfGpu().getValue())).click();
        return this;
    }

    @Step("Select field: Local SSD")
    public GoogleCalculatorFormPage selectLocalSsd(GoogleCalculatorForm form) {
        localSsdList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC,
                String.format("//*[@value='%s'][@ng-repeat='item in listingCtrl.dynamicSsd.computeServer']",
                        form.getLocalSsd().getValue())).click();
        return this;
    }

    @Step("Select field: Datacenter Location")
    public GoogleCalculatorFormPage selectDataCenterLocation(GoogleCalculatorForm form) {
        datacenterLocationList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, String.format("//*[@id='%s']",
                form.getDatacenterLocation().getValue())).click();
        return this;
    }

    @Step("Select field: Committed Usage")
    public void selectCommittedUsage(GoogleCalculatorForm form) {
        committedUsageList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, String.format("//*[@id='%s']",
                form.getCommittedUsage().getValue())).click();
    }

    @Step("Add selected to estimate")
    public GoogleCalculatorEstimatePage submitForm() {
        addToEstimateButton.click();
        LOGGER.info("Created new estimate page");
        return new GoogleCalculatorEstimatePage(driver, new EstimatePageSteps(driver));
    }
}
