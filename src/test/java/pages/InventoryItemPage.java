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

public class InventoryItemPage extends BasePage {

    public InventoryItemPage(WebDriver webDriver) {
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

    @FindBy(css = "a.shopping_cart_link")
    WebElement shoppingCartLink;

    @FindBy(css = "span.shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(css = "button.back")
    WebElement secondaryHeaderTitle;

    @FindBy(css = "div.inventory_details_name")
    WebElement inventoryDetailsName;

    @FindBy(css = ".inventory_details_img")
    WebElement inventoryDetailsImage;

    @FindBy(css = "div.inventory_details_desc")
    WebElement inventoryDetailsDescription;

    @FindBy(css = "div.inventory_details_price")
    WebElement inventoryDetailsPrice;

    @FindBy(id = "add-to-cart")
    WebElement addToCartButton;

    @FindBy(id = "remove")
    WebElement removeButton;

    private final MenuComponent menuComponent;

    public WebElement getAppLogo() {return appLogo;}
    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartLink() {return shoppingCartLink;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getInventoryDetailsName() {return inventoryDetailsName;}
    public WebElement getInventoryDetailsImage() {return inventoryDetailsImage;}
    public WebElement getInventoryDetailsDescription() {return inventoryDetailsDescription;}
    public WebElement getInventoryDetailsPrice() {return inventoryDetailsPrice;}
    public WebElement getAddToCartButton() {return addToCartButton;}
    public WebElement getRemoveButton() {return removeButton;}
    public MenuComponent getMenuComponent() {return menuComponent;}

    /**
     * Checks that the inventory item page is loaded by checking the URL and key WebElements are displayed.
     * @return true if the URL contains INVENTORY_ITEM_PAGE, the inventory details name is displayed, the inventory
     * details description is displayed, the inventory details price is displayed, and the add to cart button is
     * displayed.
     */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains(Urls.INVENTORY_ITEM_PAGE));
            wait.until(ExpectedConditions.visibilityOf(getInventoryDetailsName()));
            wait.until(ExpectedConditions.visibilityOf(getInventoryDetailsDescription()));
            wait.until(ExpectedConditions.visibilityOf(getInventoryDetailsPrice()));
            wait.until(ExpectedConditions.visibilityOf(getAddToCartButton()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clicks the cart button and navigates to the cart page
     * @return CartPage
     */
    public CartPage clickCartButton() {
        click(webDriver, shoppingCartLink, () -> new CartPage(webDriver).isPageLoaded());
        return new CartPage(webDriver);
    }

    /**
     * Clicks on the Back to Products button and waits for the InventoryPage to load.
     * @return InventoryPage instance after clicking the button.
     */
    public InventoryPage clickBackToProductsButton() {
        click(webDriver, getSecondaryHeaderTitle(),
                () -> new InventoryPage(webDriver).isPageLoaded());
        return new InventoryPage(webDriver);
    }

    /**
     * Clicks the add to cart button and waits for the remove button to be displayed.
     */
    public void clickAddToCartButton() {
        click(webDriver, addToCartButton, () -> getRemoveButton().isDisplayed());
    }

    /**
     * Clicks the remove button and waits for the add to cart button to be displayed.
     */
    public void clickRemoveButton() {
        click(webDriver, removeButton, () -> getAddToCartButton().isDisplayed());
    }

    /**
     * Gets the integer value of the shopping cart badge. If the badge is not present, returns 0.
     * @return int value of shopping cart badge, or 0 if not present
     */
    public int getShoppingCartBadgeValue() {
        try {
            return Integer.parseInt(getShoppingCartBadge().getText());
        } catch (Exception e) {
            return 0;
        }
    }
}
