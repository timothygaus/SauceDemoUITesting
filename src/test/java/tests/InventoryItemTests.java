package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.InventoryItemPage;
import pages.InventoryPage;

public class InventoryItemTests extends BaseTest {

    private static final ThreadLocal<InventoryItemPage> inventoryItemPage = new ThreadLocal<>();
    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();

    @BeforeMethod
    public void initializeInventoryItemTest() {
        inventoryPage.set(initializeToInventoryPage());
    }
}
