import base.BaseTest;
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

public class MikhailBtest {


        WebDriver driver;

        @BeforeMethod
        public void setUp() {
                   WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        @AfterMethod
        public void setDown() {
            driver.quit();
        }

        @Test
        public void testSearches() {
            driver.get("https://www.webstaurantstore.com/");
            driver.findElement(By.id("searchval")).sendKeys("table\n");

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.webstaurantstore.com/search/table.html");
 
        }
    }


