package framework.base;

import framework.driver.DriverManager;
import framework.utils.Urls;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.InventoryPage;
import pages.LoginPage;

public class BaseTest {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    protected WebDriver getWebdriver() {
        return webDriver.get();
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up the test environment...");
        webDriver.set(new DriverManager().getDriver());
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Tearing down the test environment...");
        getWebdriver().quit();
        webDriver.remove();
    }

    /**
     * Performs the steps needed to initialize a test up to the point of reaching the inventory page
     * @return InventoryPage
     */
    public InventoryPage initializeToInventoryPage() {
        getWebdriver().get(Urls.LOGIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(getWebdriver());
        Assert.assertTrue(loginPage.isPageLoaded(), "Failed to load login page");
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(inventoryPage, "Failed to navigate to Inventory page after logging in");
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Failed to load the Inventory page after logging in");
        return inventoryPage;
    }
}
