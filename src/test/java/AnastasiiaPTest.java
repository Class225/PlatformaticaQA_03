import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AnastasiiaPTest {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void testCurrentAddressTextField() {
        String expectedResult = "Roses Delivery";
        driver.get("https://www.1800flowers.com/");

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchBox_desktop")));

        driver.findElement(By.id("SearchBox_desktop")).sendKeys("Roses");
        driver.findElement(By.id("btn-search")).click();

        WebElement actualResult = driver.findElement(By.xpath("//div[h1]"));

        Assert.assertEquals(actualResult.getText(), expectedResult);
    }

    @Test
    private void testSearchSubjectTextField() {
        String expectedResult = "SEARCH RESULTS FOR NY DRESS";

        driver.get("https://shop.mango.com/us/women");
        driver.findElement(By.id("search_icon_button")).click();
        driver.findElement(By.xpath("//div//input[@class='search-input']")).sendKeys("NY dress\n");

        WebElement actualResult = driver.findElement(By.xpath("//div[@id = 'title']"));

        Assert.assertEquals(actualResult.getText(), expectedResult);
    }
}

    @Test
    public void testAvailabilityMenuButtons() {
        String[] expectedArray = new String[]{
                "ABOUT OUR ICE CREAM",
                "ORDER ONLINE",
                "SIGNATURE CREATIONS",
                "CREATE YOUR OWN CREATIONS",
                "SUNDAES",
                "MIX & GO",
                "PRE-PACKED",
                "FAMILY PACKS",
                "ICE CREAM FAQ"};

        driver.get("https://www.coldstonecreamery.com/index.php");
        driver.findElement(By.xpath("//div[contains(@class,'close-button')]")).click();
        driver.findElement(By.xpath("//a[contains(@aria-controls,'icecream')]")).click();

        List<WebElement> menuList = driver.findElements(By.xpath("//ul[contains(@id,'icecream')]//li"));
        for (int i = 0; i < menuList.size(); i++) {
            Assert.assertEquals(menuList.get(i).getText(),expectedArray[i]);
        }
    }
}

