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
import java.util.concurrent.TimeUnit;

@Ignore
public class IrinaB_Test {

    private WebDriver driver;
    String url = "https://www.exeloncorp.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void setDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void irinaBTest() {
        String expectedResult = "https://www.exeloncorp.com/grid";

        driver.get(url);

        WebElement gridTest = driver.findElement(By.xpath("//div[@id='exelon-main-nav']" +
                "/ul[@class='nav navbar-nav navbar-right']/li/a[@href='/grid']"));
        gridTest.click();
        WebElement innovationTest = driver.findElement(By.xpath("//form[@id='aspnetForm']/div//div//div//div/div//" +
                "ul[@id='topNav']/li[4]/a[\"Innovation\"]"));
        innovationTest.click();

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }
}


