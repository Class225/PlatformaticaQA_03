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
import java.util.concurrent.TimeUnit;

public class SergeiDTest {

    private static final String URL = "https://www.webstaurantstore.com/";

    private WebDriver driver;

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
    public void testSearches() {
        driver.get(URL);

        final String text = "table";
        driver.findElement(By.id("searchval")).sendKeys(text + "\n");

        List<WebElement> itemList = driver.findElements(By.xpath("//a[@data-testid = 'itemDescription']"));
        Assert.assertTrue(itemList.size() != 0);
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(text));
         //   System.out.println(itemList.get(i));
        }
    }

}
