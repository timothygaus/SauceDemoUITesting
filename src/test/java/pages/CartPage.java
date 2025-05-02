package pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    public CartPage (WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


}
