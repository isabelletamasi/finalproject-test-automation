package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.constants.SignInDetails;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SignInPopUp {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);
// Being a pop-up window, we don't need a method to open a web page

    private final static By OPEN_SIGN_IN_BUTTON = By.xpath("//*[@id=\"signin_button\"]");
    private final static By USERNAME_FIELD = By.xpath("//*[@id=\"already_formusername\"]");
    private final static By PASSWORD_FIELD = By.xpath("//*[@id=\"formpassword_field\"]");
    private final static By SIGN_IN_BUTTON = By.xpath("//*[@id=\"alreadyS\"]/div[4]/button");
    private final static By OVERVIEW_NAVIGATION_LIST = By.xpath("//*[@id=\"lt_mainsidebar\"]/div[2]/div");
    private final static By YOUR_WORLD_NAVIGATION_LIST = By.xpath("//*[@class=\"sidebar_group yourworld\"]/div/a");
    private final static By DISCOVER_NAVIGATION_LIST = By.xpath("//*[@class=\"sidebar_group discover\"]/div/a");

    public void signInLibrary() {
        log.info("Sign in to LibraryThing with existent account details");
        String user = SignInDetails.LOGIN_USER.getUsername();
        String passw = SignInDetails.LOGIN_USER.getPassword();

        actions.clickElement(OPEN_SIGN_IN_BUTTON);
        actions.waitElementToBeClickable(USERNAME_FIELD, 10);
        actions.sendKeys(USERNAME_FIELD, user);
        actions.waitElementToBeClickable(PASSWORD_FIELD, 10);
        actions.sendKeys(PASSWORD_FIELD, passw);
        actions.clickElement(SIGN_IN_BUTTON);
    }

    public void invalidSignInLibrary() {
        log.info("Sign in to LibraryThing with correct username and wrong password");
        String user2 = SignInDetails.INVALID_USER.getUsername();
        String passw2 = SignInDetails.INVALID_USER.getPassword();

        actions.clickElement(OPEN_SIGN_IN_BUTTON);
        actions.waitElementToBeClickable(USERNAME_FIELD, 10);
        actions.sendKeys(USERNAME_FIELD, user2);
        actions.waitElementToBeClickable(PASSWORD_FIELD, 10);
        actions.sendKeys(PASSWORD_FIELD, passw2);
        actions.clickElement(SIGN_IN_BUTTON);
    }

    public List<WebElement> browseOverviewHeadersList() {
        actions.waitElementToBeClickable(OVERVIEW_NAVIGATION_LIST, 10);
        return actions.getElements(OVERVIEW_NAVIGATION_LIST);
    }

    public List<WebElement> browseOverviewYourWorldList() {
        return actions.getElements(YOUR_WORLD_NAVIGATION_LIST);
    }

    public List<WebElement> browseOverviewDiscoverList() {
        return actions.getElements(DISCOVER_NAVIGATION_LIST);
    }
}
