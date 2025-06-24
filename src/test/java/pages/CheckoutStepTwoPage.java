package pages;

import framework.base.BasePage;
import framework.utils.InventoryItem;
import framework.utils.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.MenuComponent;

import java.util.ArrayList;
import java.util.List;

import static framework.utils.WebElementUtils.click;

public class CheckoutStepTwoPage extends BasePage {

    public CheckoutStepTwoPage (WebDriver webDriver) {
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

    @FindBy(id = "checkout_summary_container")
    WebElement checkoutSummaryContainer;

    @FindBy(css = "div.cart_quantity_label")
    WebElement cartQuantityLabel;

    @FindBy(css = "div.cart_desc_label")
    WebElement cartDescriptionLabel;

    @FindBy(css = "div.cart_item")
    List<WebElement> cartItems;

    @FindBy(css = "div.summary_info_label[data-test=payment-info-label]")
    WebElement paymentInfoLabel;

    @FindBy(css = "div.summary_value_label[data-test=payment-info-value]")
    WebElement paymentValueLabel;

    @FindBy(css = "div.summary_info_label[data-test=shipping-info-label]")
    WebElement shippingInfoLabel;

    @FindBy(css = "div.summary_value_label[data-test=shipping-info-value]")
    WebElement shippingValueLabel;

    @FindBy(css = "div.summary_info_label[data-test=total-info-label]")
    WebElement totalInfoLabel;

    @FindBy(css = "div.summary_subtotal_label")
    WebElement subtotalLabel;

    @FindBy(css = "div.summary_tax_label")
    WebElement taxLabel;

    @FindBy(css = "div.summary_total_label")
    WebElement totalLabel;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    private final MenuComponent menuComponent;

    private List<InventoryItem> addedCartItems;

    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getAppLogo() {return appLogo;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getCheckoutSummaryContainer() {return checkoutSummaryContainer;}
    public WebElement getCartDescriptionLabel() {return cartDescriptionLabel;}
    public WebElement getCartQuantityLabel() {return cartQuantityLabel;}
    public List<WebElement> getCartItems() {return cartItems;}
    public WebElement getPaymentInfoLabel() {return paymentInfoLabel;}
    public WebElement getPaymentValueLabel() {return paymentValueLabel;}
    public WebElement getShippingInfoLabel() {return shippingInfoLabel;}
    public WebElement getShippingValueLabel() {return shippingValueLabel;}
    public WebElement getTotalInfoLabel() {return totalInfoLabel;}
    public WebElement getSubtotalLabel() {return subtotalLabel;}
    public WebElement getTaxLabel() {return taxLabel;}
    public WebElement getTotalLabel() {return totalLabel;}
    public WebElement getCancelButton() {return cancelButton;}
    public WebElement getFinishButton() {return finishButton;}
    public MenuComponent getMenuComponent() {return menuComponent;}
    public List<InventoryItem> getAddedCartItems() {return addedCartItems;}

    public void setAddedCartItems(List<InventoryItem> addedCartItems) {this.addedCartItems = addedCartItems;}

    /**
     * Checks that the checkout step two page is loaded by checking the URL and key WebElements are displayed.
     * @return true if the URL contains CHECKOUT_STEP_TWO_PAGE, the checkout summary container is displayed, the total
     * label is displayed, and the finish button is displayed.
     */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains(Urls.CHECKOUT_STEP_TWO_PAGE));
            wait.until(ExpectedConditions.visibilityOf(getCheckoutSummaryContainer()));
            wait.until(ExpectedConditions.visibilityOf(getTotalLabel()));
            wait.until(ExpectedConditions.visibilityOf(getFinishButton()));
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
     * Gets the inventory_item_price WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement inventory_item_price
     */
    public WebElement getInventoryItemPriceElement(WebElement cartItem) {
        return  cartItem.findElement(By.cssSelector("div.inventory_item_price"));
    }

    /**
     * Clicks the cancel button and navigates to the inventory page
     * @return InventoryPage
     */
    public InventoryPage clickCancelButton() {
        click(webDriver, getCancelButton(), () -> new InventoryPage(webDriver).isPageLoaded());
        return new InventoryPage(webDriver);
    }

    /**
     * Clicks the finish button and navigates to the checkout complete page
     * @return CheckoutCompletePage
     */
    public CheckoutCompletePage clickFinishButton() {
        click(webDriver, getFinishButton(), () -> new CheckoutCompletePage(webDriver).isPageLoaded());
        return new CheckoutCompletePage(webDriver);
    }

    /**
     * Helper function to set up the CheckoutStepTwoPage for tests by adding an item to the cart, navigating to the cart,
     * filling out the checkout step one form, and then navigating to the checkout step two page. The contents of the cart
     * page are saved and passed to the CheckoutStepTwoPage.
     * @param inventoryPage InventoryPage
     * @return CheckoutStepTwoPage
     */
    public static CheckoutStepTwoPage setUpCheckoutStepTwoPage(InventoryPage inventoryPage) {
        inventoryPage.clickAddToCartButton(inventoryPage.getFirstInventoryItem());
        CartPage cartPage = inventoryPage.clickCartButton();
        List<InventoryItem> addedItems = cartPage.getCartItemsAsJsonObjects();
        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickCheckoutButton();
        checkoutStepOnePage.enterFirstName("John");
        checkoutStepOnePage.enterLastName("Doe");
        checkoutStepOnePage.enterPostalCode("12345");
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.clickContinueButton();
        checkoutStepTwoPage.setAddedCartItems(addedItems);
        return checkoutStepTwoPage;
    }

    /**
     * Extracts the cart items as InventoryItem objects from the cart items displayed on the checkout step two page.
     * @return List of InventoryItem objects
     */
    public List<InventoryItem> extractCartItemsAsJsonObjects() {
        List<InventoryItem> cartItemsList = new ArrayList<>();
        for(WebElement cartItem : getCartItems()) {;
            String itemName = getInventoryItemNameElement(cartItem).getText();
            String itemDescription = getInventoryItemDescriptionElement(cartItem).getText();
            String itemPrice = getInventoryItemPriceElement(cartItem).getText();
            InventoryItem inventoryItem = new InventoryItem(itemName, itemDescription, itemPrice);
            cartItemsList.add(inventoryItem);
        }
        return cartItemsList;
    }
}
