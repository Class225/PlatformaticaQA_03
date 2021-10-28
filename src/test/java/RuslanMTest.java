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

        WebElement closeButton = driver.findElement(By.id("dmv-modal__close"));
        closeButton.click();

        WebElement searchInput = driver.findElement(By.id("site-header-search-input"));
        WebElement submitButton = driver.findElement(By.className("site-header__search-submit"));
        searchInput.sendKeys("sqrtef");
        submitButton.click();

        WebElement noResult = driver.findElement(By.className("search-term"));

        Assert.assertEquals(noResult.getText(), "\"sqrtef\"");
    }

    @Test
    public void testWeb2() {

        driver.get("https://www.dmv.ca.gov/portal/");

        String expectedResult = "SMOG INSPECTIONS";

        WebElement closeButton = driver.findElement(By.id("dmv-modal__close"));
        closeButton.click();

        WebElement vehicleReg = driver.findElement(By.id("1086"));
        vehicleReg.click();

        WebElement smogInsp = driver.findElement(By.xpath("//li[@id='menu-item-7339']"));
        smogInsp.click();

        WebElement inspectionsTitle = driver.findElement(By.xpath("//h1[@class='hero__title']"));
        String actualResult = inspectionsTitle.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testWeb3() {

        driver.get("https://www.dmv.ca.gov/portal/");
        String expectedResult = "次へ";

        WebElement applyId = driver.findElement(By.xpath("//a[@id='dmv-modal1__apply-real-id']"));
        applyId.click();

        WebElement startApp = driver.findElement(By.xpath("//a[@class='wp-block-button__link']"));
        startApp.click();

        WebElement chooseLanguage = driver.findElement(By.xpath("//label[@for='language-ja']"));
        chooseLanguage.click();

        WebElement japaneseLanguage = driver.findElement(By.xpath("//button[@class='arrow-button forward']/span"));
        String actualResult = japaneseLanguage.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
