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


public class YuliiaKTest extends BaseTest {

    private static final String URL = "https://www.predskazanie.ru/";

    @Test
    public void predskazTest() {
        final String expectedResult = "https://www.predskazanie.ru/goldfish/";
        getDriver().get(URL);

        WebElement divinationOfaWish = getDriver().findElement(By.xpath("//div[@id='menu']//a[@title='Гадание на желание']"));
        divinationOfaWish.click();
        WebElement divinationGoldFish = getDriver().findElement(By.xpath("//p[@class='intro-image']//img[@alt='Гадание Золотая Рыбка']"));
        divinationGoldFish.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);

    }

    @Test
    public void predskazTest2() {
        final String expectedResult = "https://www.predskazanie.ru/ekat.shtml";
        getDriver().get(URL);

        WebElement divinationOfaLove = getDriver().findElement(By.xpath("//div[@id='menu']//a[@title='Гадание на любовь и отношения']"));
        divinationOfaLove.click();
        WebElement queenKatya = getDriver().findElement(By.xpath("//div[@class='content-layer']//img[@alt='Гадание императрицы Екатерины']"));
        queenKatya.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);

    }
}
