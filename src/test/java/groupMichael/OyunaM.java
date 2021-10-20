package groupMichael;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookStoreHomePage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OyunaM {

    String chromeDriver = "webdriver.chrome.driver";
    String driverPath = "D:/workJava/chromedriver.exe";
    String URL = "https://biologie.ru/";
    String expectedResult = "https://www.instagram.com/alehina_ekaterina/";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty(chromeDriver, driverPath);

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testOyunaM_openInstagramTest() {

        driver.get(URL);

        WebElement element = driver.findElement(By.xpath("//div[@data-elem-id='1474624869390']"));
        element.click();

        Set<String> newWindowsSet = driver.getWindowHandles();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        Assert.assertEquals(driver.getCurrentUrl(), expectedResult);
    }

}


