package pages;

import framework.base.BasePage;
import framework.utils.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.MenuComponent;

import java.util.List;

import static framework.utils.WebElementUtils.click;

public class CartPage extends BasePage {

    public CartPage (WebDriver webDriver) {
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

    @FindBy(css = "span.title")
    WebElement secondaryHeaderTitle;

    @FindBy(css = "div.cart_quantity_label")
    WebElement cartQuantityLabel;

    @FindBy(css = "div.cart_desc_label")
    WebElement cartDescriptionLabel;

    @FindBy(id = "cart_contents_container")
    WebElement cartContentsContainer;

    @FindBy(css = "div.cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    private final MenuComponent menuComponent;

    public WebElement getContinueShoppingButton() {return continueShoppingButton;}
    public WebElement getCheckoutButton() {return checkoutButton;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartLink() {return shoppingCartLink;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getCartDescriptionLabel() {return cartDescriptionLabel;}
    public WebElement getCartContentsContainer() {return cartContentsContainer;}
    public WebElement getCartQuantityLabel() {return cartQuantityLabel;}
    public WebElement getAppLogo() {return appLogo;}
    public List<WebElement> getCartItems() {return cartItems;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public MenuComponent getMenuComponent() {return menuComponent;}

    /**
     * Checks that the cart page is loaded by checking the URL and key WebElements are displayed.
     * @return true if the URL contains CART_PAGE, the cart contents container is displayed, the continue shopping
     * button is displayed, and the checkout button is displayed.
     */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains(Urls.CART_PAGE));
            wait.until(ExpectedConditions.visibilityOf(getCartContentsContainer()));
            wait.until(ExpectedConditions.visibilityOf(getContinueShoppingButton()));
            wait.until(ExpectedConditions.visibilityOf(getCheckoutButton()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Gets the cart_item WebElement for a given item name
     * @param itemName String name of the item
     * @return WebElement cart_item
     */
    public WebElement getCartItemByName(String itemName) {
        String xpath = "//div[@class='cart_item' and .//div[@class='inventory_item_name' and text()='" + itemName + "']]";
        return webDriver.findElement(By.xpath(xpath));
    }

    /**
     * Gets the cart_quantity WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement cart_quantity
     */
    public WebElement getCartQuantityElement(WebElement cartItem) {
        return cartItem.findElement(By.cssSelector("div.cart_quantity"));
    }

    /**
     * Gets the inventory_item_name WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement inventory_item_name
     */
    public WebElement getInventoryItemNameElement(WebElement cartItem) {
        return cartItem.findElement(By.cssSelector("div.inventory_item_name"));
    }

    /**
     * Gets the inventory_item_desc WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement inventory_item_desc
     */
    public WebElement getInventoryItemDescriptionElement(WebElement cartItem) {
        return cartItem.findElement(By.cssSelector("div.inventory_item_desc"));
    }

    /**
     * Gets the inventory_item_quantity WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement inventory_item_quantity
     */
    public WebElement getInventoryItemQuantityElement(WebElement cartItem) {
        return cartItem.findElement(By.cssSelector("div.cart_quantity"));
    }

    /**
     * Gets the inventory_item_price WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement inventory_item_price
     */
    public WebElement getInventoryItemPriceElement(WebElement cartItem) {
        return  cartItem.findElement(By.cssSelector("div.inventory_item_price"));
    }

    /**
     * Gets the remove button WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement remove button
     */
    public WebElement getRemoveButtonElement(WebElement cartItem) {
        return cartItem.findElement(By.cssSelector("button.btn.btn_secondary.btn_small.cart_button"));
    }

    /**
     * Clicks the burger menu button and opens the burger menu
     */
    public void clickBurgerMenuButton() {
        click(webDriver, burgerMenuBtn, () -> !getMenuComponent().isMenuHidden());
    }

    /**
     * Clicks the continue shopping button and navigates to the Inventory page
     * @return InventoryPage
     */
    public InventoryPage clickContinueShoppingButton() {
        click(webDriver, getContinueShoppingButton(), () -> new InventoryPage(webDriver).isPageLoaded());
        return new InventoryPage(webDriver);
    }

    /**
     * Clicks the checkout button and navigates to the checkout step one page
     * @return CheckoutStepOnePage
     */
    public CheckoutStepOnePage clickCheckoutButton() {
        click(webDriver, getCheckoutButton(), () -> new CheckoutStepOnePage(webDriver).isPageLoaded());
        return new CheckoutStepOnePage(webDriver);
    }

    /**
     * Clicks the remove button for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     */
    public void clickRemoveItemButton(WebElement cartItem) {
        click(webDriver, getRemoveButtonElement(cartItem), () -> !cartItem.isDisplayed());
    }

    /**
     * Clicks the inventory_item_name for a given cart_item WebElement
     * and navigates to the associated InventoryItemPage
     * @param cartItem WebElement
     * @return InventoryItemPage
     */
    public InventoryItemPage clickInventoryItemName(WebElement cartItem) {
        click(webDriver, getInventoryItemNameElement(cartItem), () -> new InventoryItemPage(webDriver).isPageLoaded());
        return new InventoryItemPage(webDriver);
    }

    public boolean isShoppingCartBadgePresent() {
        try {
            return getShoppingCartBadge().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the integer value of the shopping cart badge. If the badge is not present, returns 0.
     * @return int value of shopping cart badge, or 0 if not present
     */
    public int getShoppingCartBadgeValue() {
        if (isShoppingCartBadgePresent()) {
            return Integer.parseInt(getShoppingCartBadge().getText());
        } else {
            return 0;
        }
    }
}
