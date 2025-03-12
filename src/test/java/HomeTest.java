import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.automation.pageobjects.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
    @DisplayName("Validate Recent Activity books & recommendations results test")
    public void validateBooks() {
        log.info("Validate the number of Recent Activity books & recommendations results");
        List<WebElement> listOfRecentBooks = homePage.recentBooksDisplayed();
        assertNotNull(listOfRecentBooks, "List of books is null");
        assertEquals(8, listOfRecentBooks.size(), "Number of books is: " + listOfRecentBooks.size());
    }

    @Test
    @DisplayName("Validate search returns 3 books matching the input author")
    public void searchByAuthorTest() throws InterruptedException {
        String expectedAuthor = "Baek Sehee";
        String searchResults = homePage.searchAuthorFromHomePage(expectedAuthor, 20);

        assertNotNull(searchResults, "Search results are empty.");

        // Split results if they are stored in a string format
        String[] books = searchResults.split("\n"); // Adjust based on how results are structured
        assertEquals(3, books.length, "Expected 3 book results, but found: " + books.length);

        // Validate each book result contains the expected author name
        for (String book : books) {
            assertTrue(book.contains(expectedAuthor), "Book author mismatch: " + book);
        }
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        BrowserManager.closeDriver();
    }
}
