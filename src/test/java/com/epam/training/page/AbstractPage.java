package com.epam.training.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private Duration getDuration(WaitTimeouts timeout) {
        switch (timeout) {
            case ONE_SEC -> {
                return Duration.of(1, ChronoUnit.SECONDS);
            }
            case THREE_SEC -> {
                return Duration.of(3, ChronoUnit.SECONDS);
            }
            case FIVE_SEC -> {
                return Duration.of(5, ChronoUnit.SECONDS);
            }
            case TEN_SEC -> {
                return Duration.of(10, ChronoUnit.SECONDS);
            }
            default -> {
                return Duration.of(0, ChronoUnit.SECONDS);
            }
        }
    }

    public WebElement getElementWithPresenceWait(WaitTimeouts timeout, String xpath) {
        return new WebDriverWait(driver, getDuration(timeout)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public WebElement getElementWithClickableWait(WaitTimeouts timeout, String xpath) {
        return new WebDriverWait(driver, getDuration(timeout)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    enum WaitTimeouts {
        ONE_SEC,
        THREE_SEC,
        FIVE_SEC,
        TEN_SEC
    }
}
