import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class KorenevskyyTest {

    private WebDriver driver;
    private String baseUrl = "https://www.brandfarmsmn.com/";

    @BeforeMethod
    public void setDriver() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quiteDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    public void farmAppleLinkTest() {
        String expected = "Apples!";
        driver.get(baseUrl);

        driver.findElement(By.xpath("//a[@target='_self']")).click();
        driver.findElement(By.xpath("//a[@href='apples.html']")).click();

        String actual = driver.findElement(By.xpath("//span[@class='emphasize']")).getText();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void farmEggsLinkTest() {

        driver.get(baseUrl);
        driver.findElement(By.xpath("//a[@href='eggs.html']")).click();
        WebElement eggsImage = driver.findElement(By.xpath("//img[@src='./images/eggsincartonweb.jpg']"));

        Assert.assertTrue(eggsImage.isDisplayed());
    }

    @Test
    public void DemoQAelementsTest() {

        driver.get("https://demoqa.com/");
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.findElement(By.xpath("//h5[contains(text(), 'Elements')]\n"));

        WebElement headerImage = driver.findElement(By.xpath("//img[@src='/images/Toolsqa.jpg']"));
        Assert.assertTrue(headerImage.isDisplayed());
    }
}
