package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Ignore
public class VictorySmile {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testVictorySmileSearchResult() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        driver.get("http://automationpractice.com");

        WebElement input = driver.findElement(By.id("search_query_top"));
        input.sendKeys("dress\n");

        WebElement result = driver.findElement(By.className("lighter"));
        Assert.assertEquals(result.getText(), "\"DRESS\"");
    }
}
