package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void initializeLoginTest() {
        webDriver.get(ConfigReader.getProperty("base.url"));
        loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Failed to load login page");
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

    @Test
    public void testLoginLogo() {
        Assert.assertTrue(loginPage.getLoginLogo().isDisplayed(), "Login logo was not displayed");
        Assert.assertEquals(loginPage.getLoginLogoValue(), "Swag Labs", "Expected login logo to be " +
                "Swag Labs, but got " + loginPage.getLoginLogoValue());
    }

    @Test
    public void testLoginPageKeyElements() {
        softAssert.assertTrue(loginPage.getUsernameInput().isDisplayed(), "Username input field was not displayed");
        softAssert.assertEquals(loginPage.getUsernameFieldValue(), "", "Expected Username field to be empty, " +
                "but got " + loginPage.getUsernameFieldValue());
        softAssert.assertTrue(loginPage.getPasswordInput().isDisplayed(), "Password input field was not displayed");
        softAssert.assertEquals(loginPage.getPasswordFieldValue(), "", "Expected password field to be empty, " +
                "but got " + loginPage.getPasswordFieldValue());
        softAssert.assertTrue(loginPage.getLoginButton().isDisplayed(), "Login button was not displayed");

        softAssert.assertAll();
    }

    @Test
    public void testUsernameInputFieldFunctionality() {
        String username = "standard_user";
        loginPage.enterUsername(username);
        Assert.assertEquals(loginPage.getUsernameFieldValue(), username, "Unable to enter " + username + " into" +
                " the username input field");
    }

    @Test
    public void testPasswordInputFieldFunctionality() {
        String password = "secret_sauce";
        loginPage.enterPassword(password);
        Assert.assertEquals(loginPage.getPasswordFieldValue(), password, "Unable to enter " + password + " into " +
                "the password input field");
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLoginWithLoginButton(String username, String password) {
        InventoryPage inventoryPage = loginPage.login(username, password);
        Assert.assertNotNull(inventoryPage, "Login with login button failed or did not redirect to inventory page");
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLoginWithEnterKey(String username, String password) {
        InventoryPage inventoryPage = loginPage.login(username, password, false);
        Assert.assertNotNull(inventoryPage, "Login with enter key failed or did not redirect to inventory page");
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        String username = "standard_user";
        String password = "incorrect_password";
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginErrorMessageVisible(), "Error message was not displayed for locked " +
                "out user when login was attempted");

        String errorMessageActual = loginPage.getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.getInvalidLoginErrorText(), "Expected error message " +
                "text to be " + loginPage.getInvalidLoginErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testLoginWithLockedOutUser() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginErrorMessageVisible(), "Error message was not displayed for locked " +
                "out user when login was attempted");

        String errorMessageActual = loginPage.getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.getLockedOutUserLoginErrorText(),
                "Expected error message after locked out user attempts login to say " +
                        loginPage.getLockedOutUserLoginErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testLoginWithMissingUsername() {
        String username = "";
        String password = "secret_sauce";
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginErrorMessageVisible(), "Error message was not displayed for invalid " +
                "user when login was attempted");

        String errorMessageActual = loginPage.getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.getMissingUsernameErrorText(), "Expected error message " +
                "text to be " + loginPage.getMissingUsernameErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testLoginWithMissingPassword() {
        String username = "standard_user";
        String password = "";
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginErrorMessageVisible(), "Error message was not displayed for invalid " +
                "user when login was attempted");

        String errorMessageActual = loginPage.getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.getMissingPasswordErrorText(), "Expected error message " +
                "text to be " + loginPage.getMissingPasswordErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testErrorMessageElementsHiddenOnInitialization() {
        softAssert.assertFalse(loginPage.isLoginErrorMessageVisible(), "Error message was displayed on page " +
                "initialization unexpectedly");
        softAssert.assertFalse(loginPage.isUsernameErrorIconVisible(), "Username field error icon was " +
                "displayed on page initialization unexpectedly");
        softAssert.assertFalse(loginPage.isPasswordErrorIconVisible(), "Password field error icon was " +
                "displayed on page initialization unexpectedly");

        softAssert.assertAll();
    }

    @Test()
    public void testErrorUiElementsAppearOnFailedLogin() {
        String username = "standard_user";
        String password = "incorrect_password";
        loginPage.login(username, password);

        softAssert.assertTrue(loginPage.isLoginErrorMessageVisible(), "The error message was not displayed after a " +
                "failed login attempt");
        softAssert.assertTrue(loginPage.isUsernameErrorIconVisible(), "The error icon was not displayed " +
                "next to the username input field after a failed login attempt");
        softAssert.assertTrue(loginPage.isPasswordErrorIconVisible(), "The error icon was not displayed " +
                "next to the password input field after a failed login attempt");

        softAssert.assertAll();
    }

    @Test
    public void testClosingErrorMessage() {
        String username = "standard_user";
        String password = "incorrect_password";
        loginPage.login(username, password);
        loginPage.clickErrorMessageButton();

        softAssert.assertFalse(loginPage.isLoginErrorMessageVisible(), "Error message was still displayed after " +
                "clicking the error message close button");
        softAssert.assertFalse(loginPage.isUsernameErrorIconVisible(), "Error icon was still displayed " +
                "next to the username input field after clicking the error message close button");
        softAssert.assertFalse(loginPage.isPasswordErrorIconVisible(), "Error icon was still displayed " +
                "next to the password input field after clicking the error message close button");
    }

}