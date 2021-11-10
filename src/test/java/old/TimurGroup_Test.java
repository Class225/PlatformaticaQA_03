package old;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Ignore
public class TimurGroup_Test {

    private static final String URL = ("https://www.webstaurantstore.com/");

    private WebDriver driver;

    @BeforeMethod
    public void SetUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void SetDown() {
        driver.quit();
    }

    @Test
    public void testSearches(){
        driver.get(URL);

        final String text = "chair";

        WebElement furniture = driver.findElement(By.xpath("//a[@href = '/restaurant-furniture.html']"));
        furniture.click();

        WebElement chairs = driver.findElement(By.xpath("//a[@href = '/42529/restaurant-chairs.html']"));
        chairs.click();

        List<WebElement> itemList = driver.findElements(By.xpath("//a[@data-testid = 'itemDescription']"));
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(text));
        }
    }
}