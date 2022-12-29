package org.example.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YopmailHomePage extends AbstractPage {
    private final String estimateWindowHandle;

    @FindBy(xpath = "//div[@id='nbmail']")
    WebElement mailCounterLabel;

    @FindBy(xpath = "//*[contains(text(), 'Estimated Monthly Cost:')]")
    WebElement estimatedMonthlyCostField;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iFrameElement;
    private String randomEmailName;
    private String yopmailWindowHandle;


    public YopmailHomePage(WebDriver driver, String estimateWindowHandle) {
        super(driver);
        this.estimateWindowHandle = estimateWindowHandle;

    }

    public YopmailHomePage openEmailPageInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://yopmail.com/ru/");
        yopmailWindowHandle = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='accept']"))).click();
        return this;
    }

    public YopmailHomePage createNewMailBoxWithRandomName() {
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//a[@href='email-generator']").click();
        randomEmailName = String.format("%s@yopmail.com", getElementWithPresenceWait(WaitTimeouts.TEN_SEC, "//span[@class='genytxt']").getText());
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//button[@onclick='egengo();']").click();
        return this;
    }

    public void switchToEstimatePage() {
        driver.switchTo().window(estimateWindowHandle);
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
    }

    public YopmailHomePage waitForMail() {
        while (mailCounterLabel.getText().equals("0 mail")) {
            driver.manage().timeouts().implicitlyWait(Duration.of(30, ChronoUnit.SECONDS));
            getElementWithClickableWait(WaitTimeouts.SIXTY_SEC, "//button[@id='refresh']").click();
        }
        return this;
    }

    public double getActualSum() throws ParseException {
        WebElement mailFrame = getElementWithPresenceWait(WaitTimeouts.ONE_SEC, "//iframe[@id='ifmail']");
        driver.switchTo().frame(mailFrame);
        String emailCost = estimatedMonthlyCostField.getText();
        double parsedSumFromEmail = 0d;
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(emailCost);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSumFromEmail = numberFormat.parse(matcher.group()).doubleValue();
        }
        return parsedSumFromEmail;
    }

    public String getRandomEmailName() {
        return randomEmailName;
    }

    public String getYopmailWindowHandle() {
        return yopmailWindowHandle;
    }
}
