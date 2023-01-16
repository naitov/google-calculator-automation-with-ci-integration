package org.example.framework.pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class AbstractPage {
    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getElementWithPresenceWait(WaitTimeouts timeout, String xpath) {
        return new WebDriverWait(driver, timeout.getDuration()).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public WebElement getElementWithClickableWait(WaitTimeouts timeout, String xpath) {
        return new WebDriverWait(driver, timeout.getDuration()).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    @AllArgsConstructor
    @Getter
    public enum WaitTimeouts {
        ONE_SEC(Duration.of(1, ChronoUnit.SECONDS)),
        THREE_SEC(Duration.of(3, ChronoUnit.SECONDS)),
        TEN_SEC(Duration.of(10, ChronoUnit.SECONDS)),
        SIXTY_SEC(Duration.of(60, ChronoUnit.SECONDS));
        private final Duration duration;
    }
}

