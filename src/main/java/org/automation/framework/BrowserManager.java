//BrowserManager=service class care se ocupa cu tot ce tine de browser
package org.automation.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserManager {

    private static WebDriver driver;

    public void openBrowser() {
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {

        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }

    public static String currentUrl() {
        return driver.getCurrentUrl();
    }
}
