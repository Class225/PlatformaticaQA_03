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
import static org.testng.Assert.assertEquals;

public class HWSelenium {
    private static final String URL =
   "http://automationpractice.com/";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void testHomePage() {
        driver.get(URL);
        WebElement input = driver.findElement(By.id("search_query_top"));
        input.sendKeys("dress\n");
        WebElement result = driver.findElement(By.className("lighter"));

        assertEquals(result.getText(),"\"DRESS\"");
    }
    @AfterMethod
    public void quitTest(){
    driver.quit();
    }

}
