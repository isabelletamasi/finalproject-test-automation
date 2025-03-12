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

    private final static By YOUR_BOOKS_BUTTON = By.xpath("(//*[@id=\"masttab_books\"])[1]");
    private final static By YOUR_BOOKS_CATEGORY_DROPDOWN = By.xpath("//*[@id=\"catalogSelectedCollection\"]");
    private final static By YOUR_BOOKS_LIST = By.xpath("//*[@id='lt_catalog_list']");

    public By getCategoryDropdownOptionLocator(String optionText) {
        return By.xpath("//div[@id='powerbarCollectionsMenu']//a[contains(text(), '" + optionText + "')]");
    }

    public void openYourBooksPage() {
        log.info("Navigating to 'Your Books' page");
        actions.waitElementToBeClickable(YOUR_BOOKS_BUTTON, 10);
        actions.clickElement(YOUR_BOOKS_BUTTON);
    }

    // Select a category from the dropdown and return the list of books
    public List<WebElement> selectCategory(String category) {
        log.info("Selecting filter option for books list");

        // Locate the dropdown and select the desired category
        actions.clickElement(YOUR_BOOKS_CATEGORY_DROPDOWN);
        By favoritesXPath = getCategoryDropdownOptionLocator(category);
        actions.clickElement(favoritesXPath);

        actions.waitElementToBeClickable(YOUR_BOOKS_LIST, 10);

        // Retrieve and return the list of books
        log.info("Fetching the list of books for the selected category");
        return actions.getElements(YOUR_BOOKS_LIST);
    }
}