
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

@Ignore
public class Group_timurTest {
    private WebDriver driver;
    private final String URL = "https://kg.wildberries.ru/";

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }

    @Test
    public void testSearchesVasiliyTsoy(){
        driver.get(URL);

        driver.findElement(By.id("searchInput")).sendKeys("ноутбук\n");
        List<WebElement> itemList = driver.findElements(By.xpath("div[@class= 'catalog-page__content']//div[@class='product-card-list']"));
        for (WebElement webElement : itemList) {

            Assert.assertTrue(webElement.getText().toLowerCase().contains("ноутбук"));
        }
    }

    @Test
    public void testLoginVasiliyTsoy(){
        driver.get(URL);

        WebElement login = driver.findElement(By.xpath("//div[@class='navbar-pc__item']/a[@class='navbar-pc__link j-main-login']"));
        login.click();
        WebElement numberInput = driver.findElement(By.className("input-item"));
        numberInput.sendKeys("852111222");
        driver.findElement(By.id("requestCode")).click();

        WebElement actualResult = driver.findElement(By.xpath("//div[@class='login__captcha form-block form-block--captcha']//*[text()='Введите код с картинки']"));
        Assert.assertEquals(actualResult.getText(),"Введите код с картинки");
    }

    @Test
    public void testCheckingCurrencySwitchingVasiliyTsoy(){
        driver.get(URL);
        final String value = "Казахстан";

        Actions action = new Actions(driver);
        WebElement countrySelection = driver.findElement(By.xpath("//span[@class='simple-menu__flag flag-kg']"));
        action.moveToElement(countrySelection).build().perform();
        List<WebElement> listOfCountries = driver.findElements(By.xpath("//*[@class='country__item']/label/span[2]"));
        for(WebElement county:listOfCountries){
            if(county.getText().contains(value)){
               county.click();
               break;
            }
        }
        List<WebElement> listItem =driver.findElements(By.xpath("//p[@class='goods-card__price-localized']"));
        for(WebElement webElement : listItem){
                Assert.assertTrue(webElement.getText().toLowerCase().contains("тг"));
            }
    }
}
