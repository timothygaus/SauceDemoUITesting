package pages;

import framework.base.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public CartPage (WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getContinueShoppingButton() {return continueShoppingButton;}

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    public InventoryPage clickContinueShoppingButton() {
        click(getContinueShoppingButton());

        try {
            wait.until(ExpectedConditions.urlContains("inventory"));
        } catch (TimeoutException e) {
            return null;
        }

        return new InventoryPage(webDriver);
    }
}
