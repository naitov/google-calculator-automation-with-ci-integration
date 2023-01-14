package org.example.framework.pages.google;

import io.qameta.allure.Step;
import org.example.framework.pages.AbstractPage;
import org.example.framework.pages.yopmail.YopmailHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.framework.utils.Logger.LOGGER;

public class GooglePricingCalculatorEstimatePage extends AbstractPage {
    private double actualSum = -1.0;
    private YopmailHomePage yopmailPage;
    private String yopmailWindowHandle;

    public GooglePricingCalculatorEstimatePage(WebDriver driver) {
        super(driver);
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
        String estimateSummaryString = getElementWithPresenceWait(WaitTimeouts.THREE_SEC, "//*[@id='compute']/descendant::b[contains(text(), 'Estimated Component Cost')]")
                .getText();
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(estimateSummaryString);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            actualSum = numberFormat.parse(matcher.group()).doubleValue();
        }
        return actualSum;
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
    public GooglePricingCalculatorEstimatePage sendEmail() {
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

}