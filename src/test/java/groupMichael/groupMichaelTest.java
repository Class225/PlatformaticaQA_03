package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookStoreHomePage;

import java.util.concurrent.TimeUnit;

public class groupMichaelTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void testIgorKomarovVerifyAlert_1() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.xpath("//button[@id='alertButton']")).click();
        Assert.assertEquals(driver.switchTo().alert().getText(), "You clicked a button");
    }

}
