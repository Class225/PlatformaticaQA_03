import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VladimirMTest {

    @Test
    public void testSelenium() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://av.by");

        WebElement bmw = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/main[1]/div[1]/div[1]" +
                "/div[1]/div[2]/div[1]/div[2]/div[1]/ul[3]/li[1]/a[1]/span[1]"));

        bmw.click();

        WebElement show = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div" +
                "[4]/form[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[2]/button[1]"));
        show.click();

//        Thread.sleep(3000);

        String expectedUrl = "https://cars.av.by/bmw";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }
}