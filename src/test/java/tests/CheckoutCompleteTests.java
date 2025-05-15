package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutCompletePage;
import pages.InventoryPage;

public class CheckoutCompleteTests extends BaseTest {

    private CheckoutCompletePage checkoutCompletePage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initializeCheckoutCompleteTest() {
        inventoryPage = initializeToInventoryPage();
    }
}
