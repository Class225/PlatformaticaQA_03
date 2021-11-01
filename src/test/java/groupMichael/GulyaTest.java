package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GulyaTest {
    @Test
    public void testSearches() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://automationpractice.com");
        WebElement input = driver.findElement(By.id("search_query_top"));
        input.sendKeys("DRESS\n");
        WebElement result = driver.findElement(By.className("lighter"));
        Assert.assertEquals(result.getText(), "\"DRESS\"");
    }

    @Test
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/gulidieva/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        String Url =("https://www.economist.com");
         driver.get(Url);
         String title =driver.getTitle();
        int titleLength = driver.getTitle().length();
        System.out.println(" Title of the page is " + title);
        System.out.println(" Length of the title is" + titleLength);
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(Url)) {
            System.out.println("Success");
        } else {
            System.out.println("incorrect");
            System.out.println("Actual url is" + actualUrl);
            String pageSource = driver.getPageSource();
            int pageSourceLength = driver.getPageSource().length();
            System.out.println(" Total pageSource is" + pageSourceLength);
            driver.quit();
        }
    }}
