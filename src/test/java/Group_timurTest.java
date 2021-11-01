
import base.BaseTest;
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


public class Group_timurTest extends BaseTest {

    private final String URL = "https://kg.wildberries.ru/";


    @Test
    public void testSearchesVasiliyTsoy(){
        getDriver().get(URL);

        getDriver().findElement(By.id("searchInput")).sendKeys("ноутбук\n");
        List<WebElement> itemList = getDriver().findElements(By.xpath("div[@class= 'catalog-page__content']//div[@class='product-card-list']"));
        for (WebElement webElement : itemList) {

            Assert.assertTrue(webElement.getText().toLowerCase().contains("ноутбук"));
        }
    }

    @Test
    public void testLoginVasiliyTsoy(){
        getDriver().get(URL);

        WebElement login = getDriver().findElement(By.xpath("//div[@class='navbar-pc__item']/a[@class='navbar-pc__link j-main-login']"));
        login.click();
        WebElement numberInput = getDriver().findElement(By.className("input-item"));
        numberInput.sendKeys("852111222");
        getDriver().findElement(By.id("requestCode")).click();

        WebElement actualResult = getDriver().findElement(By.xpath("//div[@class='login__captcha form-block form-block--captcha']//*[text()='Введите код с картинки']"));
        Assert.assertEquals(actualResult.getText(),"Введите код с картинки");
    }

    @Test
    public void testCheckingCurrencySwitchingVasiliyTsoy(){
       getDriver().get(URL);
        final String value = "Казахстан";

        Actions action = new Actions(getDriver());
        WebElement countrySelection = getDriver().findElement(By.xpath("//span[@class='simple-menu__flag flag-kg']"));
        action.moveToElement(countrySelection).build().perform();
        List<WebElement> listOfCountries = getDriver().findElements(By.xpath("//*[@class='country__item']/label/span[2]"));
        for(WebElement county:listOfCountries){
            if(county.getText().contains(value)){
               county.click();
               break;
            }
        }
        List<WebElement> listItem = getDriver().findElements(By.xpath("//p[@class='goods-card__price-localized']"));
        for(WebElement webElement : listItem){
                Assert.assertTrue(webElement.getText().toLowerCase().contains("тг"));
            }
    }
}
