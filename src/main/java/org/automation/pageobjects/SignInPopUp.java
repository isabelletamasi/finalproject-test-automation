package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.constants.SignInDetails;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;


public class SignInPopUp {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);
// Being a pop-up window, we don't need a method to open a web page

    private final static By SIGN_IN_OPTION = By.xpath("//div[@class=\"authSwitchFlow u-marginTopLarge\"]//a");
    private final static By SIGN_IN_WITH_EMAIL_OPTION = By.xpath("//button[@class=\"gr-button gr-button--dark gr-button--auth authPortalConnectButton authPortalSignInButton\"]");
    // (//div[@class="third_party_sign_in"]//a)[4]
    private final static By EMAIL_TEXT = By.xpath("//*[@id=\"ap_email\"]");
    private final static By PASSWORD_TEXT = By.xpath("//*[@id=\"ap_password\"]");
    private final static By SIGN_IN_BUTTON = By.xpath("//*[@id=\"signInSubmit\"]");
    private final static By USER_MY_BOOKS_TAB = By.xpath("((//ul[@class=\"siteHeader__menuList\"])[2]//li)[2]//a");
    private final static By USER_CURRENTLY_READING_SECTION = By.xpath("//section[@class=\"currentlyReadingShelf\"]//h3");
    private final static By WRONG_CREDENTIALS_MESSAGE = By.xpath("(//div[@class=\"a-box-inner a-alert-container\"])[1]");

    public void signInAccount() {
        log.info("Sign in with existent account details");
        String user = SignInDetails.LOGIN_USER.getUsername();
        String passw = SignInDetails.LOGIN_USER.getPassword();

        actions.waitElementToBeClickable(SIGN_IN_OPTION, 12);
        actions.clickElement(SIGN_IN_OPTION);
        actions.waitElementToBeClickable(SIGN_IN_WITH_EMAIL_OPTION, 10);
        actions.clickElement(SIGN_IN_WITH_EMAIL_OPTION);
        actions.addCooldown();
        actions.waitElementToBeClickable(EMAIL_TEXT, 12);
        actions.sendKeys(EMAIL_TEXT, user);
        actions.waitElementToBeClickable(PASSWORD_TEXT, 10);
        actions.sendKeys(PASSWORD_TEXT, passw);
        actions.clickElement(SIGN_IN_BUTTON);
        actions.addCooldown();
    }

//    public boolean isMyBooksElementVisible() {
//        actions.waitFluentElementVisible(USER_MY_BOOKS_TAB, 20);
//        return actions.isElementDisplayed(USER_MY_BOOKS_TAB);
//    }

    public boolean isCurrentlyReadingElementVisible() {
        actions.waitFluentElementVisible(USER_CURRENTLY_READING_SECTION, 20);
        return actions.isElementDisplayed(USER_CURRENTLY_READING_SECTION);
    }

    public void invalidSignInAccount() {
        log.info("Sign in with wrong credentials");
        String user = SignInDetails.INVALID_USER.getUsername();
        String passw = SignInDetails.INVALID_USER.getPassword();

        actions.waitElementToBeClickable(SIGN_IN_OPTION, 12);
        actions.clickElement(SIGN_IN_OPTION);
        actions.waitElementToBeClickable(SIGN_IN_WITH_EMAIL_OPTION, 10);
        actions.clickElement(SIGN_IN_WITH_EMAIL_OPTION);
        actions.waitElementToBeClickable(EMAIL_TEXT, 10);
        actions.sendKeys(EMAIL_TEXT, user);
        actions.waitElementToBeClickable(PASSWORD_TEXT, 10);
        actions.sendKeys(PASSWORD_TEXT, passw);
        actions.clickElement(SIGN_IN_BUTTON);
    }

    public boolean isSignInFailing() {
        log.info("Checking if Sign In failed with wrong credentials");
        actions.waitFluentElementVisible(WRONG_CREDENTIALS_MESSAGE, 10);
        String wrongCredentialsMessage = actions.getElementText(WRONG_CREDENTIALS_MESSAGE);
        return wrongCredentialsMessage.contains("There was a problem");
    }

    /*public void acceptCookies() {
        log.info("Accepting cookies");
        actions.waitElementToBeClickable(COOKIE_BUTTON, 10);
        actions.clickElement(COOKIE_BUTTON);
        Set<Cookie> cookies = manager.getDriver().manage().getCookies();
        for (Cookie cookie : cookies) {
            manager.getDriver().manage().addCookie(cookie);
        }
        manager.getDriver().navigate().refresh();
    }*/


//    public List<WebElement> browseOverviewHeadersList() {
//        actions.waitElementToBeClickable(OVERVIEW_NAVIGATION_LIST, 10);
//        return actions.getElements(OVERVIEW_NAVIGATION_LIST);
//    }
//
//    public List<WebElement> browseOverviewYourWorldList() {
//        return actions.getElements(YOUR_WORLD_NAVIGATION_LIST);
//    }
//
//    public List<WebElement> browseOverviewDiscoverList() {
//        return actions.getElements(DISCOVER_NAVIGATION_LIST);
//    }
}
