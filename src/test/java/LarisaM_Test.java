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

@Ignore
public class LarisaM_Test {

    private WebDriver driver;
    private static final String URL_PLAYGROUND = "http://uitestingplayground.com/";
    private static final String URL_MDN = "https://developer.mozilla.org/en-US/";
    private static final By RESOURCES_LINK_XPATH = By.xpath("//a[@href='/resources']");
    private static final By LINK_ON_SITE_3SCL_XPATH = By.xpath("//a[@href='https://www.w3schools.com']");
    private static final By NAV_BUTTON = By.xpath("//a[@id='navbtn_references']");
    private static final By LINK_ON_SITE_MDN_XPATH = By.xpath("//a[contains(text(),'MDN')]");
    private static final String REFERENCES = "References";

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

        WebElement goResources = driver.findElement(RESOURCES_LINK_XPATH);
        goResources.click();
        WebElement openSite3Scl = driver.findElement(LINK_ON_SITE_3SCL_XPATH);
        openSite3Scl.click();
        WebElement actualResult = driver.findElement(NAV_BUTTON);

        Assert.assertEquals(actualResult.getText(),REFERENCES);
    }

    @Test
    public void testLarisaMalushkinaGetDynamicTable() {

        driver.get(URL_PLAYGROUND);

        WebElement goResources = driver.findElement(RESOURCES_LINK_XPATH);
        goResources.click();
        WebElement openSiteMDN = driver.findElement(LINK_ON_SITE_MDN_XPATH);
        openSiteMDN.click();

        Assert.assertEquals(driver.getCurrentUrl(), URL_MDN);
    }
}

