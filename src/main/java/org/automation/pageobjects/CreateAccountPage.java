package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;

import java.util.List;

public class CreateAccountPage {
    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);

    private final static By JOIN_NOW_BUTTON = By.xpath("//*[@id=\"joinnow_button\"]");
    private final static By USERNAME_TEXT = By.xpath("//*[@id=\"new_formusername\"]");
    private final static By PASSWORD_TEXT = By.xpath("//*[@id=\"new_formpassword\"]");
    private final static By CONFIRM_PASSWORD_TEXT = By.xpath("//*[@id=\"new_formpassword_confirm\"]");
    private final static By EMAIL_TEXT = By.xpath("//*[@id=\"formemail\"]");



    public void clickJoinNowButton() {
        log.info("Click Join Now Button");
        actions.clickElement(JOIN_NOW_BUTTON);
    }

    public List<String> getAccountLabels() {
        log.info("Get account labels");
        String usernameText = actions.getElementText(USERNAME_TEXT);
        String passwordText = actions.getElementText(PASSWORD_TEXT);
        String confirmPasswordText = actions.getElementText(CONFIRM_PASSWORD_TEXT);
        String emailText = actions.getElementText(EMAIL_TEXT);

        return List.of(usernameText, passwordText, confirmPasswordText, emailText);
    }
}