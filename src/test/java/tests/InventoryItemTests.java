package tests;

import framework.base.BaseTest;
import framework.utils.InventoryItem;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.InventoryItemPage;
import pages.InventoryPage;

public class InventoryItemTests extends BaseTest {

    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    @BeforeMethod
    public void initializeInventoryItemTest() {
        inventoryPage.set(initializeToInventoryPage());
        softAssert.set(new SoftAssert());
    }

    //TODO: verify text of header elements on Inventory Item page

    @Test
    public void testInventoryItemPageHeaderElementsDisplayed() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        softAssert.get().assertTrue(inventoryItemPage.getAppLogo().isDisplayed(), "App logo is not displayed on " +
                "Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getBurgerMenuBtn().isDisplayed(), "Burger menu button is " +
                "not displayed on Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getShoppingCartContainer().isDisplayed(), "Shopping cart " +
                "container is not displayed on Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getSecondaryHeaderTitle().isDisplayed(), "Secondary header " +
                "title is not displayed on Inventory Item page.");

        softAssert.get().assertAll();
    }

    @Test
    public void testInventoryItemPageProductDetailsDisplayed() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        softAssert.get().assertTrue(inventoryItemPage.getInventoryDetailsName().isDisplayed(), "Inventory item name " +
                "is not displayed on Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getInventoryDetailsImage().isDisplayed(), "Inventory item image " +
                "is not displayed on Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getInventoryDetailsDescription().isDisplayed(), "Inventory item " +
                "description is not displayed on Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getInventoryDetailsPrice().isDisplayed(), "Inventory item price " +
                "is not displayed on Inventory Item page.");
        softAssert.get().assertTrue(inventoryItemPage.getAddToCartButton().isDisplayed(), "Add to cart button is not " +
                "displayed on Inventory Item page.");

        softAssert.get().assertAll();
    }

    @Test(dataProvider = "inventoryItems", dataProviderClass = framework.utils.TestDataProvider.class)
    public void testInventoryItemInformation(InventoryItem item) {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickInventoryItem(item.getName());
        softAssert.get().assertEquals(inventoryItemPage.getInventoryDetailsName().getText(), item.getName(),
                "Inventory item name does not match expected value.");
        softAssert.get().assertEquals(inventoryItemPage.getInventoryDetailsDescription().getText(), item.getDescription(),
                "Inventory item description does not match expected value.");
        softAssert.get().assertEquals(inventoryItemPage.getInventoryDetailsPrice().getText(), item.getPrice(),
                "Inventory item price does not match expected value.");
        softAssert.get().assertTrue(inventoryItemPage.getInventoryDetailsImage().getAttribute("src").contains(item.getImageUrl()),
                "Inventory item image URL does not match expected value.");

        softAssert.get().assertAll();
    }

    @Test
    public void testBackToProductsButtonFunctionality() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        InventoryPage inventoryPage = inventoryItemPage.clickBackToProductsButton();
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Failed to navigate to Inventory Page after clicking " +
                "Back to Products button on Inventory Item page.");
    }

    @Test
    public void testCartButtonFunctionality() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        CartPage cartPage = inventoryItemPage.clickCartButton();
        Assert.assertTrue(cartPage.isPageLoaded(), "Failed to navigate to Cart Page after clicking cart button on " +
                "Inventory Item page.");
    }

    @Test
    public void testAddToCartButtonReplacedWithRemoveButton() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        inventoryItemPage.clickAddToCartButton();
        Assert.assertTrue(inventoryItemPage.getRemoveButton().isDisplayed(), "Add to cart button was not replaced " +
                "with remove button after clicking add to cart on Inventory Item page.");
    }

    @Test
    public void testShoppingCartBadgeIncrements() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        int initialBadgeValue = inventoryItemPage.getShoppingCartBadgeValue();

        inventoryItemPage.clickAddToCartButton();
        int updatedBadgeValue = inventoryItemPage.getShoppingCartBadgeValue();

        Assert.assertEquals(updatedBadgeValue, initialBadgeValue + 1, "Shopping cart badge did not increment after " +
                "adding item to cart on Inventory Item page.");
    }

    @Test
    public void testRemoveButtonReplacedWithAddToCartButton() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        inventoryItemPage.clickAddToCartButton();
        inventoryItemPage.clickRemoveButton();
        Assert.assertTrue(inventoryItemPage.getAddToCartButton().isDisplayed(), "Remove button was not replaced with " +
                "add to cart button after clicking remove on Inventory Item page.");
    }

    @Test
    public void testRemoveButtonDecrementsShoppingCartBadge() {
        InventoryItemPage inventoryItemPage = inventoryPage.get().clickFirstInventoryItem();
        inventoryItemPage.clickAddToCartButton();
        int initialBadgeValue = inventoryItemPage.getShoppingCartBadgeValue();

        inventoryItemPage.clickRemoveButton();
        int updatedBadgeValue = inventoryItemPage.getShoppingCartBadgeValue();

        Assert.assertEquals(updatedBadgeValue, initialBadgeValue - 1, "Shopping cart badge did not decrement after " +
                "removing item from cart on Inventory Item page.");
    }
}
