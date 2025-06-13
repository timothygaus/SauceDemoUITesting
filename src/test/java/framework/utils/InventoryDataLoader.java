package framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class InventoryDataLoader {
    public static List<InventoryItem> loadInventoryItems() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = InventoryDataLoader.class.getClassLoader().getResourceAsStream("inventory_items.json");
            return mapper.readValue(is, new TypeReference<List<InventoryItem>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
