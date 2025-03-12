package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class HomePage {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);

    private final static By HOME_PAGE_BANNER = By .xpath("/html/body/div[2]/main/div[2]/section[1]/div[1]/div[1]/img");
    private final static By HOME_PAGE_BOOKS_ACTIVITY = By .xpath("(//div[@class='ffarea fgap'])");
    private final static By SEARCH_FIELD = By .xpath("//*[@id=\"mastsearch_query\"]");
    private final static By SEARCH_MAGNIFIER_BUTTON = By .xpath("//*[@id=\"mastsearch_glass\"]");
    private final static By SPINNER = By .xpath("//*[@id=\"auf_uUGbA\"]");
    private final static By SEARCH_BOOK_ENTRIES_FROM_AUTHOR = By.xpath("//table//tr");

    public void openHomePage() {
        log.info("Open home page");
        manager.openBrowser();
        manager.getDriver().get("https://www.librarything.com/");
        manager.getDriver().manage().window().maximize();
    }

    public String homePageTitle() {
        log.info("Get home page title");
        return manager.getDriver().getTitle(); //ne returneaza titlul paginii
    }

    public boolean isBannerDisplayed() {
        log.info("Check if banner is displayed");
        return actions.isElementDisplayed(HOME_PAGE_BANNER);
    }

    public boolean isBannerEnabled() {
        log.info("Check if banner is enabled");
        return actions.isElementEnabled(HOME_PAGE_BANNER);
    }

    public List<WebElement> recentBooksDisplayed() {
        log.info("Check if books in Recent Activity are displayed");
        manager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return actions.getElements(HOME_PAGE_BOOKS_ACTIVITY);
    }

    public String searchAuthorFromHomePage(String authorForSearch, int timeToWait) {
        actions.waitFluentElementClickable(SEARCH_FIELD, timeToWait);
        actions.sendKeys(SEARCH_FIELD, authorForSearch);
        actions.clickElement(SEARCH_MAGNIFIER_BUTTON);
        actions.waitForSpinnerToHide(SPINNER, 10);
        return actions.getElementText(SEARCH_BOOK_ENTRIES_FROM_AUTHOR);
    }
}
