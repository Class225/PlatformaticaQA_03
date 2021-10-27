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

public class RuslanMTest {

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
    public void testWeb() {

        driver.get("https://www.dmv.ca.gov/portal/");

        WebElement close = driver.findElement(By.id("dmv-modal__close"));
        close.click();
        WebElement s = driver.findElement(By.id("site-header-search-input"));
        WebElement button = driver.findElement(By.className("site-header__search-submit"));
        s.sendKeys("sqrtef");
        button.click();

        WebElement noresult = driver.findElement(By.className("search-term"));
        Assert.assertEquals(noresult.getText(), "\"sqrtef\"");

    }

    @Test
    public void testWeb2() throws InterruptedException {

        driver.get("https://www.dmv.ca.gov/portal/");

        String expectedResult = "SMOG INSPECTIONS";

        WebElement close = driver.findElement(By.id("dmv-modal__close"));
        close.click();

        WebElement vehicle = driver.findElement(By.id("1086"));
        vehicle.click();

        Thread.sleep(3000);

        WebElement smog = driver.findElement(By.xpath("//li[@id='menu-item-7339']"));
        smog.click();

        Thread.sleep(3000);

        WebElement inspections = driver.findElement(By.xpath("//h1[@class='hero__title']"));
        String actualResult = inspections.getText();

        Thread.sleep(3000);

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testWeb3() {

        driver.get("https://www.dmv.ca.gov/portal/");
        String expectedResult = "次へ";

        WebElement apply = driver.findElement(By.xpath("//a[@id='dmv-modal1__apply-real-id']"));
        apply.click();

        WebElement button = driver.findElement(By.xpath("//button[@class='dmv-modal__close']"));
        button.click();

        WebElement start = driver.findElement(By.xpath("//a[@class='wp-block-button__link']"));
        start.click();

        WebElement language = driver.findElement(By.xpath("//label[@for='language-ja']"));
        language.click();

        WebElement japanese = driver.findElement(By.xpath("//button[@class='arrow-button forward']"));
        String actualResult = japanese.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

}
