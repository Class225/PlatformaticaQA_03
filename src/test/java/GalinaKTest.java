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

public class GalinaKTest {
    private static final String URL = "https://balloonfiesta.com/";

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
    public void testBaloonFiestaGalinaK() {
        driver.get(URL);

        WebElement menuNews = driver.findElement(By.xpath("//a[@href='News-Updates']"));
        menuNews.click();

        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() != 1;
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement newsTitle = driver.findElement(By.xpath("//h2[contains(text(),'News & Updates')]"));
        String searchResultText = newsTitle.getText();

        Assert.assertEquals(searchResultText, "News & Updates");
    }

    @Test
    public void testSearchGalinaK() {
        driver.get(URL);

        WebElement searchButton = driver.findElement(By.xpath("//div[contains(text(),'Search')]"));
        searchButton.click();

        WebElement searchInput = driver.findElement(By.xpath("//input[@name='q']"));
        searchInput.sendKeys("tickets\n");

        WebElement searchTitle = driver.findElement(By.xpath("//h2[contains(text(),'Searched for: tickets')]"));
        String searchResultText = searchTitle.getText();

        Assert.assertEquals(searchResultText, "Searched for: tickets");
    }
}
