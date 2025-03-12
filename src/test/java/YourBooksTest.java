import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.pageobjects.HomePage;
import org.automation.pageobjects.SignInPopUp;
import org.automation.pageobjects.YourBooksPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class YourBooksTest {
    HomePage homePage = new HomePage();
    SignInPopUp signIn = new SignInPopUp();
    YourBooksPage yourBooksPage = new YourBooksPage();
    Logger log = LogManager.getRootLogger();

    @BeforeEach
    public void setUp() {
        homePage.openHomePage();
        signIn.signInLibrary();
    }

    @Test
    @DisplayName("Validate book counts in Favorites and All collections dropdown")
    public void validateBookCounts() {
        log.info("Navigate to 'Your Books' page and validate book counts in dropdown collections");

        // Navigate to Your Books page
        yourBooksPage.openYourBooksPage();

        // Validate Favorites category
        log.info("Validating 'Favorites' category");
        List<WebElement> favoriteBooks = yourBooksPage.selectCategory("Favorites");
        assertNotNull(favoriteBooks, "Favorites book list is null");
        assertEquals(3, favoriteBooks.size(),
                "Favorites book count mismatch: " + favoriteBooks.size());

        // Validate All collections
        log.info("Validating 'All collections' category");
        List<WebElement> allBooks = yourBooksPage.selectCategory("All collections");
        assertNotNull(allBooks, "All collections book list is null");
        assertEquals(6, allBooks.size(),
                "Total book count mismatch: " + allBooks.size());
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        BrowserManager.closeDriver();
    }
}