package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);

    private final static By QUOTES_HEADER_TITLE = By .xpath("//div[@class=\"featureTeaserBox u-clearBoth\"]//h2");
    private final static By QUOTES_DYNAMIC_SECTION = By .xpath("//*[@id=\"quotes\"]");
    private final static By SEARCH_AND_BROWSE_BOOKS_FIELD = By .xpath("//*[@id=\"sitesearch_field\"]");
    private final static By RESULTS_FROM_AUTHOR_SEARCH = By.xpath("//table[@class=\"tableList\"]//tr");
    private final static By CLOSE_POPUP_BUTTON = By.xpath("(//div[@class=\"modal__close\"]//button)[2]");


    public void openHomePage() {
        log.info("Open home page");
        manager.openBrowser();
        manager.getDriver().get("https://www.goodreads.com/");
        manager.getDriver().manage().window().maximize();
    }

    public String getQuotesTitle() {
        log.info("Get Quotes header title");
        return actions.getElementText(QUOTES_HEADER_TITLE); // returns the title of the section
    }

    public String getDynamicQuote() {
        actions.waitFluentElementVisible(QUOTES_DYNAMIC_SECTION, 10);
        return actions.getElementText(QUOTES_DYNAMIC_SECTION);
    }

    // Uses your existing search method to perform the search
    public void searchAuthorFromHomePage(String authorForSearch, int timeToWait) {
        actions.waitFluentElementClickable(SEARCH_AND_BROWSE_BOOKS_FIELD, timeToWait);
        actions.sendKeys(SEARCH_AND_BROWSE_BOOKS_FIELD, authorForSearch);
        actions.addCooldown();
        actions.pressEnter(SEARCH_AND_BROWSE_BOOKS_FIELD);
    }

    public void closePopUp() {
        actions.addCooldown();
        actions.waitElementToBeClickable(CLOSE_POPUP_BUTTON, 15);
        actions.clickElement(CLOSE_POPUP_BUTTON);
    }

    // Waits for the search results to be displayed
    public void waitForResultsToLoad() {
        actions.waitFluentElementVisible(RESULTS_FROM_AUTHOR_SEARCH, 10);
    }

    // Retrieves all book sections
    public List<WebElement> getBookResults() {
        return actions.getElements(RESULTS_FROM_AUTHOR_SEARCH);
    }

    // Checks if the book section contains the exact author name
    public boolean doesBookContainAuthor(WebElement bookSection, String expectedAuthor) {
        return bookSection.getText().contains(expectedAuthor);
    }
}
