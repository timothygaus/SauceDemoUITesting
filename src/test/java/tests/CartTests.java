package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTests extends BaseTest {

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod
    public void initializeCartTest() {
        InventoryPage inventoryPage = initializeToInventoryPage();
        cartPage = inventoryPage.clickCartButton();
        Assert.assertNotNull(cartPage, "Failed to navigate to the Cart page.");
    }

    @Test
    public void testContinueShoppingButton() {
        CartPage cartPage = inventoryPage.clickCartButton();
        InventoryPage inventoryPage = cartPage.clickContinueShoppingButton();
        Assert.assertNotNull(inventoryPage, "Failed to navigate to Inventory Page after clicking Continue Shopping button on Cart page.");
    }
}
