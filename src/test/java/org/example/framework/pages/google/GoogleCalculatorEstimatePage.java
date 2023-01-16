package org.example.framework.pages.google;

import io.qameta.allure.Step;
import org.example.framework.pages.AbstractPage;
import org.example.framework.pages.yopmail.YopmailHomePage;
import org.example.framework.tests.steps.EstimatePageSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import static org.example.framework.utils.Logger.LOGGER;

public class GoogleCalculatorEstimatePage extends AbstractPage {
    private final EstimatePageSteps estimatePageSteps;
    private YopmailHomePage yopmailPage;
    private String yopmailWindowHandle;

    public GoogleCalculatorEstimatePage(WebDriver driver, EstimatePageSteps estimatePageSteps) {
        super(driver);
        this.estimatePageSteps = estimatePageSteps;
    }

    @Step("New yopmail.com page")
    public YopmailHomePage openYopmailPageInNewTab() {
        String currentWindowHandle = driver.getWindowHandle();
        yopmailPage = new YopmailHomePage(driver, currentWindowHandle);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://yopmail.com/ru/");
        yopmailWindowHandle = driver.getWindowHandle();
        LOGGER.info("Switched to yopmail tab");
        return yopmailPage;
    }

    @Step("Send email from google estimate page to yopmail.com")
    public GoogleCalculatorEstimatePage sendEmail() {
        String emailName = yopmailPage.getRandomEmailNameFromYopmailPage();
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//span[text()='email']/parent::button").click();
        getElementWithPresenceWait(WaitTimeouts.TEN_SEC, "//input[@type='email']").sendKeys(emailName);
        getElementWithPresenceWait(WaitTimeouts.TEN_SEC, "//button[@aria-label='Send Email']").click();
        return this;
    }

    @Step("Switch to tab yopmail.com")
    public void switchToYopmail() {
        driver.switchTo().window(yopmailWindowHandle);
    }

    public EstimatePageSteps goToEstimateForm() {
        return estimatePageSteps;
    }
}