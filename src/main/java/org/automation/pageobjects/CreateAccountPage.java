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


    private final static By SIGN_UP_WITH_EMAIL_BUTTON = By.xpath("//div[@id=\"signInUsingContent\"]//a[3]");
    //wait for new page to load
    private final static By YOUR_NAME_TEXT = By.xpath("//*[@id=\"ap_customer_name\"]");
    private final static By EMAIL_TEXT = By.xpath("//*[@id=\"ap_email\"]");
    private final static By PASSWORD_TEXT = By.xpath("//*[@id=\"ap_password\"]");
    private final static By REENTER_PASSWORD_TEXT = By.xpath("//*[@id=\"ap_password_check\"]");
    private final static By CREATE_ACCOUNT_BUTTON = By.xpath("//*[@id=\"continue\"]");



    public void clickSignUpWithEmailButton() {
        log.info("Click 'Sign up with email' Button");
        actions.clickElement(SIGN_UP_WITH_EMAIL_BUTTON);
        actions.addCooldown();
    }

    public List<String> getAccountLabels() {
        log.info("Get account labels");
        String usernameText = actions.getElementText(YOUR_NAME_TEXT);
        String emailText = actions.getElementText(EMAIL_TEXT);
        String passwordText = actions.getElementText(PASSWORD_TEXT);
        String confirmPasswordText = actions.getElementText(REENTER_PASSWORD_TEXT);

        return List.of(usernameText, emailText, passwordText, confirmPasswordText);
    }

    public void clickCreateAccountButton() {
        log.info("Click the 'Create account' button to continue the sign in process");
        actions.clickElement(CREATE_ACCOUNT_BUTTON);
        actions.addCooldown();
    }
}