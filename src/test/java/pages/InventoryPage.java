package pages;

import framework.base.BasePage;
import framework.utils.Urls;
import framework.utils.enums.SortingOption;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.components.MenuComponent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static framework.utils.WebElementUtils.click;

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

    @FindBy(css = "div.inventory_item")
    List<WebElement> inventoryItems;

    @FindBy(css = "div.inventory_item_name")
    List<WebElement> inventoryItemNames;

    private final String EXPECTED_APP_LOGO_TEXT = "Swag Labs";
    private final String EXPECTED_SECONDARY_HEADER_TITLE_TEXT = "Products";
    private final MenuComponent menuComponent;

    public WebElement getAppLogo() {return appLogo;}
    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getProductSortMenu() {return productSortMenu;}
    public WebElement getInventoryContainer() {return inventoryContainer;}
    public WebElement getInventoryList() {return inventoryList;}
    public List<WebElement> getInventoryItems() {return inventoryItems;}
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
        click(webDriver, burgerMenuBtn, () -> !getMenuComponent().isMenuHidden());
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

    /**
     * Selects the specified value from the sorting dropdown menu
     * @param option SortingOption
     */
    public void selectSortingOption(SortingOption option) {
        Select select = new Select(getProductSortMenu());
        select.selectByVisibleText(option.getValue());
    }

    /**
     * Gets the list of sorting options present in the sorting dropdown menu
     * @return List of Strings of sorting options
     */
    public List<String> getSortingDropdownOptions() {
        Select select = new Select(getProductSortMenu());
        return select.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Clicks on the name of a given item on the inventory page and navigates to the appropriate inventory item page for
     * that item.
     * @param itemName String name of the item
     * @return InventoryItemPage
     */
    public InventoryItemPage clickInventoryItem(String itemName) {
        WebElement inventoryItem = findInventoryItemByName(itemName);
        click(webDriver, getInventoryItemNameElement(inventoryItem), () -> new InventoryItemPage(webDriver).isPageLoaded());
        return new InventoryItemPage(webDriver);
    }

    /**
     * Clicks on the image of a given item on the inventory page and navigates to the appropriate inventory item page for
     * that item.
     * @param itemName String name of the item
     * @return InventoryItemPage
     */
    public InventoryItemPage clickInventoryItemImage(String itemName) {
        WebElement inventoryItem = findInventoryItemByName(itemName);
        click(webDriver, getInventoryItemImageElement(inventoryItem), () -> new InventoryItemPage(webDriver).isPageLoaded());
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
     * Gets the inventory_item_name WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return WebElement inventory_item_name
     */
    public WebElement getInventoryItemNameElement(WebElement inventoryItem) {
        return inventoryItem.findElement(By.cssSelector("div.inventory_item_name"));
    }

    /**
     * Gets the inventory_item_img WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return WebElement inventory_item_img
     */
    public WebElement getInventoryItemImageElement(WebElement inventoryItem) {
        return inventoryItem.findElement(By.cssSelector("img.inventory_item_img"));
    }

    /**
     * Gets the src relative path of the inventory_item_img WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return String src relative path of inventory_item_img
     */
    public String getInventoryItemImageSrcPath(WebElement inventoryItem) {
        try {
            String imgUrl = getInventoryItemImageElement(inventoryItem).getAttribute("src");
            URL url = new URL(imgUrl);
            return url.getPath();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the inventory_item_desc WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return WebElement inventory_item_desc
     */
    public WebElement getInventoryItemDescriptionElement(WebElement inventoryItem) {
        return inventoryItem.findElement(By.cssSelector("div.inventory_item_desc"));
    }

    /**
     * Gets the inventory_item_price WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return WebElement inventory_item_price
     */
    public WebElement getInventoryItemPriceElement(WebElement inventoryItem) {
        return inventoryItem.findElement(By.cssSelector("div.inventory_item_price"));
    }

    /**
     * Gets the add to cart button WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return WebElement Add to cart button
     */
    public WebElement getInventoryItemAddToCartButtonElement(WebElement inventoryItem) {
        return inventoryItem.findElement(By.cssSelector("button.btn.btn_primary"));
    }

    /**
     * Gets the remove cart button WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return WebElement Remove button
     */
    public WebElement getInventoryItemRemoveButtonElement(WebElement inventoryItem) {
        return inventoryItem.findElement(By.cssSelector("button.btn.btn_secondary"));
    }

    /**
     * Clicks the Add to cart button WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     */
    public void clickAddToCartButton(WebElement inventoryItem) {
        click(webDriver, getInventoryItemAddToCartButtonElement(inventoryItem),
                () -> getInventoryItemRemoveButtonElement(inventoryItem).isDisplayed());
    }

    /**
     * Clicks the Remove button WebElement for a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     */
    public void clickRemoveButton(WebElement inventoryItem) {
        click(webDriver, getInventoryItemRemoveButtonElement(inventoryItem),
                () -> getInventoryItemAddToCartButtonElement(inventoryItem).isDisplayed());
    }

    /**
     * Attempts to locate the given item name by looping through all inventory items currently on the inventory page.
     * @param itemName String name of the item
     * @return WebElement of the item of the matching name. Return null if it was not found.
     */
    public WebElement findInventoryItemByName(String itemName) {
        try {
            for(WebElement item : getInventoryItems()) {
                if (getInventoryItemNameElement(item).
                        getText().
                        equalsIgnoreCase(itemName)) {
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Removes the '$' character from a string and returns it as a double
     * @param priceString String to be parsed
     * @return double converted from String with $ removed
     */
    public static double parsePrice(String priceString) {
        return Double.parseDouble(priceString.replace("$", ""));
    }

    /**
     * Gets the price of a given inventory_item WebElement
     * @param inventoryItem inventory_item WebElement
     * @return double inventory_item_price converted from String with '$' removed
     */
    public double getInventoryItemPriceAsDouble(WebElement inventoryItem) {
        return parsePrice(getInventoryItemPriceElement(inventoryItem).getText());
    }


}
