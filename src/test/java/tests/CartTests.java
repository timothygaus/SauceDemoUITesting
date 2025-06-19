package tests;

import framework.base.BaseTest;
import framework.utils.InventoryDataLoader;
import framework.utils.InventoryItem;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.CheckoutStepOnePage;
import pages.InventoryItemPage;
import pages.InventoryPage;

import java.util.List;

public class CartTests extends BaseTest {

    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    @BeforeMethod
    public void initializeCartTest() {
        inventoryPage.set(initializeToInventoryPage());
        softAssert.set(new SoftAssert());
    }

    @Test
    public void testCartPageHeaderElements() {
        CartPage cartPage = inventoryPage.get().clickCartButton();
        softAssert.get().assertTrue(cartPage.getAppLogo().isDisplayed(), "App logo is not displayed on Cart page.");
        softAssert.get().assertTrue(cartPage.getBurgerMenuBtn().isDisplayed(), "Burger menu button is not displayed on Cart page.");
        softAssert.get().assertTrue(cartPage.getShoppingCartContainer().isDisplayed(), "Shopping cart container is not displayed on Cart page.");
        softAssert.get().assertTrue(cartPage.getSecondaryHeaderTitle().isDisplayed(), "Secondary header title is not displayed on Cart page.");

        softAssert.get().assertAll();
    }

    @Test
    public void testCartPageBodyElements() {
        CartPage cartPage = inventoryPage.get().clickCartButton();
        softAssert.get().assertTrue(cartPage.getCartQuantityLabel().isDisplayed(), "Cart quantity label is not displayed on Cart page.");
        softAssert.get().assertTrue(cartPage.getCartDescriptionLabel().isDisplayed(), "Cart description label is not displayed on Cart page.");
        softAssert.get().assertTrue(cartPage.getCartContentsContainer().isDisplayed(), "Cart contents container is not displayed on Cart page.");
        softAssert.get().assertTrue(cartPage.getContinueShoppingButton().isDisplayed(), "Continue Shopping button is not displayed on Cart page.");
        softAssert.get().assertTrue(cartPage.getCheckoutButton().isDisplayed(), "Checkout button is not displayed on Cart page.");

        softAssert.get().assertAll();
    }

    @Test(dataProvider = "inventoryItems", dataProviderClass = framework.utils.TestDataProvider.class)
    public void testCartItems(InventoryItem item) {
        String itemName = item.getName();
        WebElement inventoryItem = inventoryPage.get().findInventoryItemByName(itemName);
        inventoryPage.get().clickAddToCartButton(inventoryItem);
        CartPage cartPage = inventoryPage.get().clickCartButton();

        WebElement cartItem = cartPage.getCartItemByName(itemName);
        Assert.assertTrue(cartItem.isDisplayed(), "Cart item with name '" + itemName + "' is not displayed on Cart page " +
                "after adding to cart on Inventory page.");

        softAssert.get().assertEquals(cartPage.getInventoryItemDescriptionElement(cartItem).getText(), item.getDescription(),
                "Cart item description does not match expected value after adding to cart on Inventory page.");
        //App does not have functionality to add multiple quantities of the same item to the cart
        softAssert.get().assertEquals(cartPage.getInventoryItemQuantityElement(cartItem).getText(), "1",
                "Cart item quantity does not match expected value after adding to cart on Inventory page.");
        softAssert.get().assertEquals(cartPage.getInventoryItemPriceElement(cartItem).getText(), item.getPrice(),
                "Cart item price does not match expected value after adding to cart on Inventory page.");
        softAssert.get().assertTrue(cartPage.getRemoveButtonElement(cartItem).isDisplayed(),
                "Remove button for cart item is not displayed on Cart page after adding to cart on Inventory page.");

        softAssert.get().assertAll();
    }

    @Test
    public void testContinueShoppingButton() {
        CartPage cartPage = inventoryPage.get().clickCartButton();
        InventoryPage inventoryPage = cartPage.clickContinueShoppingButton();
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Failed to navigate to Inventory Page after clicking Continue Shopping button on Cart page.");
    }

    @Test
    public void testCheckoutButton() {
        CartPage cartPage = inventoryPage.get().clickCartButton();
        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickCheckoutButton();
        Assert.assertTrue(checkoutStepOnePage.isPageLoaded(), "Failed to navigate to Checkout Page after clicking Checkout button on Cart page.");
    }

    @Test
    public void testClickingProductName() {
        WebElement inventoryItem = inventoryPage.get().getFirstInventoryItem();
        String inventoryItemName = inventoryPage.get().getInventoryItemNameElement(inventoryItem).getText();
        inventoryPage.get().clickAddToCartButton(inventoryItem);
        CartPage cartPage = inventoryPage.get().clickCartButton();

        WebElement cartItem = cartPage.getCartItemByName(inventoryItemName);
        InventoryItemPage inventoryItemPage = cartPage.clickInventoryItemName(cartItem);
        Assert.assertTrue(inventoryItemPage.isPageLoaded(), "Failed to navigate to Inventory Item Page after clicking product name on Cart page.");

        List<InventoryItem> inventoryItemData = InventoryDataLoader.loadInventoryItems();
        InventoryItem inventoryItemTestData = inventoryItemData.stream()
                .filter(item -> item.getName().equals(inventoryItemName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Inventory item not found in test data"));

        softAssert.get().assertEquals(inventoryItemPage.getInventoryDetailsName().getText(), inventoryItemTestData.getName(),
                "Inventory item name does not match expected value on Inventory Item page.");
        softAssert.get().assertEquals(inventoryItemPage.getInventoryDetailsDescription().getText(), inventoryItemTestData.getDescription(),
                "Inventory item description does not match expected value on Inventory Item page.");
        softAssert.get().assertEquals(inventoryItemPage.getInventoryDetailsPrice().getText(), inventoryItemTestData.getPrice(),
                "Inventory item price does not match expected value on Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getInventoryDetailsImage().getAttribute("src").contains(inventoryItemTestData.getImageUrl()),
                "Inventory item image URL does not match expected value on Inventory Item page.");

        softAssert.get().assertAll();
    }

    @Test
    public void testCartBadgeNoItems() {
        CartPage cartPage = inventoryPage.get().clickCartButton();
        Assert.assertFalse(cartPage.isShoppingCartBadgePresent(), "Cart badge should not be displayed when there are no items in the cart.");
    }

    @Test
    public void testCartBadgeWithItems() {
        WebElement inventoryItem = inventoryPage.get().getFirstInventoryItem();
        inventoryPage.get().clickAddToCartButton(inventoryItem);
        CartPage cartPage = inventoryPage.get().clickCartButton();

        Assert.assertTrue(cartPage.isShoppingCartBadgePresent(), "Cart badge should be displayed when there are items in the cart.");
        Assert.assertEquals(cartPage.getShoppingCartBadgeValue(), 1, "Cart badge value should be 1 after adding one item to the cart.");
    }

    @Test
    public void testRemoveItemFromCart() {
        WebElement inventoryItem = inventoryPage.get().getFirstInventoryItem();
        String itemName = inventoryPage.get().getInventoryItemNameElement(inventoryItem).getText();
        inventoryPage.get().clickAddToCartButton(inventoryItem);
        CartPage cartPage = inventoryPage.get().clickCartButton();

        WebElement cartItem = cartPage.getCartItemByName(itemName);
        Assert.assertTrue(cartItem.isDisplayed(), "Cart item is not displayed after adding to cart.");

        cartPage.clickRemoveItemButton(cartItem);
        Assert.assertFalse(cartItem.isDisplayed(), "Cart item is still displayed after removing from cart.");

        Assert.assertFalse(cartPage.isShoppingCartBadgePresent(), "Cart badge should not be displayed when there are no items in the cart.");
    }
}
