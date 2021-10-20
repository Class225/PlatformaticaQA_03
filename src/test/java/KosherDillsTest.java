import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class KosherDillsTest {

    private WebDriver dills;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        dills = new ChromeDriver();

        dills.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dills.manage().window().maximize();
    }

    @AfterMethod

    public void shutDown() throws InterruptedException {
        dills.quit();
    }

    @Test
    public void testGoogleSearch() {

        dills.get("https://www.google.com");
        dills.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"))
                .sendKeys("RuAmerica" + "\n");

        Assert.assertEquals(dills
                .findElement(By.xpath("//cite[text()='https://ruamerica.com']"))
                .getText(), "https://ruamerica.com");

    }
}
