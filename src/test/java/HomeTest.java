import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.pageobjects.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeTest {

    HomePage homePage = new HomePage();
    Logger log = LogManager.getRootLogger();

    @BeforeEach
    public void setUp() {
        homePage.openHomePage();
    }

    @Test //validam titlul paginii si adresa
    @DisplayName("Validate title test")
    public void validateTitle() {
        log.info("Validate title");
        String title = homePage.homePageTitle();
        assertEquals("LibraryThing | Catalog your books online | LibraryThing", title, "Title of page was: " + title);
    }

    @Test
    @DisplayName("Validate banner image test")
    public void validateBanner() {
        log.info("Validate home page banner");
        boolean isBannerDisplayed = homePage.isBannerDisplayed();
        boolean isBannerEnabled = homePage.isBannerEnabled();
        assertTrue(isBannerDisplayed, "Image is not displayed");
        assertTrue(isBannerEnabled, "Image is not enabled"); //se foloseste la butoane mai mult, verifici daca butonul este activ
        //intr-un test se pot verifica mai multe asserturi
    }

    @Test
    @DisplayName("Validate Recent Activity books test")
    public void validateBooks() {
        log.info("Validate Recent Activity books");
        List<WebElement> listOfRecentBooks = homePage.recentBooksDisplayed();
        assertNotNull(listOfRecentBooks, "List of books is null");
        assertEquals(8, listOfRecentBooks.size(), "Number of books is: " + listOfRecentBooks.size());
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        BrowserManager.closeDriver();
    }
}
