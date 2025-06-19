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

import static framework.utils.WebElementUtils.click;
import static framework.utils.WebElementUtils.type;

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
     * Enters the first name into the first name field.
     * @param firstName String
     */
    public void enterFirstName(String firstName) {
        type(webDriver, getFirstNameField(), firstName);
    }

    /**
     * Enters the last name into the last name field.
     * @param lastName String
     */
    public void enterLastName(String lastName) {
        type(webDriver, getLastNameField(), lastName);
    }

    /**
     * Enters the postal code into the postal code field.
     * @param postalCode String
     */
    public void enterPostalCode(String postalCode) {
        type(webDriver, getPostalCodeField(), postalCode);
    }

    /**
     * Clicks the cancel button and navigates to the cart page
     * @return CartPage
     */
    public CartPage clickCancelButton() {
        click(webDriver, getCancelButton(), () -> new CartPage(webDriver).isPageLoaded());
        return new CartPage(webDriver);
    }

    /**
     * Clicks the continue button and navigates to the checkout step two page
     * @return CheckoutStepTwoPage
     */
    public CheckoutStepTwoPage clickContinueButton() {
        click(webDriver, getContinueButton(), () -> new CheckoutStepTwoPage(webDriver).isPageLoaded());
        return new CheckoutStepTwoPage(webDriver);
    }

    /**
     * Checks if the error message is displayed.
     * @return true if the error message is displayed, false otherwise.
     */
    public boolean isErrorMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getErrorMessage()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clicks the error message button to close the error message.
     */
    public void clickErrorMessageButton() {
        click(webDriver, getErrorMessageButton(), () -> !getErrorMessage().isDisplayed());
    }

    /**
     * Checks if the first name error icon is displayed.
     * @return true if the first name error icon is displayed, false otherwise.
     */
    public boolean isFirstNameErrorIconVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getFirstNameErrorIcon()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Checks if the last name error icon is displayed.
     * @return true if the last name error icon is displayed, false otherwise.
     */
    public boolean isLastNameErrorIconVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getLastNameErrorIcon()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Checks if the postal code error icon is displayed.
     * @return true if the postal code error icon is displayed, false otherwise.
     */
    public boolean isPostalCodeErrorIconVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getPostalCodeErrorIcon()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Helper function to set up the checkout step one page by adding an item to the cart and navigating to the checkout step one page.
     * @param inventoryPage InventoryPage
     * @return CheckoutStepOnePage
     */
    public static CheckoutStepOnePage setUpCheckoutStepOnePage(InventoryPage inventoryPage) {
        inventoryPage.clickAddToCartButton(inventoryPage.getFirstInventoryItem());
        CartPage cartPage = inventoryPage.clickCartButton();
        return cartPage.clickCheckoutButton();
    }
}
