package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);
    //private static final Logger log = LoggerFactory.getLogger(LoginTests.class);
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
                {"", ""},
                {"standard_user", ""},
                {"", "secret_sauce"}
        };
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLoginWithLoginButton(String username, String password) {
        InventoryPage inventoryPage = loginPage.login(username, password);
        Assert.assertNotNull(inventoryPage, "Login with login button failed or did not redirect to inventory page");
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLoginWithEnterKey(String username, String password) {
        InventoryPage inventoryPage = loginPage.login(username, password);
        Assert.assertNotNull(inventoryPage, "Login with enter key failed or did not redirect to inventory page");
    }

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(String username, String password) {
        loginPage.login(username, password, false);

        Assert.assertTrue(loginPage.waitForErrorToBeVisible(), "Error message was not displayed for invalid user when login was attempted");
    }

    @Test(dataProvider = "invalidLoginData") //TODO: not sure if testing all of these cases is necessary
    public void testErrorButton(String username, String password) {
        loginPage.login(username, password);
        loginPage.getErrorButton().click();

        Assert.assertFalse(loginPage.waitForErrorToBeVisible(), "Error message did not close after clicking the error button");
    }

    @Test
    public void testUsernameFieldVisibility() {
        Assert.assertTrue(loginPage.getUsernameInput().isDisplayed(), "Username input field was not displayed");
    }

    @Test
    public void testPasswordFieldVisibility() {
        Assert.assertTrue(loginPage.getPasswordInput().isDisplayed(), "Password field was not displayed");
    }

    @Test
    public void testLoginButtonVisibility() {
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed(), "Login button was not displayed");
    }

    @Test
    public void testLoginLogoVisibility() {
        Assert.assertTrue(loginPage.getLoginLogo().isDisplayed(), "Login logo was not displayed");
    }

    @Test
    public void testErrorMessageHidden() {
        Assert.assertFalse(loginPage.waitForErrorToBeVisible(), "Error message was displayed on page initialization unexpectedly");
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
