import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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

    public void shutDown() throws InterruptedException {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {

        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"))
                .sendKeys("RuAmerica\n");

        Assert.assertEquals(driver
                .findElement(By.xpath("//cite[text()='https://ruamerica.com']"))
                .getText(), "https://ruamerica.com");

    }

    @Test
    public void testDropDownSelect() {

        Actions action = new Actions(driver);
        driver.get("https://www.amazon.com");

        WebElement select = driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));
        action.moveToElement(select).perform();
        driver.findElement(By.xpath("//*[@id='nav-al-wishlist']/a[3]")).click();

        Assert.assertEquals(driver
                .findElement(By.xpath("//*[@id='ge-stories']/div[1]/h2"))
                .getText(), "Over 1 million charities. See their stories.");
    }
}
