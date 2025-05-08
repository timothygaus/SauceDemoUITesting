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

    @FindBy(id = "user-name")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "div.login_logo")
    WebElement loginLogo;

    @FindBy(css = "div.error-message-container.error")
    WebElement errorMessage;

    @FindBy(css = "[data-test='error-button']")
    private WebElement errorButton;

    // Expected error messages for different failed login cases
    private static final String invalidLoginErrorText = "Epic sadface: Username and password do not match any user in this service";
    private static final String lockedOutUserLoginErrorText = "Epic sadface: Sorry, this user has been locked out.";
    private static final String missingUsernameErrorText = "Epic sadface: Username is required";
    private static final String missingPasswordErrorText = "Epic sadface: Password is required";

    public WebElement getUsernameInput() {return usernameInput;}
    public WebElement getPasswordInput() {return passwordInput;}
    public WebElement getLoginButton() {return loginButton;}
    public WebElement getLoginLogo() {return loginLogo;}
    public WebElement getErrorMessage() {return errorMessage;}
    public WebElement getErrorButton() {return errorButton;}
    public String getInvalidLoginErrorText() {return invalidLoginErrorText;}
    public String getLockedOutUserLoginErrorText() {return lockedOutUserLoginErrorText;}
    public String getMissingUsernameErrorText() {return missingUsernameErrorText;}
    public String getMissingPasswordErrorText() {return missingPasswordErrorText;}

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
            click(loginButton);
        } else {
            passwordInput.sendKeys(Keys.ENTER);
        }

        // short-circuit if the error message is displayed immediately
        try {
            WebElement errorElement = webDriver.findElement(By.cssSelector("h3[data-test='error']"));
            if (errorElement.isDisplayed()) {
                return null;
            }
        } catch (NoSuchElementException ignored) {
            // continue if the error is displayed or cannot be found
        }

        try {
            wait.until(ExpectedConditions.urlContains("inventory"));
        } catch (TimeoutException e) {
            return null;
        }

        return new InventoryPage(webDriver);
    }

    public boolean waitForErrorToBeVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
