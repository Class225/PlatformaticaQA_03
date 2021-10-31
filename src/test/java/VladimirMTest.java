import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class VladimirMTest {

    @Test
    public void testSelenium() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.get("https://av.by");

        WebElement bmw = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/main[1]/div[1]" +
                "/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[3]/li[1]/a[1]/span[1]"));

        bmw.click();

        WebElement model = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div" +
                "[4]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/button[1]/span[1]/span[2]"));
        model.click();


        WebElement modelX7 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div" +
                "[4]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[26]/button[1]"));
        modelX7.click();

        WebElement show = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div" +
                "[4]/form[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[2]/button[1]"));
        show.click();

        WebElement car = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]" +
                "/div[6]/div[3]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/h3[1]/a[1]/span[1]"));

        String expectedUrl = "https://cars.av.by/bmw/x7";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }
}