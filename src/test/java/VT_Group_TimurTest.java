
import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;


public class VT_Group_TimurTest extends BaseTest {

    private final String URL = "https://kg.wildberries.ru/";
    private final String URL_GIT_HUB = "https://github.com/SergeiDemyanenko/PlatformaticaQA_03";


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

        getDriver().findElement(By.xpath("//div[@class='navbar-pc__item']/a[@class='navbar-pc__link j-main-login']")).click();
        getDriver().findElement(By.className("input-item")).sendKeys("852111222");
        WebElement getCodeButton = getDriver().findElement(By.id("requestCode"));
        getWait().until(ExpectedConditions.elementToBeClickable(getCodeButton));
        getCodeButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//h2[@class='sign-in-page__title']"));
        Assert.assertEquals(actualResult.getText(),"Войти или создать профиль");
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
    @Test
    public void clickButtonStar(){
        getDriver().get(URL_GIT_HUB);


        getDriver().findElement(By.xpath("//li/a[@class='tooltipped tooltipped-s btn-with-count btn-sm btn']")).click();

        WebElement actualResult = getDriver().findElement(By.xpath("//form[@action='/session']/label"));

        Assert.assertEquals(actualResult.getText(),"Username or email address");
    }
    @Test
    public void clickButtonFork(){
        getDriver().get(URL_GIT_HUB);

        getDriver().findElement(By.xpath("//div/a[@class='tooltipped tooltipped-s btn-with-count btn-sm btn']")).click();

        WebElement actualResult = getDriver().findElement(By.xpath("//div[@class='position-relative']/label"));

        Assert.assertEquals(actualResult.getText(),"Password");
    }
    @Test
    public void clickButtonIssues(){
        getDriver().get(URL_GIT_HUB);

        getDriver().findElement(By.xpath("//span[@data-content='Issues']")).click();

        WebElement actualResult = getDriver().findElement(By.xpath("//div[@class='blankslate blankslate-large blankslate-spacious']/h3"));

        Assert.assertEquals(actualResult.getText(),"There aren’t any open issues.");
    }
    @Test
    public void clickButtonPullRequests(){
        getDriver().get(URL_GIT_HUB);

        getDriver().findElement(By.xpath("//span[@data-content='Pull requests']")).click();

        WebElement actualResult = getDriver().findElement(By.xpath("//span[@class='d-none d-md-block']"));

        Assert.assertEquals(actualResult.getText(),"New pull request ");
    }
}
