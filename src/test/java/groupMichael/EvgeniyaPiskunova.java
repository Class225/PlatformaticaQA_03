package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class EvgeniyaPiskunova {

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
    public void testEvgeniyaPiskunovaCartEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://sunduchokknig.com/cart");
        String expetedResult = "Ваша корзина пуста";
        String actual = driver
                .findElement(By.xpath("//*[@class='cart--empty-message']"))
                .getText();
        Assert.assertEquals(actual, expetedResult);
    }
}

