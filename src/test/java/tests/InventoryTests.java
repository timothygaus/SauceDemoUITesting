package tests;

import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryItemPage;
import pages.InventoryPage;

public class InventoryTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initializeInventoryTest() {
        inventoryPage = initializeToInventoryPage();
    }

    //TODO: Seems like this data would be better stored in a separate file.
    // Will eventually need to test things like price, description, and image
    @DataProvider(name = "inventoryItemNames")
    public static Object[][] provideInventoryItemNames() {
        return new Object[][] {
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"},
                {"Sauce Labs Onesie"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }

    @Test
    public void testPresenceOfReactBurgerMenuButton () {
        Assert.assertTrue(inventoryPage.getMenuComponent().isMenuHidden());
    }

    @Test
    public void testPresenceOfShoppingCartContainer() {
        Assert.assertTrue(inventoryPage.getShoppingCartContainer().isDisplayed());
    }

    @Test
    public void testPresenceOfInventoryContainer() {
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed());
    }

    @Test
    public void testPresenceOfInventoryList() {
        Assert.assertTrue(inventoryPage.getInventoryList().isDisplayed());
    }

    @Test
    public void testAppLogo() {
        String appLogoText = inventoryPage.getAppLogo().getText();
        Assert.assertEquals(appLogoText, inventoryPage.getExpectedAppLogoText(), "Expected App Logo on Inventory Page to be " + inventoryPage.getExpectedAppLogoText() + " but got " + appLogoText);
    }

    @Test
    public void testSecondaryHeaderTitle() {
        String headerTitleText = inventoryPage.getSecondaryHeaderTitle().getText();
        Assert.assertEquals(headerTitleText, inventoryPage.getExpectedSecondaryHeaderTitleText(), "Expected Secondary Header Title on Inventory Page to be " + inventoryPage.getExpectedSecondaryHeaderTitleText() + " but got " + headerTitleText);
    }

    @Test
    public void testClickCartButton() {
        CartPage cartPage = inventoryPage.clickCartButton();
        Assert.assertTrue(cartPage.isPageLoaded(), "Clicking cart button on Inventory page failed to navigate to Cart page.");
    }

    @Test(dataProvider = "inventoryItemNames")
    public void testPresenceOfItemsByName(String itemName) {
        Assert.assertTrue(inventoryPage.isItemPresent(itemName), "Item " + itemName + " was not found on the Inventory page.");
    }

    @Test
    public void testAbsenceOfNonExistentItem() {
        String itemName = "Invalid Item";
        Assert.assertFalse(inventoryPage.isItemPresent(itemName), "Item " + itemName + " was unexpectedly found on the Inventory page.");
    }

    @Test
    public void testClickingBurgerMenuButton() {
        inventoryPage.clickBurgerMenuButton();
        Assert.assertFalse(inventoryPage.getMenuComponent().isMenuHidden(), "Menu did not appear after clicking burger menu button on Inventory page");
    }

    @Test
    public void testAbsenceOfBurgerMenu() {
        Assert.assertTrue(inventoryPage.getMenuComponent().isMenuHidden(), "Burger menu unexpectedly displayed on Inventory page initialization");
    }

    @Test(dataProvider = "inventoryItemNames")
    public void testClickInventoryItemName(String itemName) {
        InventoryItemPage itemPage = inventoryPage.clickInventoryItem(itemName);
        Assert.assertTrue(itemPage.isPageLoaded(), "Clicking name for " + itemName + " did not correctly navigate to the Inventory Item page for that item.");
        Assert.assertEquals(itemPage.getInventoryDetailsName().getText(), itemName, "Clicking name for " + itemName + " did not correctly navigate to the Inventory Item page for that item. Instead, navigated to " + itemPage.getInventoryDetailsName().getText());
    }
}
