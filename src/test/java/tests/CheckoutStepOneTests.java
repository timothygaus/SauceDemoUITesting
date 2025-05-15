package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutStepOnePage;
import pages.InventoryPage;

public class CheckoutStepOneTests extends BaseTest {

    private CheckoutStepOnePage checkoutStepOnePage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initializeCheckoutStepOneTest() {
        inventoryPage = initializeToInventoryPage();
    }
}
