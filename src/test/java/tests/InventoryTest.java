package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initializeInventoryTest() {
        webDriver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(webDriver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(inventoryPage, "Failed to navigate to the Inventory Page after login.");
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
        Assert.assertTrue(inventoryPage.getBurgerMenuBtn().isDisplayed());
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
        Assert.assertEquals(appLogoText, "Swag Labs", "Expected App Logo on Inventory Page to be Swag Labs, but got " + appLogoText);
    }

    @Test
    public void testHeaderTitle() {
        String headerTitleText = inventoryPage.getHeaderSecondaryContainerTitle().getText();
        Assert.assertEquals(headerTitleText, "Products", "Expected Header Title on Inventory Page to be Products, but got " + headerTitleText);
    }

    @Test
    public void testClickCartButton() {
        inventoryPage.clickCartButton();
        Assert.assertTrue(webDriver.getCurrentUrl().contains("cart"), "Clicking cart button on Inventory page failed to navigate to Cart page.");
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
        inventoryPage.getBurgerMenuBtn().click();
        Assert.assertTrue(inventoryPage.getBurgerMenuBtn().isDisplayed(), "Menu did not appear after clicking burger menu button on Inventory page");
    }

    @Test
    public void testAbsenceOfBurgerMenu() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        boolean isVisible = (boolean) jsExecutor.executeScript(
                "return window.getComputedStyle(arguments[0]).hidden == 'true';",
                inventoryPage.getBurgerMenu()
        );
        Assert.assertFalse(isVisible, "Burger menu unexpectedly displayed on Inventory page initialization");
    }
}
