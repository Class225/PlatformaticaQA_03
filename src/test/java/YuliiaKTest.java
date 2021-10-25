import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YuliiaKTest {

    private static final String URL = "https://www.predskazanie.ru/";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void setDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void predskazTest() throws InterruptedException {

        final String expectedResult = "https://www.predskazanie.ru/goldfish/";

        driver.get(URL);
        Thread.sleep(3000);

        WebElement divinationOfaWish = driver.findElement(By.xpath("//div[@id='menu']//a[@title='Гадание на желание']"));
        divinationOfaWish.click();
        WebElement divinationGoldFish = driver.findElement(By.xpath("//p[@class='intro-image']//img[@alt='Гадание Золотая Рыбка']"));
        divinationGoldFish.click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedResult);

    }

    @Test
    public void predskazTest2() throws InterruptedException {

        final String expectedResult = "https://www.predskazanie.ru/ekat.shtml";

        driver.get(URL);
        Thread.sleep(3000);

        WebElement divinationOfaLove = driver.findElement(By.xpath("//div[@id='menu']//a[@title='Гадание на любовь и отношения']"));
        divinationOfaLove.click();
        WebElement queenKatya = driver.findElement(By.xpath("//div[@class='content-layer']//img[@alt='Гадание императрицы Екатерины']"));
        queenKatya.click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedResult);

    }
}
