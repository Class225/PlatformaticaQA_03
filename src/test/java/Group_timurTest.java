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

public class Group_timurTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown(){
        driver.close();
        driver.quit();
    }

    @Test
    public void testSearchesVasiliyTsoy(){
        driver.get("https://kg.wildberries.ru/?gclid=CjwKCAjw2bmLBhBREiwAZ6ugoxyz4_xwL9mqLYvjYS7SaN2o-kcaCCKKEs4m-u152H-Z8e9_WRPs4RoCFiIQAvD_BwE");

        driver.findElement(By.id("searchInput")).sendKeys("ноутбук\n");
        List<WebElement> itemList = driver.findElements(By.xpath("div[@class= 'catalog-page__content']//div[@class='product-card-list']"));
        for (WebElement webElement : itemList) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains("ноутбук"));
        }
    }

    @Test
    public void testLoginVasiliyTsoy(){
        String expectedResult = "Введите код с картинки";

        driver.get("https://kg.wildberries.ru/");
        driver.findElement(By.xpath("//div[@class='navbar-pc__item']/a[@class='navbar-pc__link j-main-login']")).click();
        driver.findElement(By.className("input-item")).sendKeys("852111222");
        driver.findElement(By.id("requestCode")).click();

        WebElement actualResult = driver.findElement(By.xpath("//div[@class='login__captcha form-block form-block--captcha']//*[text()='Введите код с картинки']"));
        Assert.assertEquals(actualResult.getText(),expectedResult);
    }
}
