import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VladimirMTest {
    @Test
    public void testSelenium() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://av.by/login");

        WebElement tab = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div/div/div/div/div[1]/div/div[1]/button[2]"));
        tab.click();

        WebElement username = driver.findElement(By.id("login"));
        WebElement password = driver.findElement(By.xpath("//body/div[@id='__next']/div[2]/main[1]/div[1]/div[1]/div[1]" +
                "/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/div[2]/input[1]"));
        WebElement login = driver.findElement(By.xpath("//body/div[@id='__next']/div[2]/main[1]/div[1]/div[1]/div[1]/" +
                "div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/button[1]"));

//        Необходима регистрация
//        username.sendKeys("--------");
//        password.sendKeys("--------");

        login.click();
//        Thread.sleep(3000);

        String expectedUrl = "https://av.by/";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }

}
