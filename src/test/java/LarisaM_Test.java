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

public class LarisaM_Test {

    private WebDriver driver;
    private final String URL_PLAYGROUND = "http://uitestingplayground.com/";
    private final String URL_MDN = "https://developer.mozilla.org/en-US/";
    private final By RESOURCES_LINK_XPATH = By.xpath("//a[@href='/resources']");
    private final By LINK_ON_SITE_3SCL_XPATH = By.xpath("//a[@href='https://www.w3schools.com']");
    private final By NAV_BUTTON = By.xpath("//a[@id='navbtn_references']");
    private final By LINK_ON_SITE_MDN_XPATH = By.xpath("//a[contains(text(),'MDN')]");
    private final String REFERENCES = "References";
    private String expectedResult;

    @BeforeMethod
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown(){

        driver.quit();
    }

    @Test
    public void testLarisaMalushkinaGetElementCheckBox() {

        driver.get(URL_PLAYGROUND);
        expectedResult = REFERENCES;

        WebElement goResources = driver.findElement(RESOURCES_LINK_XPATH);
        goResources.click();
        WebElement openSite3Scl = driver.findElement(LINK_ON_SITE_3SCL_XPATH);
        openSite3Scl.click();
        WebElement actualResult = driver.findElement(NAV_BUTTON);

        Assert.assertEquals(actualResult.getText(),expectedResult);
    }

    @Test
    public void testLarisaMalushkinaGetDynamicTable() {

        driver.get(URL_PLAYGROUND);
        expectedResult = URL_MDN;

        WebElement goResources = driver.findElement(RESOURCES_LINK_XPATH);
        goResources.click();
        WebElement openSiteMDN = driver.findElement(LINK_ON_SITE_MDN_XPATH);
        openSiteMDN.click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedResult);
    }
}

