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

    private CartPage cartPage;

    @BeforeMethod
    public void initializeCartTest() {
        webDriver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        cartPage = inventoryPage.clickCartButton();
        Assert.assertNotNull(cartPage, "Failed to navigate to the Cart page.");
    }

    @Test
    public void testContinueShoppingButton() {
        InventoryPage inventoryPage = cartPage.clickContinueShoppingButton();
        Assert.assertNotNull(inventoryPage, "Failed to navigate to Inventory Page after clicking Continue Shopping button on Cart page.");
    }
}
