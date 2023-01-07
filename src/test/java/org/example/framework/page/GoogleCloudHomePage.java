package org.example.framework.page;

import org.example.framework.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.framework.test.BaseTest.logger;

public class GoogleCloudHomePage extends AbstractPage {

    @FindBy(name = "q")
    private WebElement searchField;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openHomePage(String url) {
        driver.get(url);
        logger.info("Opened home page");
        return this;
    }

    public GoogleCloudHomePage searchForTerm() {
        searchField.sendKeys(BaseTest.SEARCH_TERM);
        searchField.submit();
        logger.info(String.format("Searching for: \"%s\"", BaseTest.SEARCH_TERM));
        return this;
    }

    public GooglePricingCalculatorFormPage getCalculatorPageFromSearch() {
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//div[@class='gs-title']//a").click();
        logger.info("Created new calculator page");
        return new GooglePricingCalculatorFormPage(driver);
    }
}
