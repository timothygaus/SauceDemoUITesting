package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutStepTwoPage;
import pages.InventoryPage;

public class CheckoutStepTwoTests extends BaseTest {

    private CheckoutStepTwoPage checkoutStepTwoPage;

    @BeforeMethod
    public void initializeCheckoutStepTwoTest() {
        InventoryPage inventoryPage = initializeToInventoryPage();
    }
}
