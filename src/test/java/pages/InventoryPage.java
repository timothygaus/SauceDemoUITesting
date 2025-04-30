package pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {

    private String inventoryPageUrl = "https://www.saucedemo.com/inventory.html";

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

    @FindBy(id = "shopping-cart-container")
    WebElement shoppingCartContainer;

    @FindBy(css = "div.app_logo")
    WebElement appLogo;

    @FindBy(css = "span.title")
    WebElement pageTitle;
}
