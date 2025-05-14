package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverManager;
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

    public InventoryPage initializeToInventoryPage() {
        webDriver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(loginPage, "Failed to navigate to Inventory page after logging in");
        return inventoryPage;
    }
}
