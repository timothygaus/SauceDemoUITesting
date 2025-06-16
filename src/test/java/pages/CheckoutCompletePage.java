package pages;

import framework.base.BasePage;
import framework.utils.Urls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.MenuComponent;

import static framework.utils.WebElementUtils.click;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver webDriver) {
        super(webDriver);
        this.menuComponent = new MenuComponent(webDriver);
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

    private final MenuComponent menuComponent;

    public WebElement getBurgerMenuBtn() {return burgerMenuBtn;}
    public WebElement getAppLogo() {return appLogo;}
    public WebElement getShoppingCartContainer() {return shoppingCartContainer;}
    public WebElement getShoppingCartBadge() {return shoppingCartBadge;}
    public WebElement getSecondaryHeaderTitle() {return secondaryHeaderTitle;}
    public WebElement getPonyExpressImage() {return ponyExpressImage;}
    public WebElement getCompleteHeader() {return completeHeader;}
    public WebElement getCompleteText() {return completeText;}
    public WebElement getBackToProductsButton() {return backToProductsButton;}
    public MenuComponent getMenuComponent() {return menuComponent;}

    /**
     * Checks that the checkout complete page is loaded by checking the URL and key WebElements are displayed.
     * @return true if the URL contains CHECKOUT_COMPLETE_PAGE, the complete header is displayed, the pony express image
     * is displayed, and the back to products button is displayed.
     */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains(Urls.CHECKOUT_COMPLETE_PAGE));
            wait.until(ExpectedConditions.visibilityOf(getCompleteHeader()));
            wait.until(ExpectedConditions.visibilityOf(getPonyExpressImage()));
            wait.until(ExpectedConditions.visibilityOf(getBackToProductsButton()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks the back home button and navigates to the inventory page
     * @return InventoryPage
     */
    public InventoryPage clickBackHomeButton() {
        click(webDriver, getBackToProductsButton(), () -> new InventoryPage(webDriver).isPageLoaded());
        return new InventoryPage(webDriver);
    }
}
