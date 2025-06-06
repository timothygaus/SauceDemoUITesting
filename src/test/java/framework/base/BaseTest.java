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

    protected WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up the test environment...");
        webDriver = new DriverManager().getDriver();
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Tearing down the test environment...");
        webDriver.quit();
    }

    /**
     * Performs the steps needed to initialize a test up to the point of reaching the inventory page
     * @return InventoryPage
     */
    public InventoryPage initializeToInventoryPage() {
        webDriver.get(Urls.LOGIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Failed to load login page");
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(loginPage, "Failed to navigate to Inventory page after logging in");
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Failed to load the Inventory page after logging in");
        return inventoryPage;
    }
}
