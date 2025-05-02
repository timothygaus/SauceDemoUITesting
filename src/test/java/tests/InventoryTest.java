package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
}
