package pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "div.app_logo")
    WebElement appLogo;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

    @FindBy(css = "div.shopping_cart_container")
    WebElement shoppingCartContainer;

    @FindBy(css = "span.shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(css = "span.title")
    WebElement secondaryHeaderTitle;

    @FindBy(css = "img.pony_express")
    WebElement ponyExpressImage;

    @FindBy(css = "h2.complete-header")
    WebElement completeHeader;

    @FindBy(css = "div.complete-text")
    WebElement completeText;

    @FindBy(id = "back-to-products")
    WebElement backToProductsButton;

    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getAppLogo() {return appLogo;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getPonyExpressImage() {return ponyExpressImage;}
    public WebElement getCompleteHeader() {return completeHeader;}
    public WebElement getCompleteText() {return completeText;}
    public WebElement getBackToProductsButton() {return backToProductsButton;}

    /**
     * Clicks the back home button and navigates to the inventory page
     * @return InventoryPage
     */
    public InventoryPage clickBackHomeButton() {
        click(getBackToProductsButton(), () -> webDriver.getCurrentUrl().contains("inventory"));
        return new InventoryPage(webDriver);
    }
}
