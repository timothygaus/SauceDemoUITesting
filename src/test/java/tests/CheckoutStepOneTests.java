package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutStepOnePage;
import pages.InventoryPage;

public class CheckoutStepOneTests extends BaseTest {

    private static final ThreadLocal<CheckoutStepOnePage> checkoutStepOnePage = new ThreadLocal<>();
    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();

    @BeforeMethod
    public void initializeCheckoutStepOneTest() {
        inventoryPage.set(initializeToInventoryPage());
    }
}
