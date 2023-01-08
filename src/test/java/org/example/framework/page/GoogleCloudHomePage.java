package org.example.framework.page;

import io.qameta.allure.Step;
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

    @Step("Open cloud.google.com")
    public GoogleCloudHomePage openHomePage(String url) {
        driver.get(url);
        logger.info("Opened home page");
        return this;
    }

    @Step("Search for 'Google pricing calculator'")
    public GoogleCloudHomePage searchForTerm() {
        searchField.sendKeys(BaseTest.SEARCH_TERM);
        searchField.submit();
        logger.info(String.format("Searching for: \"%s\"", BaseTest.SEARCH_TERM));
        return this;
    }

    @Step("Open calculator page from Search results")
    public GooglePricingCalculatorFormPage getCalculatorPageFromSearch() {
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//div[@class='gs-title']//a").click();
        logger.info("Created new calculator page");
        return new GooglePricingCalculatorFormPage(driver);
    }
}
