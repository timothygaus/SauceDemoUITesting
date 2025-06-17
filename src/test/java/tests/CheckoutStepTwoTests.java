package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutStepTwoPage;
import pages.InventoryPage;

public class CheckoutStepTwoTests extends BaseTest {

    private static final ThreadLocal<CheckoutStepTwoPage> checkoutStepTwoPage = new ThreadLocal<>();
    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();

    @BeforeMethod
    public void initializeCheckoutStepTwoTest() {
        inventoryPage.set(initializeToInventoryPage());
    }
}
