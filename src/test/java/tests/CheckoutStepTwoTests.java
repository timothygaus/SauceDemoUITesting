package tests;

import framework.base.BaseTest;
import framework.utils.InventoryItem;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckoutCompletePage;
import pages.CheckoutStepTwoPage;
import pages.InventoryItemPage;
import pages.InventoryPage;

import java.util.List;

public class CheckoutStepTwoTests extends BaseTest {

    private static final ThreadLocal<CheckoutStepTwoPage> checkoutStepTwoPage = new ThreadLocal<>();
    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    @BeforeMethod
    public void initializeCheckoutStepTwoTest() {
        inventoryPage.set(initializeToInventoryPage());
        checkoutStepTwoPage.set(CheckoutStepTwoPage.setUpCheckoutStepTwoPage(inventoryPage.get()));
        softAssert.set(new SoftAssert());
    }

    @Test
    public void testCheckoutStepTwoHeaderElements() {
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getAppLogo().isDisplayed(), "App logo is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getBurgerMenuBtn().isDisplayed(), "Burger menu button is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getShoppingCartContainer().isDisplayed(), "Shopping cart container is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getSecondaryHeaderTitle().isDisplayed(), "Secondary header title is not displayed");
        softAssert.get().assertAll();
    }

    @Test
    public void testCheckoutStepTwoBodyElements() {
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getCartQuantityLabel().isDisplayed(), "Cart quantity label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getCartDescriptionLabel().isDisplayed(), "Cart description label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getPaymentInfoLabel().isDisplayed(), "Payment info label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getPaymentValueLabel().isDisplayed(), "Payment value label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getShippingInfoLabel().isDisplayed(), "Shipping info label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getShippingValueLabel().isDisplayed(), "Shipping value label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getTotalInfoLabel().isDisplayed(), "Total info label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getSubtotalLabel().isDisplayed(), "Subtotal label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getTaxLabel().isDisplayed(), "Tax label is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getTotalLabel().isDisplayed(), "Total label is not displayed");
        softAssert.get().assertAll();
    }

    @Test
    public void testCheckoutStepTwoButtonsVisibility() {
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getCancelButton().isDisplayed(), "Cancel button is not displayed");
        softAssert.get().assertTrue(checkoutStepTwoPage.get().getFinishButton().isDisplayed(), "Finish button is not displayed");
        softAssert.get().assertAll();
    }

    @Test
    public void testCheckoutItemsDisplayed() {
        List<InventoryItem> addedItems = checkoutStepTwoPage.get().getAddedCartItems();
        List<InventoryItem> actualItems = checkoutStepTwoPage.get().extractCartItemsAsJsonObjects();

        Assert.assertEquals(actualItems, addedItems, "Cart items do not match the expected items previously added to the cart");
    }

    @Test
    public void testCancelButtonFunctionality() {
        InventoryPage inventoryPage = checkoutStepTwoPage.get().clickCancelButton();
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Cancel button did not redirect to inventory page");
    }

    @Test
    public void testFinishButtonFunctionality() {
        CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.get().clickFinishButton();
        Assert.assertTrue(checkoutCompletePage.isPageLoaded(), "Finish button did not redirect to checkout complete page");
    }

    @Test
    public void testCartItemQuantity() {
        final int EXPECTED_QUANTITY = 1; // App only has functionality to add one of each item to the cart
        for(WebElement item : checkoutStepTwoPage.get().getCartItems()) {
            Assert.assertEquals(Integer.parseInt(checkoutStepTwoPage.get().getCartQuantityElement(item).getText()), EXPECTED_QUANTITY,
                    "Cart item quantity does not match expected value of " + EXPECTED_QUANTITY);
        }
    }

    @Test
    public void testNavigatingToInventoryItemPage() {
        WebElement firstCartItem = checkoutStepTwoPage.get().getCartItems().get(0);
        String itemName = checkoutStepTwoPage.get().getInventoryItemNameElement(firstCartItem).getText();
        InventoryItemPage inventoryItemPage = checkoutStepTwoPage.get().clickCartItem(firstCartItem);

        Assert.assertTrue(inventoryItemPage.isPageLoaded(), "Navigating to inventory item page failed");
        Assert.assertEquals(inventoryItemPage.getInventoryDetailsName().getText(), itemName, "Item name on inventory item page does not match the cart item name");
    }

    @Test
    public void testPaymentInformation() {
        String expectedPaymentInfo = "SauceCard #31337"; // App does not have functionality to change payment info, so this is the expected value
        String actualPaymentInfo = checkoutStepTwoPage.get().getPaymentValueLabel().getText();

        Assert.assertEquals(actualPaymentInfo, expectedPaymentInfo, "Payment information does not match expected value");
    }

    @Test
    public void testShippingInformation() {
        String expectedShippingInfo = "Free Pony Express Delivery!"; // App does not have functionality to change shipping info, so this is the expected value
        String actualShippingInfo = checkoutStepTwoPage.get().getShippingValueLabel().getText();

        Assert.assertEquals(actualShippingInfo, expectedShippingInfo, "Shipping information does not match expected value");
    }

    @Test
    public void testPriceCalculations() {
        double expectedSubtotal = checkoutStepTwoPage.get().calculateItemTotal();
        double expectedTax = checkoutStepTwoPage.get().calculateTax();
        double expectedTotal = checkoutStepTwoPage.get().calculateTotal();

        double actualSubtotal = checkoutStepTwoPage.get().extractPriceFromLabel(checkoutStepTwoPage.get().getSubtotalLabel());
        double actualTax = checkoutStepTwoPage.get().extractPriceFromLabel(checkoutStepTwoPage.get().getTaxLabel());
        double actualTotal = checkoutStepTwoPage.get().extractPriceFromLabel(checkoutStepTwoPage.get().getTotalLabel());

        softAssert.get().assertEquals(expectedSubtotal, actualSubtotal, "Subtotal does not match expected value");
        softAssert.get().assertEquals(expectedTax, actualTax, "Tax does not match expected value");
        softAssert.get().assertEquals(expectedTotal, actualTotal, "Total does not match expected value");

        softAssert.get().assertAll();
    }
}
