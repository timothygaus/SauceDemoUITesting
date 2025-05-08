package pages;

import framework.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(css = "a.shopping_cart_link")
    WebElement shoppingCartLink;

    @FindBy(css = "div.app_logo")
    WebElement appLogo;

    @FindBy(css = "span.title")
    WebElement secondaryHeaderTitle;

    @FindBy(css = "div.inventory_container")
    WebElement inventoryContainer;

    @FindBy(css = "div.inventory_list")
    WebElement inventoryList;

    @FindBy(css = "div.inventory_item_name")
    List<WebElement> inventoryItemNames;

    @FindBy(css = "div.bm-menu")
    WebElement burgerMenu;

    @FindBy(css = "div.bm-menu-wrap")
    WebElement burgerMenuWrap;

    private final String EXPECTED_APP_LOGO_TEXT = "Swag Labs";
    private final String EXPECTED_SECONDARY_HEADER_TITLE_TEXT = "Products";

    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getAppLogo() {return appLogo;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getInventoryContainer() {return inventoryContainer;}
    public WebElement getInventoryList() {return inventoryList;}
    public List<WebElement> getInventoryItemNames() {return inventoryItemNames;}
    public WebElement getBurgerMenu() {return burgerMenu;}
    public WebElement getBurgerMenuWrap() {return burgerMenuWrap;}
    public String getExpectedAppLogoText() {return EXPECTED_APP_LOGO_TEXT;}
    public String getExpectedSecondaryHeaderTitleText() {return EXPECTED_SECONDARY_HEADER_TITLE_TEXT;}

    public CartPage clickCartButton() {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            jsExecutor.executeScript("arguments[0].click();", shoppingCartLink);
            wait.until(ExpectedConditions.urlContains("cart"));
        } catch (TimeoutException e) {
            return null;
        }

        return new CartPage(webDriver);
    }

    public InventoryItemPage clickInventoryItem(String itemName) {
        WebElement inventoryItem = findInventoryItemByName(itemName);

        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            jsExecutor.executeScript("arguments[0].click()", inventoryItem);
            wait.until(ExpectedConditions.urlContains("inventory-item"));
        } catch (TimeoutException e) {
            return null;
        }

        return new InventoryItemPage(webDriver, inventoryItem);
    }

    public void clickBurgerMenuButton() {
        try {
            click(burgerMenuBtn);
            wait.until(driver -> isBurgerMenuDisplayed());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public boolean isItemPresent (String itemName) {
        return findInventoryItemByName(itemName) != null;
    }

    public WebElement findInventoryItemByName(String itemName) {
        try {
            for(WebElement item : inventoryItemNames) {
                if (item.getText().equalsIgnoreCase(itemName)) {
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isBurgerMenuDisplayed() {
        return burgerMenu.getAttribute("hidden") == null && burgerMenuWrap.isDisplayed();
    }
}
