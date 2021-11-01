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

public class ArtemChTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void register()  {
        driver.get("https://fantasy.premierleague.com/");
        WebElement email = driver.findElement(By.id("loginUsername"));
        email.sendKeys("first@gmail.com");
        WebElement password = driver.findElement(By.id("loginPassword"));
        password.sendKeys("password");
        WebElement button = driver.findElement(By.cssSelector("#root > div:nth-child(2) > div > div > div.sc-bdnxRM.hbrYOM > form > div.Login__LoginButtonWrap-sc-1dpiyoc-3.gJIZgq > button > svg"));
        button.click();

        WebElement error = driver.findElement(By.cssSelector("#root > div:nth-child(2) > div > div > div.sc-bdnxRM.hbrYOM > div:nth-child(1) > div > div"));
        Assert.assertEquals(error.getText(),"Incorrect email or password");
    }
}
