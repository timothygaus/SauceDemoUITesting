package pages;

import framework.base.BasePage;
import framework.utils.Urls;
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

    @FindBy(css = "#user-name + svg.error_icon")
    WebElement usernameErrorIcon;

    @FindBy(css = "#password + svg.error_icon")
    WebElement passwordErrorIcon;

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
    public WebElement getUsernameErrorIcon() {return usernameErrorIcon;}
    public WebElement getPasswordErrorIcon() {return passwordErrorIcon;}

    public String getInvalidLoginErrorText() {return INVALID_ERROR_LOGIN_TEXT;}
    public String getLockedOutUserLoginErrorText() {return LOCKED_OUT_USER_LOGIN_ERROR_TEXT;}
    public String getMissingUsernameErrorText() {return MISSING_USERNAME_ERROR_TEXT;}
    public String getMissingPasswordErrorText() {return MISSING_PASSWORD_ERROR_TEXT;}

    public String getUsernameFieldValue() {return usernameInput.getAttribute("value");}
    public String getPasswordFieldValue() {return passwordInput.getAttribute("value");}
    public String getLoginLogoValue() {return loginLogo.getText();}
    public String getErrorMessageText() {return errorMessage.getText();}

    /**
     * Checks that the login page is loaded by checking the URL and key WebElements are displayed
     * @return boolean true if the URL contains the LOGIN_PAGE_URL, the login logo is displayed, the username input is
     * displayed, the password input is displayed, and the login button is displayed
     */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains(Urls.LOGIN_PAGE_URL));
            wait.until(ExpectedConditions.visibilityOf(getLoginLogo()));
            wait.until(ExpectedConditions.visibilityOf(getUsernameInput()));
            wait.until(ExpectedConditions.visibilityOf(getPasswordInput()));
            wait.until(ExpectedConditions.visibilityOf(getLoginButton()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Enters a string into the username input field
     * @param username String
     */
    public void enterUsername(String username) {
        type(getUsernameInput(), username);
    }

    /**
     * Enters a string into the password input field
     * @param password String
     */
    public void enterPassword(String password) {
        type(getPasswordInput(), password);
    }

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
        enterUsername(username);
        enterPassword(password);

        if (useLoginButton) {
            click(getLoginButton(), () -> webDriver.getCurrentUrl().contains("inventory"));
        } else {
            getPasswordInput().sendKeys(Keys.ENTER);
        }

        return new InventoryPage(webDriver);
    }

    /**
     * Checks if the error message displayed after a failed login is visible
     * @return true if the error web element is visible, false if it is not visible
     */
    public boolean isLoginErrorMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getErrorMessage()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clicks the X icon on the error message to close it
     */
    public void clickErrorMessageButton() {
        try {
            click(getErrorButton(), () -> !isLoginErrorMessageVisible());
        } catch (Exception ignored) {

        }
    }

    /**
     * Checks if the error icon is visible next to the username input field
     * @return true if visible
     */
    public boolean isUsernameErrorIconVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getUsernameErrorIcon()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Checks if the error icon is visible next to the password input field
     * @return true if visible
     */
    public boolean isPasswordErrorIconVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getPasswordErrorIcon()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
