package org.example.framework.pages.yopmail;

import io.qameta.allure.Step;
import org.example.framework.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.framework.utils.Logger.LOGGER;

public class YopmailHomePage extends AbstractPage {
    private final String estimateWindowHandle;

    @FindBy(xpath = "//div[@id='nbmail']")
    WebElement mailCounterLabel;

    @FindBy(xpath = "//*[contains(text(), 'Estimated Monthly Cost:')]")
    WebElement estimatedMonthlyCostField;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iFrameElement;
    private String randomEmailNameFromYopmailPage;


    public YopmailHomePage(WebDriver driver, String estimateWindowHandle) {
        super(driver);
        this.estimateWindowHandle = estimateWindowHandle;
    }

    private void acceptCookies() {
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//button[@id='accept']")
                .click();
    }

    @Step("Create new mailbox with random name")
    public YopmailHomePage createNewMailBoxWithRandomName() {
        acceptCookies();
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//a[@href='email-generator']").click();
        randomEmailNameFromYopmailPage = String.format("%s@yopmail.com", getElementWithPresenceWait(WaitTimeouts.TEN_SEC, "//span[@class='genytxt']").getText());
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//button[@onclick='egengo();']").click();
        LOGGER.info("Created new mailbox with random name");
        return this;
    }

    @Step("Switch to google estimate page")
    public YopmailHomePage switchToEstimatePage() {
        driver.switchTo().window(estimateWindowHandle);
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
        LOGGER.info("Switched to estimate tab");
        return this;
    }

    @Step("Wait for mail")
    public YopmailHomePage waitForEmail() {
        while (mailCounterLabel.getText().equals("0 mail")) {
            driver.manage().timeouts().implicitlyWait(Duration.of(30, ChronoUnit.SECONDS));
            getElementWithClickableWait(WaitTimeouts.SIXTY_SEC, "//button[@id='refresh']").click();
            LOGGER.info("Waiting for email from google");
        }
        return this;
    }

    @Step("Get sum from email")
    public double getActualSumFromEmail() throws ParseException {
        WebElement mailFrame = getElementWithPresenceWait(WaitTimeouts.ONE_SEC, "//iframe[@id='ifmail']");
        driver.switchTo().frame(mailFrame);
        String emailCost = estimatedMonthlyCostField.getText();
        double parsedSumFromEmail;
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(emailCost);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSumFromEmail = numberFormat.parse(matcher.group()).doubleValue();
            LOGGER.info(String.format("Sum in email = %s", parsedSumFromEmail));
        } else {
            throw new AssertionError("Sum in email not found");
        }
        return parsedSumFromEmail;
    }

    public String getRandomEmailNameFromYopmailPage() {
        return randomEmailNameFromYopmailPage;
    }
}
