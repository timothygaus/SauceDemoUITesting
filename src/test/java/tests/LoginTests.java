package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);
    private LoginPage loginPage;

    @BeforeMethod
    public void initializeLoginTest() {
        webDriver.get(ConfigReader.getProperty("base.url"));
        loginPage = new LoginPage(webDriver);
    }

    @DataProvider(name = "validLoginData")
    public static Object[][] provideValidLoginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public static Object [][] provideInvalidLoginData() {
        return new Object[][] {
                {"locked_out_user", "secret_sauce"},
                {"standard_user", "wrong_password"},
                {"", ""}
        };
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLogin(String username, String password) {
        loginPage.login(username, password);

        Assert.assertTrue(webDriver.getCurrentUrl().contains("inventory"), "Login with enter key failed or did not redirect to inventory");
    }

    @Test(dataProvider = "validLoginData")
    public void testLoginButton(String username, String password) {
        loginPage.login(username, password);

        Assert.assertTrue(webDriver.getCurrentUrl().contains("inventory"), "Login with button failed or did not redirect to inventory");
    }

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(String username, String password) {
        loginPage.login(username, password, false);

        Assert.assertTrue(loginPage.waitForErrorToBeVisible(), "Error message was not displayed for invalid user");
    }

    @Test
    public void testUsernameInitialValue() {
        Assert.assertEquals(loginPage.getUsernameFieldValue(), "", "Expected Username field to be empty, but got " + loginPage.getUsernameFieldValue());
    }

    @Test
    public void testPasswordInitialValue() {
        Assert.assertEquals(loginPage.getPasswordFieldValue(), "", "Expected Password field to be empty, but got " + loginPage.getPasswordFieldValue());
    }

    @Test
    public void testLoginLogoValue() {
        Assert.assertEquals(loginPage.getLoginLogoValue(), "Swag Labs", "Expected login logo to be Swag Labs, but got " + loginPage.getLoginLogoValue());
    }
}
