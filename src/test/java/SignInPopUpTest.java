import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.pageobjects.CreateAccountPage;
import org.automation.pageobjects.HomePage;
import org.automation.pageobjects.SignInPopUp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignInPopUpTest {

    HomePage homePage = new HomePage();
    SignInPopUp signIn = new SignInPopUp();
    Logger log = LogManager.getRootLogger();

    @BeforeEach
    public void setUp() {
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Sign in & Check existence of left navigation web elements test & Sign Out")
    public void signInTest() {
        signIn.signInLibrary();
        List<WebElement> browseOverviewHeadersList = signIn.browseOverviewHeadersList();
        List<WebElement> browseOverviewYourWorldList = signIn.browseOverviewYourWorldList();
        List<WebElement> browseOverviewDiscoverList = signIn.browseOverviewDiscoverList();
        List<String> actualHeaders = new ArrayList<>();
        List<String> expectedHeaders = List.of("Overview", "Your World", "Recent News", "Your Feed", "About You", "For You", "Discover", "Books", "Reviews", "Community", "Lists", "Helpers", "Folly", "Unused");

        for (WebElement element : browseOverviewHeadersList) {
            log.info(element.getText());
            actualHeaders.add(element.getText());
        }
        for (WebElement element : browseOverviewYourWorldList) {
            log.info(element.getText());
            actualHeaders.add(element.getText());
        }
        for (WebElement element : browseOverviewDiscoverList) {
            log.info(element.getText());
            actualHeaders.add(element.getText());
        }
        log.info(actualHeaders);
        assertEquals(expectedHeaders.size(), actualHeaders.size());

        signIn.signOutLibrary();
        assertEquals(BrowserManager.currentUrl(), "https://www.librarything.com/");
    }

    @Test
    @DisplayName("Verify sign in failure when adding a valid username but an invalid password")
    public void invalidSignInTest() {
        signIn.invalidSignInLibrary();
        assertTrue(signIn.isSignInFailing(), "Sign In should fail with invalid credentials");
        System.out.println("Sign In failed");
    }

  /*  @AfterEach
    public void tearDown() throws InterruptedException {
        BrowserManager.closeDriver();
    }*/
}