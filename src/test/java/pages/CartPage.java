package pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage (WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getContinueShoppingButton() {return continueShoppingButton;}

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(css = "div.shopping_cart_container")
    WebElement shoppingCartContainer;

    @FindBy(css = "a.shopping_cart_link")
    WebElement shoppingCartLink;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

    @FindBy(css = "div.cart_desc_label")
    WebElement cartDescriptionLabel;

    @FindBy(css = "div.cart_quantity_label")
    WebElement cartQuantityLabel;

    @FindBy(css = "div.app_logo")
    WebElement appLogo;

    @FindBy(css = "div.cart_item")
    List<WebElement> cartItems;

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
     * Gets the remove button WebElement for a given cart_item WebElement
     * @param cartItem cart_item WebElement
     * @return WebElement remove button
     */
    public WebElement getRemoveButtonElement(WebElement cartItem) {
        return cartItem.findElement(By.cssSelector("button.btn.btn_secondary.btn_small.cart_button"));
    }

    public InventoryPage clickContinueShoppingButton() {
        click(getContinueShoppingButton(), () -> webDriver.getCurrentUrl().contains("inventory"));
        return new InventoryPage(webDriver);
    }
}
