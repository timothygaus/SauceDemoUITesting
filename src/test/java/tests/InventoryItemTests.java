package tests;

import framework.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.InventoryItemPage;
import pages.InventoryPage;

public class InventoryItemTests extends BaseTest {

    private InventoryItemPage inventoryItemPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initializeInventoryItemTest() {
        inventoryPage = initializeToInventoryPage();
    }
}
