package groupMichael;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

@Ignore
public class MichaelKamenTestScript  {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    // useless comment
    @Test
    public void testSearches() {

        driver.get("http://automationpractice.com");
        WebElement input = driver.findElement(By.id("search_query_top"));
        input.sendKeys("dress\n");

        WebElement result = driver.findElement(By.className("lighter"));

        Assert.assertEquals(result.getText(), "\"DRESS\"");

    }

    @Test
    public void testSearchSpecials() {

        driver.get("http://automationpractice.com");
        WebElement section = driver.findElement(By.xpath("//a[@title='Specials']"));
        section.click();
        WebElement result = driver.findElement(By.className("navigation_page"));

        Assert.assertEquals(result.getText(), "Price drop");

    }
    @AfterMethod
    public void setDown(){
        driver.quit();
    }
}



