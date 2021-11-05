package old;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
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

    @Test
    public void testHistoryOfBaloonFiestaGalinaK() {
        getDriver().get(URL);

        WebElement balooning = getDriver().findElement(By.xpath("//div[@class = 'mainMenu']//child::a//span[text()='Ballooning']"));
        balooning.click();
        WebElement hotAir = getDriver().findElement(By.xpath("//div[@class = 'mainMenu']//child::a//span[text()='Hot Air']"));
        hotAir.click();
        WebElement historyOfBallooning = getDriver().findElement(By.xpath("//div[@class = 'mainMenu']//span[text() = 'History of Ballooning']"));
        historyOfBallooning.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://balloonfiesta.com/Hot-Air-History");
    }
}
