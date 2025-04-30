package framework.base;

import framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
}
