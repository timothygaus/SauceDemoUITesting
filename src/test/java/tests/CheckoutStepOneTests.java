package tests;

import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;
import pages.InventoryPage;

public class CheckoutStepOneTests extends BaseTest {

    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();
    private static final ThreadLocal<CheckoutStepOnePage> checkoutStepOnePage = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    @BeforeMethod
    public void initializeCheckoutStepOneTest() {
        inventoryPage.set(initializeToInventoryPage());
        checkoutStepOnePage.set(CheckoutStepOnePage.setUpCheckoutStepOnePage(inventoryPage.get()));
        softAssert.set(new SoftAssert());
    }

    @Test
    public void testCheckoutStepOneHeaderElements() {
        softAssert.get().assertTrue(checkoutStepOnePage.get().getAppLogo().isDisplayed(), "App logo is not displayed");
        softAssert.get().assertTrue(checkoutStepOnePage.get().getBurgerMenuBtn().isDisplayed(), "Burger menu button is not displayed");
        softAssert.get().assertTrue(checkoutStepOnePage.get().getShoppingCartContainer().isDisplayed(), "Shopping cart container is not displayed");
        softAssert.get().assertTrue(checkoutStepOnePage.get().getSecondaryHeaderTitle().isDisplayed(), "Secondary header title is not displayed");
        softAssert.get().assertAll();
    }

    @Test
    public void testCheckoutStepOneFormFieldVisibility() {
        softAssert.get().assertTrue(checkoutStepOnePage.get().getFirstNameField().isDisplayed(), "First name field is not displayed");
        softAssert.get().assertTrue(checkoutStepOnePage.get().getLastNameField().isDisplayed(), "Last name field is not displayed");
        softAssert.get().assertTrue(checkoutStepOnePage.get().getPostalCodeField().isDisplayed(), "Postal code field is not displayed");
        softAssert.get().assertAll();
    }

    @Test
    public void testCheckoutStepOneButtonVisibility() {
        softAssert.get().assertTrue(checkoutStepOnePage.get().getCancelButton().isDisplayed(), "Cancel button is not displayed");
        softAssert.get().assertTrue(checkoutStepOnePage.get().getContinueButton().isDisplayed(), "Continue button is not displayed");
        softAssert.get().assertAll();
    }

    @Test
    public void testFirstNameInputFieldFunctionality() {
        checkoutStepOnePage.get().enterFirstName("John");
        Assert.assertEquals(checkoutStepOnePage.get().getFirstNameField().getAttribute("value"), "John", "First name input field did not accept input");
    }

    @Test
    public void testLastNameInputFieldFunctionality() {
        checkoutStepOnePage.get().enterLastName("Doe");
        Assert.assertEquals(checkoutStepOnePage.get().getLastNameField().getAttribute("value"), "Doe", "Last name input field did not accept input");
    }

    @Test
    public void testPostalCodeInputFieldFunctionality() {
        checkoutStepOnePage.get().enterPostalCode("12345");
        Assert.assertEquals(checkoutStepOnePage.get().getPostalCodeField().getAttribute("value"), "12345", "Postal code input field did not accept input");
    }

    @Test
    public void testCancelButtonFunctionality() {
        CartPage cartPage = checkoutStepOnePage.get().clickCancelButton();
        Assert.assertTrue(cartPage.isPageLoaded(), "Cancel button did not navigate to the cart page");
    }

    @Test
    public void testContinueButtonFunctionality() {
        checkoutStepOnePage.get().enterFirstName("John");
        checkoutStepOnePage.get().enterLastName("Doe");
        checkoutStepOnePage.get().enterPostalCode("12345");
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.get().clickContinueButton();
        Assert.assertTrue(checkoutStepTwoPage.isPageLoaded(), "Continue button did not navigate to the checkout step two page");
    }

    @Test
    public void testContinueButtonWithoutFirstName() {
        checkoutStepOnePage.get().enterLastName("Doe");
        checkoutStepOnePage.get().enterPostalCode("12345");
        checkoutStepOnePage.get().clickContinueButton();

        Assert.assertTrue(checkoutStepOnePage.get().isPageLoaded(), "Continue button did not stay on the checkout step one page when first name is missing");
        Assert.assertTrue(checkoutStepOnePage.get().isErrorMessageVisible(), "Error message is not displayed when first name is missing");
        //TODO: Check expected error message text
        Assert.assertTrue(checkoutStepOnePage.get().isFirstNameErrorIconVisible(), "First name error icon is not displayed when first name is missing");

        checkoutStepOnePage.get().clickErrorMessageButton();
        Assert.assertFalse(checkoutStepOnePage.get().isErrorMessageVisible(), "Error message is still displayed after clicking the error message close button");
        Assert.assertFalse(checkoutStepOnePage.get().isFirstNameErrorIconVisible(), "First name error icon is still displayed after clicking the error message close button");
    }

    @Test
    public void testContinueButtonWithoutLastName() {
        checkoutStepOnePage.get().enterFirstName("John");
        checkoutStepOnePage.get().enterPostalCode("12345");
        checkoutStepOnePage.get().clickContinueButton();

        Assert.assertTrue(checkoutStepOnePage.get().isPageLoaded(), "Continue button did not stay on the checkout step one page when last name is missing");
        Assert.assertTrue(checkoutStepOnePage.get().isErrorMessageVisible(), "Error message is not displayed when last name is missing");
        Assert.assertTrue(checkoutStepOnePage.get().isLastNameErrorIconVisible(), "Last name error icon is not displayed when last name is missing");

        checkoutStepOnePage.get().clickErrorMessageButton();
        Assert.assertFalse(checkoutStepOnePage.get().isErrorMessageVisible(), "Error message is still displayed after clicking the error message close button");
        Assert.assertFalse(checkoutStepOnePage.get().isLastNameErrorIconVisible(), "Last name error icon is still displayed after clicking the error message close button");
    }

    @Test
    public void testContinueButtonWithoutPostalCode() {
        checkoutStepOnePage.get().enterFirstName("John");
        checkoutStepOnePage.get().enterLastName("Doe");
        checkoutStepOnePage.get().clickContinueButton();

        Assert.assertTrue(checkoutStepOnePage.get().isPageLoaded(), "Continue button did not stay on the checkout step one page when postal code is missing");
        Assert.assertTrue(checkoutStepOnePage.get().isErrorMessageVisible(), "Error message is not displayed when postal code is missing");
        Assert.assertTrue(checkoutStepOnePage.get().isPostalCodeErrorIconVisible(), "Postal code error icon is not displayed when postal code is missing");

        checkoutStepOnePage.get().clickErrorMessageButton();
        Assert.assertFalse(checkoutStepOnePage.get().isErrorMessageVisible(), "Error message is still displayed after clicking the error message close button");
        Assert.assertFalse(checkoutStepOnePage.get().isLastNameErrorIconVisible(), "Postal code error icon is still displayed after clicking the error message close button");
    }
}