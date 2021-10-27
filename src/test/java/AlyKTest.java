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

public class AlyKTest extends BaseTest {

    private final String KOHILS = "https://www.kohls.com/";

    @Test
    public void confirmUrlTest() {

        getDriver().get(KOHILS);
        String expectedResult = "https://www.kohls.com/";

        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void SearchHeaderTest() {
        getDriver().get(KOHILS);

        WebElement shopByDepartmentButton = getDriver().findElement(By.xpath("//span[@class='middle-menu-title']"));
        shopByDepartmentButton.click();

        WebElement holidayGiftShopButton = getDriver().findElement(By.xpath("//*[@id=\"navigation\"]/li[2]/h2/a"));
        holidayGiftShopButton.click();

        WebElement giftsButton = getDriver().findElement(By.xpath("//*[@id=\"dcp-project\"]/div[1]/section/a[1]/div/p[3]"));
        giftsButton.click();

        WebElement giftsForWomenButton = getDriver().findElement(By.xpath("//*[@id=\"vn-box\"]/ul/li[2]/a/p"));
        giftsForWomenButton.click();

        WebElement clothingButton = getDriver().findElement(By.xpath("//*[@id=\"vn-box\"]/ul/li[1]/a/p"));
        clothingButton.click();
    }
}
