package framework.utils;

import org.testng.annotations.DataProvider;

import java.util.List;

public class TestDataProvider {
    @DataProvider(name = "inventoryItems")
    public static Object[][] provideInventoryItems() {
        try {
            List<InventoryItem> items = InventoryDataLoader.loadInventoryItems();
            Object[][] data = new Object[items.size()][1];
            for (int i = 0; i < items.size(); i++) {
                data[i][0] = items.get(i);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
