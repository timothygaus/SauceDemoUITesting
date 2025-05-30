package pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutStepTwoPage extends BasePage {

    public CheckoutStepTwoPage (WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

    @FindBy(css = "div.app_logo")
    WebElement appLogo;

    @FindBy(css = "div.shopping_cart_container")
    WebElement shoppingCartContainer;

    @FindBy(css = "span.shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(css = "span.title")
    WebElement secondaryHeaderTitle;

    @FindBy(css = "div.cart_desc_label")
    WebElement cartDescriptionLabel;

    @FindBy(css = "div.cart_quantity_label")
    WebElement cartQuantityLabel;

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

    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getAppLogo() {return appLogo;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getCartDescriptionLabel() {return cartDescriptionLabel;}
    public WebElement getCartQuantityLabel() {return cartQuantityLabel;}
    public List<WebElement> getCartItems() {return cartItems;}
    public WebElement getPaymentInfoLabel() {return paymentInfoLabel;}
    public WebElement getPaymentValueLabel() {return paymentValueLabel;}
    public WebElement getShippingInfoLabel() {return shippingInfoLabel;}
    public WebElement getShippingValueLabel() {return shippingValueLabel;}
    public WebElement getSubtotalLabel() {return subtotalLabel;}
    public WebElement getTaxLabel() {return taxLabel;}
    public WebElement getTotalLabel() {return totalLabel;}
    public WebElement getCancelButton() {return cancelButton;}
    public WebElement getFinishButton() {return finishButton;}

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
        click(getCancelButton(), () -> webDriver.getCurrentUrl().contains("inventory"));
        return new InventoryPage(webDriver);
    }

    /**
     * Clicks the finish button and navigates to the checkout complete page
     * @return CheckoutCompletePage
     */
    public CheckoutCompletePage clickFinishButton() {
        click(getFinishButton(), () -> webDriver.getCurrentUrl().contains("checkout-complete"));
        return new CheckoutCompletePage(webDriver);
    }
}
