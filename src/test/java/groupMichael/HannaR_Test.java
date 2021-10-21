package groupMichael;

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

public class HannaR_Test {
    private final String URL = "https://www.flowerchimp.co.id/";

    public static WebDriver driver;

    @BeforeMethod
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterMethod
    public void after(){
        driver.quit();
    }

    @Test
    public void testSingIn(){
        driver.get(URL);
        WebElement SingIn = driver.findElement(By.xpath("//span[text()='Login']"));
        SingIn.click();
        WebElement email = driver.findElement(By.xpath("//div[@id='login_form']/form/input[@type='email']"));
        email.sendKeys("Heli@dmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("12634");
        WebElement login = driver.findElement(By.xpath("//form/input[@class='btn action_button']"));
        login.click();
        String actualResult = driver.findElement(By.xpath("//p[@class='shopify-challenge__message']")).getText();

        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);

        Assert.assertEquals(actualResult, "To continue, let us know you're not a robot.");
    }

    @Test
    public void testCountTextBox(){
        driver.get(URL);
        final String EXPECTED_TEXT = "box";

        WebElement Cakes = driver.findElement(By.xpath("//a[@href='/collections/trinity-collection-flower-shop-flower-chimp'][@class='hidden-product-link']"));
        Cakes.click();
        List<WebElement> itemList = driver.findElements(By.xpath("//span[text()='Trinity Box Deluxe Collection - Dainty Dreams']"));

        for (int i=0; i< itemList.size(); i++){
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(EXPECTED_TEXT));
        }
    }
}
