package tests;

import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.components.MenuComponent;

public class MenuComponentTests extends BaseTest {

    private MenuComponent menuComponent;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initializeMenuComponentTests() {
        inventoryPage = initializeToInventoryPage();
        menuComponent = inventoryPage.getMenuComponent();
    }

    @Test
    public void testMenuComponentElementsPresence() {
        inventoryPage.clickBurgerMenuButton();
        Assert.assertFalse(menuComponent.isMenuHidden(), "Menu is not displayed");
        Assert.assertTrue(menuComponent.getCloseButton().isDisplayed(), "Close Button is not displayed");
        Assert.assertTrue(menuComponent.getAllItemsLink().isDisplayed(), "All Items Link is not displayed");
        Assert.assertTrue(menuComponent.getAboutLink().isDisplayed(), "About Link is not displayed");
        Assert.assertTrue(menuComponent.getLogoutLink().isDisplayed(), "Logout Link is not displayed");
        Assert.assertTrue(menuComponent.getResetAppStateLink().isDisplayed(), "Reset App State Link is not displayed");
    }

    @Test
    public void testCloseButtonFunctionality() {
        inventoryPage.clickBurgerMenuButton();
        menuComponent.clickCloseButton();
        Assert.assertTrue(menuComponent.isMenuHidden(), "Menu is still displayed after clicking Close Button");
    }

    @Test
    public void testAllItemsLinkFunctionalityOnInventoryPage() {
        inventoryPage.clickBurgerMenuButton();
        menuComponent.clickAllItems();
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory Page is not displayed after clicking All Items link");
    }

    @Test
    public void testAllItemsLinkFunctionalityOnAnotherPage() {
        // Navigate to another page (e.g., item details page)
        CartPage cartPage = inventoryPage.clickCartButton();
        Assert.assertTrue(cartPage.isPageLoaded(), "Cart Page is not displayed");

        // Now test the All Items link from the menu
        cartPage.clickBurgerMenuButton();
        InventoryPage inventoryPage = menuComponent.clickAllItems();
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory Page is not displayed after clicking All Items " +
                "link from another page");
    }

    @Test
    public void testLogoutButtonFunctionality() {
        inventoryPage.clickBurgerMenuButton();
        LoginPage loginPage = menuComponent.clickLogout();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not displayed after logout");
    }
}
