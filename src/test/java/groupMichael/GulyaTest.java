package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GulyaTest {
    @Test
    public void testSearches() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://automationpractice.com");
        WebElement input = driver.findElement(By.id("search_query_top"));
        input.sendKeys("DRESS\n");
        WebElement result = driver.findElement(By.className("lighter"));
        Assert.assertEquals(result.getText(), "\"DRESS\"");
    }
}
