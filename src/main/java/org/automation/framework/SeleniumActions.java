package org.automation.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumActions {

    private final BrowserManager browserManager;

    public SeleniumActions(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    //Helper classes are used for an overall clean code
    public boolean isElementDisplayed(By locator) {
        return browserManager.getDriver().findElement(locator).isDisplayed();
    }

    public boolean isElementEnabled(By locator) {
        return browserManager.getDriver().findElement(locator).isEnabled();
    }

    public List<WebElement> getElements(By locator) {
        return browserManager.getDriver().findElements(locator);
    }

    public void clickElement(By locator) {
        browserManager.getDriver().findElement(locator).click();
    }

    public String getElementText(By locator) {
        return browserManager.getDriver().findElement(locator).getText();
    }

    public void sendKeys(By locator, String text) {
        browserManager.getDriver().findElement(locator).sendKeys(text);
    }

    public void waitElementToBeClickable(By locator, int timeOut) {
        Wait<WebDriver> wait = new WebDriverWait(browserManager.getDriver(), Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitFluentElementClickable(By locator, int timeOut) {
        Wait<WebDriver> wait = new FluentWait<>(browserManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitFluentElementVisible(By locator, int timeOut) {
        Wait<WebDriver> wait = new FluentWait<>(browserManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


//    public void hoverElement(By locator, WebDriver driver) {
//        Actions actions = new Actions(driver);
//        WebElement element = driver.findElement(locator);
//        actions.moveToElement(element).build().perform();
//    }

    public WebElement getElement(By locator) {
        return browserManager.getDriver().findElement(locator);
    }

    public void pressEnter(By locator) {
        WebElement element = getElement(locator); // Locate the element
        element.sendKeys(Keys.ENTER); // Send the Enter key input
    }

    public void addCooldown() {
        try {
            long delay = (long) (Math.random() * 2000 + 3000); // Random delay between 3-5 seconds
            Thread.sleep(delay); // Pause execution
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupt status
        }
    }

}