import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class YourBooksTest {

    YourBooksPage yourBooksPage = new YourBooksPage();
    Logger log = LogManager.getRootLogger();


    @BeforeAll
    public static void setUp() {
        HomePage homePage = new HomePage();
        SignInPopUp signIn = new SignInPopUp();
        homePage.openHomePage();
        signIn.signInAccount();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag("functional")
    @Tag("ui")
    @Tag("regression")
    @DisplayName("Add new book to Currently Reading collection and validate updated book count")
    public void validateAddingBookToCurrentlyRead() {
        log.info("Click on 'Want to Read' dropdown to add book");
        yourBooksPage.clickOnBookDropdown();
        yourBooksPage.addToCollection();
        yourBooksPage.openMyBooksPage();
        String isBookInCollectionText = yourBooksPage.isBookInCollection();
        assertEquals("Currently Reading (1)", isBookInCollectionText, "This collection is empty");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @Tag("functional")
    @DisplayName("Validate total book count in My Books")
    public void validateTotalBookCount() {
        log.info("Navigate to 'Your Books' page and validate book counts in dropdown collections");
        yourBooksPage.openMyBooksPage();

        // Validate Favorites category
        List<WebElement> allBooks = yourBooksPage.getAllBooks();
        assertEquals(19, allBooks.size(),
                "All books count mismatch: " + allBooks.size());
    }


    @AfterAll
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        BrowserManager.closeDriver();
    }
}