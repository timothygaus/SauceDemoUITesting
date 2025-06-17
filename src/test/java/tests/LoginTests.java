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

    private static final ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    @BeforeMethod
    public void initializeLoginTest() {
        getWebdriver().get(ConfigReader.getProperty("base.url"));
        loginPage.set(new LoginPage(getWebdriver()));
        softAssert.set(new SoftAssert());
        Assert.assertTrue(loginPage.get().isPageLoaded(), "Failed to load login page");
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
        Assert.assertTrue(loginPage.get().getLoginLogo().isDisplayed(), "Login logo was not displayed");
        Assert.assertEquals(loginPage.get().getLoginLogoValue(), "Swag Labs", "Expected login logo to be " +
                "Swag Labs, but got " + loginPage.get().getLoginLogoValue());
    }

    @Test
    public void testLoginPageKeyElements() {
        softAssert.get().assertTrue(loginPage.get().getUsernameInput().isDisplayed(), "Username input field was not displayed");
        softAssert.get().assertEquals(loginPage.get().getUsernameFieldValue(), "", "Expected Username field to be empty, " +
                "but got " + loginPage.get().getUsernameFieldValue());
        softAssert.get().assertTrue(loginPage.get().getPasswordInput().isDisplayed(), "Password input field was not displayed");
        softAssert.get().assertEquals(loginPage.get().getPasswordFieldValue(), "", "Expected password field to be empty, " +
                "but got " + loginPage.get().getPasswordFieldValue());
        softAssert.get().assertTrue(loginPage.get().getLoginButton().isDisplayed(), "Login button was not displayed");

        softAssert.get().assertAll();
    }

    @Test
    public void testUsernameInputFieldFunctionality() {
        String username = "standard_user";
        loginPage.get().enterUsername(username);
        Assert.assertEquals(loginPage.get().getUsernameFieldValue(), username, "Unable to enter " + username + " into" +
                " the username input field");
    }

    @Test
    public void testPasswordInputFieldFunctionality() {
        String password = "secret_sauce";
        loginPage.get().enterPassword(password);
        Assert.assertEquals(loginPage.get().getPasswordFieldValue(), password, "Unable to enter " + password + " into " +
                "the password input field");
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLoginWithLoginButton(String username, String password) {
        InventoryPage inventoryPage = loginPage.get().login(username, password);
        Assert.assertNotNull(inventoryPage, "Login with login button failed or did not redirect to inventory page");
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLoginWithEnterKey(String username, String password) {
        InventoryPage inventoryPage = loginPage.get().login(username, password, false);
        Assert.assertNotNull(inventoryPage, "Login with enter key failed or did not redirect to inventory page");
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        String username = "standard_user";
        String password = "incorrect_password";
        loginPage.get().login(username, password);
        Assert.assertTrue(loginPage.get().isLoginErrorMessageVisible(), "Error message was not displayed for locked " +
                "out user when login was attempted");

        String errorMessageActual = loginPage.get().getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.get().getInvalidLoginErrorText(), "Expected error message " +
                "text to be " + loginPage.get().getInvalidLoginErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testLoginWithLockedOutUser() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        loginPage.get().login(username, password);
        Assert.assertTrue(loginPage.get().isLoginErrorMessageVisible(), "Error message was not displayed for locked " +
                "out user when login was attempted");

        String errorMessageActual = loginPage.get().getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.get().getLockedOutUserLoginErrorText(),
                "Expected error message after locked out user attempts login to say " +
                        loginPage.get().getLockedOutUserLoginErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testLoginWithMissingUsername() {
        String username = "";
        String password = "secret_sauce";
        loginPage.get().login(username, password);
        Assert.assertTrue(loginPage.get().isLoginErrorMessageVisible(), "Error message was not displayed for invalid " +
                "user when login was attempted");

        String errorMessageActual = loginPage.get().getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.get().getMissingUsernameErrorText(), "Expected error message " +
                "text to be " + loginPage.get().getMissingUsernameErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testLoginWithMissingPassword() {
        String username = "standard_user";
        String password = "";
        loginPage.get().login(username, password);
        Assert.assertTrue(loginPage.get().isLoginErrorMessageVisible(), "Error message was not displayed for invalid " +
                "user when login was attempted");

        String errorMessageActual = loginPage.get().getErrorMessageText();
        Assert.assertEquals(errorMessageActual, loginPage.get().getMissingPasswordErrorText(), "Expected error message " +
                "text to be " + loginPage.get().getMissingPasswordErrorText() + " but got " + errorMessageActual);
    }

    @Test
    public void testErrorMessageElementsHiddenOnInitialization() {
        softAssert.get().assertFalse(loginPage.get().isLoginErrorMessageVisible(), "Error message was displayed on page " +
                "initialization unexpectedly");
        softAssert.get().assertFalse(loginPage.get().isUsernameErrorIconVisible(), "Username field error icon was " +
                "displayed on page initialization unexpectedly");
        softAssert.get().assertFalse(loginPage.get().isPasswordErrorIconVisible(), "Password field error icon was " +
                "displayed on page initialization unexpectedly");

        softAssert.get().assertAll();
    }

    @Test()
    public void testErrorUiElementsAppearOnFailedLogin() {
        String username = "standard_user";
        String password = "incorrect_password";
        loginPage.get().login(username, password);

        softAssert.get().assertTrue(loginPage.get().isLoginErrorMessageVisible(), "The error message was not displayed after a " +
                "failed login attempt");
        softAssert.get().assertTrue(loginPage.get().isUsernameErrorIconVisible(), "The error icon was not displayed " +
                "next to the username input field after a failed login attempt");
        softAssert.get().assertTrue(loginPage.get().isPasswordErrorIconVisible(), "The error icon was not displayed " +
                "next to the password input field after a failed login attempt");

        softAssert.get().assertAll();
    }

    @Test
    public void testClosingErrorMessage() {
        String username = "standard_user";
        String password = "incorrect_password";
        loginPage.get().login(username, password);
        loginPage.get().clickErrorMessageButton();

        softAssert.get().assertFalse(loginPage.get().isLoginErrorMessageVisible(), "Error message was still displayed after " +
                "clicking the error message close button");
        softAssert.get().assertFalse(loginPage.get().isUsernameErrorIconVisible(), "Error icon was still displayed " +
                "next to the username input field after clicking the error message close button");
        softAssert.get().assertFalse(loginPage.get().isPasswordErrorIconVisible(), "Error icon was still displayed " +
                "next to the password input field after clicking the error message close button");
    }

}