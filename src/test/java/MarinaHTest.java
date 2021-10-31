import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class MarinaHTest extends BaseTest {

          @Test
    public void testMarinaHort() throws InterruptedException {
        String expectedResult = "https://my-learning.w3schools.com/";

        getDriver().get("https://www.w3schools.com/");

        WebElement logInButton = getDriver().findElement(By.xpath("//div/a[@id='w3loginbtn']"));
        logInButton.click();

        WebElement emailField = getDriver().findElement(By.xpath("//input[@class='_ZsgaF undefined']"));
        emailField.sendKeys("marinahortobagyi66@gmail.com");

        WebElement password = getDriver().findElement(By.xpath("//input[@class='_lEWNL']"));
        password.sendKeys("Mandula66!");

        WebElement logInButton1 = getDriver().findElement(By.xpath("//button[@class='_1VfsI _OD95i _3_H0V']"));
        logInButton1.click();

        Thread.sleep(7000);
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
