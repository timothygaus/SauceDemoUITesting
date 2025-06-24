package framework.utils;

import java.util.Objects;

public class InventoryItem {

    public InventoryItem(String name, String description, String price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public InventoryItem(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = ""; // Default to empty string if no image URL is provided
    }

    private String name;
    private String description;
    private String price;
    private String imageUrl;

    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getPrice() {return price;}
    public String getImageUrl() {return imageUrl;}

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setPrice(String price) {this.price = price;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItem)) return false;
        InventoryItem that = (InventoryItem) o;
        return name.equals(that.name) &&
               description.equals(that.description) &&
               price.equals(that.price) &&
               imageUrl.equals(that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, imageUrl);
    }
}
