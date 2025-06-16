package framework.utils;

import framework.config.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementUtils {

    /**
     * Clicks on the specified WebElement and waits for a specified condition. Selenium's .click() is attempted first,
     * and falls back to a Javascript Executor click method if the specified condition is not met.
     * @param element WebElement to be clicked
     * @param postClickCondition Expected result of the click
     */
    public static void click(WebDriver webDriver, WebElement element, java.util.function.Supplier<Boolean> postClickCondition) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait"))));

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();

            if (postClickCondition != null) {
                wait.until(driver -> postClickCondition.get());
            }
        } catch (Exception e) {
            try {
                // Fallback to JavascriptExecutor click if Selenium's click fails
                JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
                jsExecutor.executeScript("arguments[0].click();", element);

                if (postClickCondition != null) {
                    wait.until(driver -> postClickCondition.get());
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public static void type(WebDriver webDriver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait"))));
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

}
