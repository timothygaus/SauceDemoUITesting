package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutStepTwoPage;
import pages.InventoryPage;

public class CheckoutStepTwoTests extends BaseTest {

    private CheckoutStepTwoPage checkoutStepTwoPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initializeCheckoutStepTwoTest() {
        inventoryPage = initializeToInventoryPage();
    }
}
