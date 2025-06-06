package pages;

import framework.base.BasePage;
import framework.utils.Urls;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.MenuComponent;

public class CheckoutStepOnePage extends BasePage {

    public CheckoutStepOnePage (WebDriver webDriver) {
        super(webDriver);
        this.menuComponent = new MenuComponent(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "div.app_logo")
    WebElement appLogo;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

    @FindBy(css = "div.shopping_cart_container")
    WebElement shoppingCartContainer;

    @FindBy(css = "span.shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(css = "span.title")
    WebElement secondaryHeaderTitle;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(css = "div.error")
    WebElement errorMessage;

    @FindBy(css = "[data-test='error-button']")
    WebElement errorMessageButton;

    @FindBy(css = "#first-name + svg.error_icon")
    WebElement firstNameErrorIcon;

    @FindBy(css = "#last-name + svg.error_icon")
    WebElement lastNameErrorIcon;

    @FindBy(css = "#postal-code + svg.error_icon")
    WebElement postalCodeErrorIcon;

    private final MenuComponent menuComponent;

    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getAppLogo() {return appLogo;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getFirstNameField() {return firstNameField;}
    public WebElement getLastNameField() {return lastNameField;}
    public WebElement getPostalCodeField() {return postalCodeField;}
    public WebElement getCancelButton() {return cancelButton;}
    public WebElement getContinueButton() {return continueButton;}
    public WebElement getErrorMessage() {return errorMessage;}
    public WebElement getErrorMessageButton() {return errorMessageButton;}
    public WebElement getFirstNameErrorIcon() {return firstNameErrorIcon;}
    public WebElement getLastNameErrorIcon() {return lastNameErrorIcon;}
    public WebElement getPostalCodeErrorIcon() {return postalCodeErrorIcon;}
    public MenuComponent getMenuComponent() {return menuComponent;}

    /**
     * Check that the checkout step one page is loaded by checking the URL and key WebElements are displayed.
     * @return true if the URL contains CHECKOUT_STEP_ONE_PAGE, the first name field is displayed, the last name field
     * is displayed, the postal code field is displayed, and the continue button is displayed.
     */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains(Urls.CHECKOUT_STEP_ONE_PAGE));
            wait.until(ExpectedConditions.visibilityOf(getFirstNameField()));
            wait.until(ExpectedConditions.visibilityOf(getLastNameField()));
            wait.until(ExpectedConditions.visibilityOf(getPostalCodeField()));
            wait.until(ExpectedConditions.visibilityOf(getContinueButton()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clicks the cancel button and navigates to the inventory page
     * @return InventoryPage
     */
    public InventoryPage clickCancelButton() {
        click(getCancelButton(), () -> webDriver.getCurrentUrl().contains("inventory"));
        return new InventoryPage(webDriver);
    }

    /**
     * Clicks the continue button and navigates to the checkout step two page
     * @return CheckoutStepTwoPage
     */
    public CheckoutStepTwoPage clickContinueButton() {
        click(getContinueButton(), () -> webDriver.getCurrentUrl().contains("checkout-step-two"));
        return new CheckoutStepTwoPage(webDriver);
    }
}
