package pages;

import framework.base.BasePage;
import framework.utils.Urls;
import framework.utils.enums.SortingOption;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.components.MenuComponent;

import java.util.List;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver webDriver) {
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

    @FindBy(css = "select.product_sort_container")
    WebElement productSortMenu;

    @FindBy(css = "div.inventory_container")
    WebElement inventoryContainer;

    @FindBy(css = "div.inventory_list")
    WebElement inventoryList;

    @FindBy(css = "div.inventory_item_name")
    List<WebElement> inventoryItemNames;

    private final String EXPECTED_APP_LOGO_TEXT = "Swag Labs";
    private final String EXPECTED_SECONDARY_HEADER_TITLE_TEXT = "Products";
    private final MenuComponent menuComponent;

    public WebElement getAppLogo() {return appLogo;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getProductSortMenu() {return productSortMenu;}
    public WebElement getInventoryContainer() {return inventoryContainer;}
    public WebElement getInventoryList() {return inventoryList;}
    public List<WebElement> getInventoryItemNames() {return inventoryItemNames;}
    public String getExpectedAppLogoText() {return EXPECTED_APP_LOGO_TEXT;}
    public String getExpectedSecondaryHeaderTitleText() {return EXPECTED_SECONDARY_HEADER_TITLE_TEXT;}
    public MenuComponent getMenuComponent() {return menuComponent;}

    /**
     * Checks that the inventory page is loaded by checking the URL and key WebElements are displayed
     * @return true if the URL contains INVENTORY_PAGE_URL, the inventory container is displayed, the shopping cart
     * container is displayed, and the product sort menu is displayed.
     */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains(Urls.INVENTORY_PAGE_URL));
            wait.until(ExpectedConditions.visibilityOf(getInventoryContainer()));
            wait.until(ExpectedConditions.visibilityOf(getShoppingCartContainer()));
            wait.until(ExpectedConditions.visibilityOf(getProductSortMenu()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clicks the burger menu button and opens the burger menu
     */
    public void clickBurgerMenuButton() {
        click(burgerMenuBtn, () -> !getMenuComponent().isMenuHidden());
    }

    /**
     * Clicks the cart button and navigates to the cart page
     * @return CartPage
     */
    public CartPage clickCartButton() {
        click(shoppingCartLink, () -> webDriver.getCurrentUrl().contains("cart"));
        wait.until(ExpectedConditions.urlContains("cart"));
        if (!webDriver.getCurrentUrl().contains("cart")) {
            throw new IllegalStateException("Failed to navigate to the Cart page.");
        }
        return new CartPage(webDriver);
    }

    /**
     * Selects the specified value from the sorting dropdown menu
     * @param option SortingOption
     */
    public void selectSortingOption(SortingOption option) {
        Select select = new Select(getProductSortMenu());
        select.selectByValue(option.getValue());
    }

    /**
     * Clicks on the name of a given item on the inventory page and navigates to the appropriate inventory item page for
     * that item.
     * @param itemName String name of the item
     * @return InventoryItemPage
     */
    public InventoryItemPage clickInventoryItem(String itemName) {
        WebElement inventoryItem = findInventoryItemByName(itemName);
        click(inventoryItem, () -> webDriver.getCurrentUrl().contains("inventory-item"));
        return new InventoryItemPage(webDriver);
    }

    /**
     * Checks if a given item is present on the inventory page
     * @param itemName String name of the item
     * @return boolean, true if the item is present
     */
    public boolean isItemPresent (String itemName) {
        return findInventoryItemByName(itemName) != null;
    }

    /**
     * Attempts to locate the given item name by looping through all inventory items currently on the inventory page.
     * @param itemName String name of the item
     * @return WebElement of the item of the matching name. Return null if it was not found.
     */
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
}
