import org.automation.framework.BrowserManager;
import org.automation.pageobjects.CreateAccountPage;
import org.automation.pageobjects.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateAccountTest {
    HomePage homePage = new HomePage();
    CreateAccountPage createAccountPage = new CreateAccountPage();

    @BeforeEach
    public void setUp() {
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Validate Create Account input fields accessibility")
    public void validateCreateAccountFields() {
        createAccountPage.clickSignUpWithEmailButton();
        List<String> labelsText = createAccountPage.getAccountLabels();
        createAccountPage.clickCreateAccountButton();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        BrowserManager.closeDriver();
    }
}
