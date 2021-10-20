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

public class GroupTimurTest {

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
    public void testSvitlanaHoshaw() {
        driver.get("https://www.merriam-webster.com/");
        WebElement searchForWord = driver.findElement(By.id("s-term"));
        WebElement searchButton = driver.findElement(By.xpath("//div[@class=\"nav-search-btn desk-search-btn\"]"));

        searchForWord.sendKeys("value");
        searchButton.click();

        String actualResult = driver.getCurrentUrl();
        String expectedResult = "https://www.merriam-webster.com/dictionary/value";
        Assert.assertEquals(actualResult, expectedResult);
    }


}