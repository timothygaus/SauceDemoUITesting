package framework.utils;

public class InventoryItem {

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
}
