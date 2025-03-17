package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YourBooksPage {
    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);
    private final static By WANT_TO_READ_DROPDOWN = By.xpath("(//div[@class=\"wantToReadButton__right\"])[2]");
    private final static By CURRENTLY_READING_DROPDOWN_OPTION = By.xpath("((//div[contains(@class,\"dropdown__menu--wantToReadMenu\")])[2]//li)[2]");
    private final static By CURRENTLY_READING_BOOK_COUNT = By.xpath("(//div[@class=\"userShelf\"])[2]//a");
    private final static By ALL_BOOKS_LIST = By.xpath("//tbody[@id=\"booksBody\"]//tr");

    public void clickOnBookDropdown() {
        log.info("Clicking the Want to Read dropdown to open it");
        actions.waitElementToBeClickable(WANT_TO_READ_DROPDOWN, 10);
        actions.clickElement(WANT_TO_READ_DROPDOWN);
    }

    // Select a collection from the dropdown
    public void addToCollection() {
        actions.waitElementToBeClickable(CURRENTLY_READING_DROPDOWN_OPTION, 12);
        actions.clickElement(CURRENTLY_READING_DROPDOWN_OPTION);
        actions.addCooldown();
    }

    public void openMyBooksPage() {
        log.info("Open My Books page");
        manager.getDriver().get("https://www.goodreads.com/review/list/188615106?ref=nav_mybooks/");
    }

    public String isBookInCollection() {
        log.info("Verifying if the book was added to the collection");
        actions.addCooldown();
        return actions.getElementText(CURRENTLY_READING_BOOK_COUNT);
    }

    public List<WebElement> getAllBooks() {
        log.info("Retrieving the total number of books");
        actions.addCooldown();
        return actions.getElements(ALL_BOOKS_LIST);
    }
}