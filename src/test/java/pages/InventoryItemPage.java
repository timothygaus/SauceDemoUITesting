package pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage extends BasePage {

    public InventoryItemPage(WebDriver webDriver, WebElement inventoryItem) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "div.inventory_details_name")
    WebElement inventoryDetailsName;

    @FindBy(css = "div.inventory_details_desc")
    WebElement inventoryDetailsDescription;

    @FindBy(css = "div.inventory_details_price")
    WebElement inventoryDetailsPrice;

    @FindBy(id = "add-to-cart")
    WebElement addToCartButton;

    public WebElement getInventoryDetailsName() {return inventoryDetailsName;}
    public WebElement getInventoryDetailsDescription() {return inventoryDetailsDescription;}
    public WebElement getInventoryDetailsPrice() {return inventoryDetailsPrice;}
    public WebElement getAddToCartButton() {return addToCartButton;}
}
