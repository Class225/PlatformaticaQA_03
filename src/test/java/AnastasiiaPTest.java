import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        driver.findElement(By.id("SearchBox_desktop")).sendKeys("Roses \n");
        driver.findElement(By.id("btn-search"));

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


