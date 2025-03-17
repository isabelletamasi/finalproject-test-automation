//BrowserManager=service class care se ocupa cu tot ce tine de browser
package org.automation.framework;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BrowserManager {

    private static WebDriver driver;

    public void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

//        options.addArguments("--enable-javascript");
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        options.setExperimentalOption("useAutomationExtension", false);
//        options.addArguments("--disable-infobars");
//        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.5481.178 Safari/537.36");
//        options.addArguments("--headless");
        //driver.manage().addCookie(new Cookie("cf_clearance", "cookie-domain:.librarything.com cookie-name:cf_clearance"));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
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

    public static void setCookie() {
        String dateString = "Thu Mar 14 21:24:39 EET 2026";
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(dateString);
            System.out.println("Parsed Date: " + date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        date.toString();

        Cookie cookie = new Cookie.Builder("cf_clearance", "RxfYVUQBucuSLMBbHp7SRxFyWE7MwCpIgbXVa06q0mg-1741973586-1.2.1.1-RDR7pe44Nx9E7465HRwI3ZkIHYLPgEt2isnUm_MYEVRpyXXDrJgcowYzrZlCZTdoTjUCR8PGCTWG0dfGGJrvR0SenXovF5oqqeMdh1WvjbjVC22OJ4qRaWsegwMuduCw9ZRSpd1ANRVt0Bz0pBxZ6km9CO.zSwMT6GhHMkeaWaaTg4EBbgz_1fhGpb.Pj_H7jwEpWHkhuJ1ENYPXGZypUBNGF_fyFSN7XCJ6oIobmkv9Ey1AtN8_ScnReoC9_xNHDD9zecCpQ71sfR.Nwj1XGUUocugXPxu_gmrvgGiLPILn6MZ2EXf8dWTK8lWuItuDXHDqxhKjUTRTXJ64gryUjZJJteKqucCfk.2qNQyUVB4")
                .path("/")
                .domain(".librarything.com")
                .expiresOn(date)
                .isSecure(true)
                .isHttpOnly(true)
                .sameSite("None")
                .build();

        driver.manage().addCookie(cookie);
    }
}