import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimurGrBikeTest {
    private   static  final String URL = "https://www.trekbikes.com/";
    private WebDriver driver;
    private static final By ITEM=By.xpath("//*[@class=\"product-tile__title\"]");

    @BeforeMethod
    public  void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }
    @AfterMethod
    public  void setDown(){driver.quit();}

    @Test
    public void testSearches() throws AWTException, InterruptedException {
        //Открываем сайт
        driver.get(URL);


        // Ищем элемент bikes
        WebElement bikes= driver.findElement(By.xpath("//*[@id='expandBikesMenu-large']"));
        bikes.click();

        // Ищем элемент 29 inch
        WebElement eanch29 = driver.findElement(By.xpath("(//span[text()='29er bikes'])[2]"));
        eanch29.click();

        // Ищем элемент поле
        WebElement search = driver.findElement(By.xpath("(//*[@qaid=\"input_SearchBox\"])[2]"));

        //Вводим текст марлин
        search.sendKeys("Marlin");
        search.sendKeys(Keys.ENTER);

        //Просим показать все элементы с лэйбой Marlin
        WebElement SeeBikes= driver.findElement(By.xpath("//*[@qaid=\"link_marqueeEbikeB507\"]"));
        SeeBikes.click();
        //Прогнать страницу вниз
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
        Thread.sleep(1000);


        //Про гнать страницу вверх
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyRelease(KeyEvent.VK_PAGE_UP);


        //Открыть Price
        WebElement priceOpen=driver.findElement(By.xpath("//*[@class=\"plp-sidebar__form open\"]/li[4]"));
        priceOpen.click();


        //Закрыть прайс
        WebElement priceclose = driver.findElement(By.xpath("//*[@class='plp-sidebar__form open']/li[4]//*[@class='accordion-title text-default--bold']"));
        priceclose.click();


        //Открыть опять прайс

        WebElement priceOpen2=driver.findElement(By.xpath("//*[@class=\"plp-sidebar__form open\"]/li[4]"));
        priceOpen2.click();


        //Выбираем price.
        WebElement pricefinal=driver.findElement(By.xpath("//*[@class=\"plp-sidebar__form open\"]/li[4]//*[text()='$500-$999.99']"));





        // Считаем элементы
        List<WebElement> itemList = driver.findElements(ITEM);
        for (int i = 0; i< itemList.size();i++) {
            System.out.println(itemList.get(i));

            System.out.println(i);

            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains("marlin"));
            Assert.assertEquals(7,7);
        }











    }

}
