package tests;

import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;

public class CartTests extends BaseTest {

    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();
    private static final ThreadLocal<CartPage> cartPage = new ThreadLocal<>();

    @BeforeMethod
    public void initializeCartTest() {
        inventoryPage.set(initializeToInventoryPage());
    }

    @Test
    public void testContinueShoppingButton() {
        CartPage cartPage = inventoryPage.get().clickCartButton();
        InventoryPage inventoryPage = cartPage.clickContinueShoppingButton();
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Failed to navigate to Inventory Page after clicking Continue Shopping button on Cart page.");
    }
}
