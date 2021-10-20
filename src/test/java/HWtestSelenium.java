
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class HWtestSelenium {
    private WebDriver driver;

    @BeforeMethod
    public void setDriver(){
        System.setProperty("webdriver.chrome.driver", "/Users/lisajohns/Downloads/chromedriver");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    /*@Test
    public void openPage(){
        driver.get("https://www.eventbrite.com/");
        WebElement title = driver.findElement(By.xpath("//span[@class='eds-is-hidden-accessible']"));
       // Assert.assertEquals(title.getText(),"Eventbrite");
        // WebElement element = driver.findElement(By.xpath("consumer-header__logo-link"));
    }
     */
    @Test
    public void testSearches() {
        driver.get("http://automationpractice.com/");

        WebElement input = driver.findElement(By.id("search_query_top"));
        input.sendKeys("dress\n");
        WebElement result = driver.findElement(By.className("lighter"));

        assertEquals(result.getText(),"\"DRESS\"");
    }
    @Test
    public void testRedirectionTshirts() {
        driver.get("http://automationpractice.com/");
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"uniform-selectProductSort\"]")).click();
       // driver.findElement(By.xpath("//*[@id=\"selectProductSort\"]/option[3]")).click();


       // WebElement minimumPriceItem = driver.findElement(By.className("cat-name"));
      //  Assert.assertEquals(minimumPriceItem.getText(),"T-SHIRTS");
    }
    @Test
    public void testRedirectionWomen() {
        driver.get("http://automationpractice.com/");
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();

        WebElement women = driver.findElement(By.xpath("//*[text()='T-shirts']"));
                //(By.xpath("//*[@id=\"center_column\"]/h1/span[1]"));

        //String women1 = women.getText();

        //assertEquals(women.getText(),"WOMEN");

       // WebElement minimumPriceItem = driver.findElement(By.className("cat-name"));
        //  Assert.assertEquals(minimumPriceItem.getText(),"T-SHIRTS");
    }

    @AfterMethod
    public void quitTest(){
       driver.quit();
    }

}

