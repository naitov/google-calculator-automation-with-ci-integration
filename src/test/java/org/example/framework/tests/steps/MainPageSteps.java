package org.example.framework.tests.steps;

import org.example.framework.models.Constants;
import org.example.framework.pages.google.GoogleCalculatorFormPage;
import org.example.framework.pages.google.GoogleCloudHomePage;
import org.example.framework.tests.BaseTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPageSteps extends BaseTest {

    public static GoogleCalculatorFormPage openCalculatorFormPageFromSearch() {
        GoogleCalculatorFormPage calculatorFormPage = new GoogleCloudHomePage(driver)
                .openHomePage(Constants.HOMEPAGE_URL)
                .searchFor(Constants.SEARCH_STRING)
                .goToCalculatorPageFromSearchResults();
        assertThat("Actual URL should match " + Constants.CALCULATOR_PAGE_URL, driver.getCurrentUrl(),
                is(Constants.CALCULATOR_PAGE_URL));
        return calculatorFormPage;
    }
}
