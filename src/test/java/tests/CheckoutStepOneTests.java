package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutStepOnePage;
import pages.InventoryPage;

public class CheckoutStepOneTests extends BaseTest {

    private CheckoutStepOnePage checkoutStepOnePage;

    @BeforeMethod
    public void initializeCheckoutStepOneTest() {
        InventoryPage inventoryPage = initializeToInventoryPage();
    }
}
