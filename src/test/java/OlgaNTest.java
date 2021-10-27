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

public class OlgaNTest {

        WebDriver driver;

        @BeforeMethod
        public void setUp() {

            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        @AfterMethod
        public void testDown() {
            driver.quit();

        }

        @Test
        public void goToContactPage(){


            driver.get("https://schools.cms.k12.nc.us/elizabethlaneES/Pages/Default.aspx");

            WebElement contact = driver.findElement(By.xpath("//*[contains(text(), 'Contact CMS')]"));

            contact.click();

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.cms.k12.nc.us/communications/aboutus/Pages/Contact-Us.aspx");

        }
}
