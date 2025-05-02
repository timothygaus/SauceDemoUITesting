package pages;

import framework.base.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InventoryPage extends BasePage {

    //private String inventoryPageUrl = "https://www.saucedemo.com/inventory.html";

    public InventoryPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

    @FindBy(css = "div.shopping_cart_container")
    WebElement shoppingCartContainer;

    @FindBy(css = "div.app_logo")
    WebElement appLogo;

    @FindBy(css = "span.title")
    WebElement headerSecondaryContainerTitle; //TODO: might rename this to something shorter

    @FindBy(css = "div.inventory_container")
    WebElement inventoryContainer;

    @FindBy(css = "div.inventory_list")
    WebElement inventoryList;

    @FindBy(css = "div.inventory_item_name")
    List<WebElement> inventoryItemNames;

    @FindBy(css = "div.bm-menu")
    WebElement burgerMenu;

    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getAppLogo() {return appLogo;}
    public WebElement getHeaderSecondaryContainerTitle() {return headerSecondaryContainerTitle;}
    public WebElement getInventoryContainer() {return inventoryContainer;}
    public WebElement getInventoryList() {return inventoryList;}
    public List<WebElement> getInventoryItemNames() {return inventoryItemNames;}
    public WebElement getBurgerMenu() {return burgerMenu;}

    public CartPage clickCartButton() {
        getShoppingCartContainer().click();

        try {
            wait.until(ExpectedConditions.urlContains("cart"));
        } catch (TimeoutException e) {
            return null;
        }

        return new CartPage(webDriver);
    }

    public boolean isItemPresent (String itemName) {
        try {
            for(WebElement item : inventoryItemNames) {
                if(item.getText().equalsIgnoreCase(itemName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
