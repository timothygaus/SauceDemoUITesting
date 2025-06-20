package pages.components;

import framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

import static framework.utils.WebElementUtils.click;

public class MenuComponent {

    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public MenuComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait"))));
        PageFactory.initElements(webDriver, this);
    }

    // Note that the About link and Reset App State link are outside the scope of testing for this project,
    // selectors are still included for completeness.

    @FindBy(css = "div.bm-menu-wrap")
    WebElement bmMenuWrap;

    @FindBy(css = "div.bm-menu")
    WebElement bmMenu;

    @FindBy(id = "react-burger-menu-btn")
    WebElement closeButton;

    @FindBy(id = "inventory_sidebar_link")
    WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    WebElement aboutLink;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppStateLink;

    public WebElement getBmMenuWrap() {return bmMenuWrap;}
    public WebElement getBmMenu() {return bmMenu;}
    public WebElement getCloseButton() {return closeButton;}
    public WebElement getAllItemsLink() {return allItemsLink;}
    public WebElement getAboutLink() {return aboutLink;}
    public WebElement getLogoutLink() {return logoutLink;}
    public WebElement getResetAppStateLink() {return resetAppStateLink;}

    public void clickCloseButton() {
        click(webDriver, getCloseButton(), this::isMenuHidden);
    }

    /**
     * Clicks the All Items option on the menu and navigates to the inventory page
     * @return InventoryPage
     */
    public InventoryPage clickAllItems() {
        click(webDriver, getAllItemsLink(), () -> new InventoryPage(webDriver).isPageLoaded());
        return new InventoryPage(webDriver);
    }

    /**
     * Clicks the Logout option on the menu and navigates to the login page
     * @return LoginPage
     */
    public LoginPage clickLogout() {
        click(webDriver, getLogoutLink(), () -> new LoginPage(webDriver).isPageLoaded());
        return new LoginPage(webDriver);
    }

    /**
     * Checks to see if the side menu is hidden on the page
     * @return true if the menu is hidden, false otherwise
     */
    public boolean isMenuHidden() {
        try {
            return wait.until(ExpectedConditions.domAttributeToBe(getBmMenuWrap(), "aria-hidden", "true"));
        } catch (Exception e) {
            return false;
        }
    }
}
