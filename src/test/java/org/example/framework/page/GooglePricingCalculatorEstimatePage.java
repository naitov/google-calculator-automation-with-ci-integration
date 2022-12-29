package framework.example.org.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GooglePricingCalculatorEstimatePage extends AbstractPage {
    private double actualSum = -1.0;
    private YopmailHomePage yopmailPage;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iFrameElement;

    public GooglePricingCalculatorEstimatePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getActualTextFromField() {
        List<String> tempElements = driver.findElements(By.xpath("//md-card-content[@id='resultBlock']//div[contains(@class,'list-item')]"))
                .stream().map(WebElement::getText).toList();
        List<String> webElementTextList = new ArrayList<>();
        for (String row : tempElements) {
            String[] temp = row.split("\\n");
            webElementTextList.addAll(List.of(temp));
        }
        return webElementTextList;
    }

    public double getSumFromEstimateField() throws ParseException {
        switchToCalcPageFrame(iFrameElement);
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

    public YopmailHomePage createYopmailPage() {
        String currentWindowHandle = driver.getWindowHandle();
        yopmailPage = new YopmailHomePage(driver, currentWindowHandle);
        return yopmailPage;
    }

    public GooglePricingCalculatorEstimatePage sendEmailFromPage() {
        String emailName = yopmailPage.getRandomEmailName();
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//span[text()='email']/parent::button").click();
        getElementWithPresenceWait(WaitTimeouts.TEN_SEC, "//input[@type='email']").sendKeys(emailName);
        getElementWithPresenceWait(WaitTimeouts.TEN_SEC, "//button[@aria-label='Send Email']").click();
        return this;
    }

    public void switchToYopmail() {
        driver.switchTo().window(yopmailPage.getYopmailWindowHandle());
    }

    private void switchToCalcPageFrame(WebElement frame) {
        driver.switchTo().frame(0);
        driver.switchTo().frame(frame);
    }
}