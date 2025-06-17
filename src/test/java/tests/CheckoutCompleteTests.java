package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.CheckoutCompletePage;
import pages.InventoryPage;

public class CheckoutCompleteTests extends BaseTest {

    private static final ThreadLocal<CheckoutCompletePage> checkoutCompletePage = new ThreadLocal<>();
    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();

    @BeforeMethod
    public void initializeCheckoutCompleteTest() {
        inventoryPage.set(initializeToInventoryPage());
    }
}
