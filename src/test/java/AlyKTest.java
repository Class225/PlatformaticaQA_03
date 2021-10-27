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

public class AlyKTest {

    private WebDriver driver;
    private final String KOHILS = "https://www.kohls.com/";

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
    public void confirmUrlTest() {

        driver.get(KOHILS);
        String expectedResult = "https://www.kohls.com/";

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void SearchHeaderTest() {
        driver.get(KOHILS);

        WebElement shopByDepartmentButton = driver.findElement(By.xpath("//span[@class='middle-menu-title']"));
        shopByDepartmentButton.click();

        WebElement holidayGiftShopButton = driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[2]/h2/a"));
        holidayGiftShopButton.click();

        WebElement giftsButton = driver.findElement(By.xpath("//*[@id=\"dcp-project\"]/div[1]/section/a[1]/div/p[3]"));
        giftsButton.click();

        WebElement giftsForWomenButton = driver.findElement(By.xpath("//*[@id=\"vn-box\"]/ul/li[2]/a/p"));
        giftsForWomenButton.click();

        WebElement clothingButton = driver.findElement(By.xpath("//*[@id=\"vn-box\"]/ul/li[1]/a/p"));
        clothingButton.click();
    }
}
