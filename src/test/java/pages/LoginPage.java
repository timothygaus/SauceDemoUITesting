package pages;

import framework.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "div.login_logo")
    WebElement loginLogo;

    @FindBy(id = "user-name")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "div.error-message-container.error")
    WebElement errorMessage;

    @FindBy(css = "[data-test='error-button']")
    WebElement errorButton;

//    TODO: Selectors for:
//    Username Error Icon
//    Password Error Icon

    // Expected error messages for different failed login cases
    private final String INVALID_ERROR_LOGIN_TEXT = "Epic sadface: Username and password do not match any user in this service";
    private final String LOCKED_OUT_USER_LOGIN_ERROR_TEXT = "Epic sadface: Sorry, this user has been locked out.";
    private final String MISSING_USERNAME_ERROR_TEXT = "Epic sadface: Username is required";
    private final String MISSING_PASSWORD_ERROR_TEXT = "Epic sadface: Password is required";

    public WebElement getUsernameInput() {return usernameInput;}
    public WebElement getPasswordInput() {return passwordInput;}
    public WebElement getLoginButton() {return loginButton;}
    public WebElement getLoginLogo() {return loginLogo;}
    public WebElement getErrorMessage() {return errorMessage;}
    public WebElement getErrorButton() {return errorButton;}

    public String getInvalidLoginErrorText() {return INVALID_ERROR_LOGIN_TEXT;}
    public String getLockedOutUserLoginErrorText() {return LOCKED_OUT_USER_LOGIN_ERROR_TEXT;}
    public String getMissingUsernameErrorText() {return MISSING_USERNAME_ERROR_TEXT;}
    public String getMissingPasswordErrorText() {return MISSING_PASSWORD_ERROR_TEXT;}

    public String getUsernameFieldValue() {return usernameInput.getText();}
    public String getPasswordFieldValue() {return passwordInput.getText();}
    public String getLoginLogoValue() {return loginLogo.getText();}
    public String getErrorMessageText() {return errorMessage.getText();}

    /**
     * Enters values into the username and password fields and attempts to log in
     * Defaults useLoginButton to true
     *
     * @param username String
     * @param password String
     */
    public InventoryPage login(String username, String password) {
        return login(username, password, true);
    }

    /**
     * Enters values into the username and password fields and attempts to log in
     * @param username String
     * @param password String
     * @param useLoginButton Boolean, set to true to use login button, set to false to use enter key
     */
    public InventoryPage login(String username, String password, boolean useLoginButton) {
        type(usernameInput, username);
        type(passwordInput, password);

        if (useLoginButton) {
            click(loginButton, () -> webDriver.getCurrentUrl().contains("inventory"));
        } else {
            passwordInput.sendKeys(Keys.ENTER);
        }

        return new InventoryPage(webDriver);
    }

    /**
     * Checks if the error message displayed after a failed login is visible
     * @return true if the error web element is visible, false if it is not visible
     */
    public boolean isLoginErrorMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
