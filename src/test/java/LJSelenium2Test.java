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

import static org.testng.Assert.assertEquals;

public class    {
    private static final String URL =
        "http://www.diamondpeak.com/";
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void testHomePage() {
        driver.get(URL);
        WebElement title = driver.findElement(By.xpath("/html/body/div/nav/a[1]"));
        title.getText();
        WebElement snowReport = driver.findElement(By.xpath("//a[@class='bar-item bar-forecast']"));
        WebElement direction = driver.findElement(By.xpath("//a[@class='bar-item bar-directions']"));
        WebElement tickets = driver.findElement(By.xpath("//a[@class='bar-item bar-tickets']"));
    }
    @AfterMethod
    public void quitTest(){
        driver.quit();
    }


}


