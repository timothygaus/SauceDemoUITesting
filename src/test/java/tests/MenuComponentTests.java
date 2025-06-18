package tests;

import framework.base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.components.MenuComponent;

import java.awt.*;

public class MenuComponentTests extends BaseTest {

    private static final ThreadLocal<MenuComponent> menuComponent = new ThreadLocal<>();
    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    @BeforeMethod
    public void initializeMenuComponentTests() {
        inventoryPage.set(initializeToInventoryPage());
        menuComponent.set(inventoryPage.get().getMenuComponent());
        softAssert.set(new SoftAssert());
    }

    @Test
    public void testMenuComponentElementsPresence() {
        inventoryPage.get().clickBurgerMenuButton();
        Assert.assertFalse(menuComponent.get().isMenuHidden(), "Menu is not displayed");
        softAssert.get().assertTrue(menuComponent.get().getCloseButton().isDisplayed(), "Close Button is not displayed");
        softAssert.get().assertTrue(menuComponent.get().getAllItemsLink().isDisplayed(), "All Items Link is not displayed");
        softAssert.get().assertTrue(menuComponent.get().getAboutLink().isDisplayed(), "About Link is not displayed");
        softAssert.get().assertTrue(menuComponent.get().getLogoutLink().isDisplayed(), "Logout Link is not displayed");
        softAssert.get().assertTrue(menuComponent.get().getResetAppStateLink().isDisplayed(), "Reset App State Link is not displayed");

        softAssert.get().assertAll();
    }

    @Test
    public void testCloseButtonFunctionality() {
        inventoryPage.get().clickBurgerMenuButton();
        Assert.assertFalse(menuComponent.get().isMenuHidden(), "Menu was not displayed after clicking Menu button");
        menuComponent.get().clickCloseButton();
        Assert.assertTrue(menuComponent.get().isMenuHidden(), "Menu is still displayed after clicking Close Button");
    }

    @Test
    public void testAllItemsLinkFunctionalityOnInventoryPage() {
        inventoryPage.get().clickBurgerMenuButton();
        menuComponent.get().clickAllItems();
        Assert.assertTrue(inventoryPage.get().isPageLoaded(), "Inventory Page is not displayed after clicking All Items link");
    }

    @Test
    public void testAllItemsLinkFunctionalityOnAnotherPage() {
        // Navigate to another page (e.g., item details page)
        CartPage cartPage = inventoryPage.get().clickCartButton();
        Assert.assertTrue(cartPage.isPageLoaded(), "Cart Page is not displayed");

        // Now test the All Items link from the menu
        cartPage.clickBurgerMenuButton();
        InventoryPage inventoryPage = menuComponent.get().clickAllItems();
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory Page is not displayed after clicking All Items " +
                "link from another page");
    }

    @Test
    public void testLogoutButtonFunctionality() {
        inventoryPage.get().clickBurgerMenuButton();
        LoginPage loginPage = menuComponent.get().clickLogout();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not displayed after logout");
    }
}
