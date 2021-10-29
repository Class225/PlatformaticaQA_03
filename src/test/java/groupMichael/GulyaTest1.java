package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GulyaTest1 {
    @Test
    public void testFirst() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://www.99-bottles-of-beer.net/";
        String expectedResult = "https://www.99-bottles-of-beer.net/";


        driver.get(url);
        Thread.sleep(3000);
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
    }
}