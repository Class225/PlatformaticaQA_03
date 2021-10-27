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

import java.util.concurrent.TimeUnit;

public class GalinaKTest extends BaseTest {

    private static final String URL = "https://balloonfiesta.com/";

    @Test
    public void testBaloonFiestaGalinaK() {
        getDriver().get(URL);

        WebElement menuNews = getDriver().findElement(By.xpath("//a[@href='News-Updates']"));
        menuNews.click();

        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() != 1;
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        WebElement newsTitle = getDriver().findElement(By.xpath("//h2[contains(text(),'News & Updates')]"));
        String searchResultText = newsTitle.getText();

        Assert.assertEquals(searchResultText, "News & Updates");
    }

    @Test
    public void testSearchGalinaK() {
        getDriver().get(URL);

        WebElement searchButton = getDriver().findElement(By.xpath("//div[contains(text(),'Search')]"));
        searchButton.click();

        WebElement searchInput = getDriver().findElement(By.xpath("//input[@name='q']"));
        searchInput.sendKeys("tickets\n");

        WebElement searchTitle = getDriver().findElement(By.xpath("//h2[contains(text(),'Searched for: tickets')]"));
        String searchResultText = searchTitle.getText();

        Assert.assertEquals(searchResultText, "Searched for: tickets");
    }
}
