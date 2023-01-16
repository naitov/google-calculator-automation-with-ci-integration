package org.example.framework.pages.google;

import io.qameta.allure.Step;
import org.example.framework.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.framework.utils.Logger.LOGGER;

public class GoogleCloudHomePage extends AbstractPage {

    @FindBy(name = "q")
    private WebElement searchField;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open cloud.google.com")
    public GoogleCloudHomePage openHomePage(String url) {
        driver.get(url);
        LOGGER.info("Opened home page");
        return this;
    }

    @Step("Search for 'Google pricing calculator'")
    public GoogleCloudHomePage searchFor(String searchString) {
        searchField.sendKeys(searchString);
        searchField.submit();
        LOGGER.info(String.format("Searching for: \"%s\"", searchString));
        return this;
    }

    @Step("Open calculator page from Search results")
    public GoogleCalculatorFormPage goToCalculatorPageFromSearchResults() {
        getElementWithClickableWait(WaitTimeouts.TEN_SEC, "//div[@class='gs-title']//a").click();
        LOGGER.info("Created new calculator page");
        return new GoogleCalculatorFormPage(driver);
    }
}
