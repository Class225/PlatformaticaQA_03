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

import java.util.concurrent.TimeUnit;

public class GroupMichaelTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void testIgorKomarovVerifyAlert_1() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.xpath("//button[@id='alertButton']")).click();
        Assert.assertEquals(driver.switchTo().alert().getText(), "You clicked a button");
    }

    @Test

    public void testMainPage1 () {
        driver.get("http://automationpractice.com/index.php");
        WebElement singIn = driver.findElement(By.xpath("//div[@class='header_user_info']"));
        singIn.click();
        String title = "Login - My Store";
        Assert.assertEquals(driver.getTitle(), title);
        WebElement email = driver.findElement(By.id("email"));
        WebElement singIn1 = driver.findElement(By.id("SubmitLogin"));
        email.sendKeys("123");
        singIn1.click();
        WebElement error1 = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol"));
        String err1 = "Invalid email address.";
        Assert.assertEquals(error1.getText(), err1);
        WebElement email2 = driver.findElement(By.id("email"));
        WebElement singIn2 = driver.findElement(By.id("SubmitLogin"));
        email2.clear();
        email2.sendKeys("123@gmail.com");
        singIn2.click();
        WebElement error2 = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol"));
        String err2 = "Password is required.";
        Assert.assertEquals(error2.getText(), err2);
        WebElement pass = driver.findElement(By.id("passwd"));
        WebElement singIn3 = driver.findElement(By.id("SubmitLogin"));
        pass.sendKeys("12345");
        singIn3.click();
        WebElement error3 = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol"));
        String err3 = "Authentication failed.";
        Assert.assertEquals(error3.getText(), err3);
    }

    @Test

    public void testMainPage2 () throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement firstname = driver.findElement(By.id("firstName"));
        firstname.sendKeys("123");
        WebElement lastname = driver.findElement(By.id("lastName"));
        lastname.sendKeys("5678");
        WebElement genderF = driver.findElement(By.xpath("//input[@name='gender'][@value='Female']"));
        driver.findElement(By.xpath("//label[contains(text(),'Female')]")).click();
    }
}
