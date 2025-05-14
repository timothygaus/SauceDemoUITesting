package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutCompletePage;
import pages.InventoryPage;

public class CheckoutCompleteTests extends BaseTest {

    private CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void initializeCheckoutCompleteTest() {
        InventoryPage inventoryPage = initializeToInventoryPage();
    }
}
