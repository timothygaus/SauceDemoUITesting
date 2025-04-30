package pages;

import framework.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    /**
     * Enters values into the username and password fields and attempts to log in
     * Defaults useLoginButton to true
     *
     * @param username String
     * @param password String
     */
    public void login(String username, String password) {
        login(username, password, true);
    }

    /**
     * Enters values into the username and password fields and attempts to log in
     * @param username String
     * @param password String
     * @param useLoginButton Boolean, set to true to use login button, set to false to use enter key
     */
    public void login(String username, String password, boolean useLoginButton) {
        type(usernameInput, username);
        type(passwordInput, password);
        if (useLoginButton) {
            click(loginButton);
        } else {
            passwordInput.sendKeys(Keys.ENTER);
        }
    }

    public boolean waitForErrorToBeVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getUsernameFieldValue() {
        return usernameInput.getText();
    }

    public String getPasswordFieldValue() {
        return passwordInput.getText();
    }

    public String getLoginLogoValue() {
        return loginLogo.getText();
    }
}
