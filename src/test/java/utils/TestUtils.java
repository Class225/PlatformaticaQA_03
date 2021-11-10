package utils;

import org.openqa.selenium.*;

public class TestUtils {

    public static void jsClick(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollClick(WebDriver driver, WebElement element) {
        scroll(driver, element);
        element.click();
    }

    public static void scrollClick(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        scroll(driver, element);
        element.click();
    }
}
