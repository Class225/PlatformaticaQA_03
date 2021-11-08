package old;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GroupTimurNewTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize(); // раскрыли браузер на полный экран

    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testSvetlanaKanel() {
        driver.get("https://netology.ru");

        WebElement beginnersLink  = driver.findElement(By.xpath("//a[contains(@class, 'module__neo--mKScR')]"));

        beginnersLink.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://netology.ru/neo");

    }
}
