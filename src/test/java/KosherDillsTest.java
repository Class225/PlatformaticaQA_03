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
public class KosherDillsTest {

    private WebDriver driver;
    private final By ERROR = By.xpath("//div[@class='alert alert-info']/b");

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod

    public void shutDown() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {

        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"))
                .sendKeys("RuAmerica\n");

        Assert.assertEquals(driver
                .findElement(By.xpath("//cite[text()='https://ruamerica.com']"))
                .getText(), "https://ruamerica.com");

    }

    @Test
    public void testDropDownSelect() {

        Actions action = new Actions(driver);
        driver.get("https://www.amazon.com");

        WebElement select = driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));
        action.moveToElement(select).perform();
        driver.findElement(By.xpath("//*[@id='nav-al-wishlist']/a[3]")).click();

        Assert.assertEquals(driver
                .findElement(By.xpath("//*[@id='ge-stories']/div[1]/h2"))
                .getText(), "Over 1 million charities. See their stories.");
    }

    @Test
    public void testDoubleDropDownSelect () {

        driver.get("https://bidfax.info/");

        driver.findElement(By.xpath("(//span[@class='drop-down'])[1]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Aston martin']")).click();
        driver.findElement(By.xpath("(//span[@class='drop-down'])[2]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Db9']")).click();

            for (int i = 0; i < Integer.MAX_VALUE; i++) {
            List<WebElement> cars = driver.findElements(By.xpath("//div[@class='caption']/a/h2"));
            Assert.assertTrue(carsCheck(cars));
            driver.findElement(By.xpath("//a[normalize-space()='»']")).click();
                if (driver.findElements(ERROR).size() > 0) break;
        }
    }

    public boolean carsCheck (List<WebElement> var1){
        int trueCount = 0;
        for (int i = 0; i < var1.size(); i++) {
            if (var1.get(i).getText().toLowerCase().contains("db9"))
                trueCount++;
        }
        if (trueCount == var1.size()){return true;}
        return false;
    }

    @Test
    public void testBatterySelect () {
        driver.get("https://rbc.sm.ua/");
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("apc 500\n");

        List<WebElement> upsList = driver.findElements(By.xpath("//div[@class='us-module-title']/a"));
        for (int i = 0; i < upsList.size(); i++) {

            Assert.assertTrue(upsList.get(i).getText().toLowerCase().contains("500"));
        }
    }





}
