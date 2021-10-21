import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class KosherDillsTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod

    public void shutDown() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {

        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//input[@title='Search']"))
                .sendKeys("RuAmerica" + "\n");

        Assert.assertEquals(driver
                .findElement(By.xpath("//cite[text()='https://ruamerica.com']"))
                .getText(), "https://ruamerica.com");

    }
}
