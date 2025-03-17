import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.pageobjects.HomePage;
import org.junit.jupiter.api.*;
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

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag("regression")
    @Tag("ui")
    @DisplayName("Check visibility of Quotes section title")
    public void validateQuotesTitle() {
        log.info("Validate Quotes section title");
        String title = homePage.getQuotesTitle();
        assertEquals("Quotes", title, "Header of the section is: " + title);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @Tag("ui")
    @Tag("dynamic")
    @DisplayName("Verify Quotes section updates dynamically")
    public void validateQuotesUpdate() {
        log.info("Testing if the Quotes section updates dynamically");

        // Capture the initial dynamic element
        String initialQuote = homePage.getDynamicQuote();

        // Wait for a few seconds to allow the quote to potentially update
        try {
            Thread.sleep(12000); // Simple delay to let the dynamic change happen
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Capture the new quote text
        String updatedQuote = homePage.getDynamicQuote();

        // Check if the quote has changed
        boolean hasQuoteUpdated = !initialQuote.equals(updatedQuote);

        // Assert that the quote dynamically updates
        assertTrue(hasQuoteUpdated, "The Quotes section did not update dynamically as expected");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @Tag("search")
    @DisplayName("Validate search returns 3 books matching the input author")
    public void searchByAuthorTest () throws InterruptedException {
        String expectedAuthor = "Baek Se-hee";

        // Perform search
        homePage.searchAuthorFromHomePage(expectedAuthor, 10);
        homePage.closePopUp();
        // Wait for results to load
        homePage.waitForResultsToLoad();

        // Get all book results
        List<WebElement> bookResults = homePage.getBookResults();

        // Ensure results exist
        assertNotNull(bookResults, "Search results are empty.");
        assertTrue(bookResults.size() >= 3, "Expected at least 3 results, but found: " + bookResults.size());

        // Verify the first 3 results contain the exact author name
        for (int i = 0; i < 3; i++) { // Only checking first 3 books
            assertTrue(homePage.doesBookContainAuthor(bookResults.get(i), expectedAuthor),
                    "Book section does not contain the exact author name.");
        }
    }

    @AfterEach
    public void tearDown () throws InterruptedException {
        Thread.sleep(5000);
        BrowserManager.closeDriver();
    }
}