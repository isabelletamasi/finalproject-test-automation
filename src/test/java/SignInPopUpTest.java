import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.pageobjects.HomePage;
import org.automation.pageobjects.SignInPopUp;
import org.junit.jupiter.api.*;
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
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @Tag("functional")
    @DisplayName("Validate successful sign-in & visibility of Currently Reading header")
    public void signInTest() {
        signIn.signInAccount();
        //assertTrue(signIn.isMyBooksElementVisible(), "My Books tab is not there.");
        assertTrue(signIn.isCurrentlyReadingElementVisible(), "Currently Reading section is not there.");
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Tag("regression")
    @Tag("security")
    @DisplayName("Verify sign-in failure when using invalid credentials")
    public void invalidSignInTest() {
        signIn.invalidSignInAccount();
        assertTrue(signIn.isSignInFailing(), "Sign-In should fail with invalid credentials");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        BrowserManager.closeDriver();
    }
}