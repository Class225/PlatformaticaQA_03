import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NoGrValeriyKanTest {
    private static final String URL_NBA_STORE = "https://store.nba.com/";
    private static final By TOP_NAV_MEN = By.id("1");
    private static final By TOP_NAV_WOMEN_XPATH = By.xpath("//a[@aria-label='women']");
    private static final By LIST_ITEM_HEADER_CLASSNAME = By.className("breadcrumb-text");
    private static final By SEARCH_FIELD = By.id("typeahead-input-desktop");
    private static final By SEARCH_RESULT_ITEMS = By.xpath("//a[@data-talos = 'linkSearchResult']");

    private static final String MEN = "MEN";
    private static final String WOMEN = "WOMEN";
    private static final String SEARCH_PRODUCT_NAME = "shorts\n";

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
    public void testValeriyKanMenuMen() throws InterruptedException {
        driver.get(URL_NBA_STORE);

        WebElement menMenu = driver.findElement(TOP_NAV_MEN);
        Thread.sleep(3000);

        menMenu.click();
        Thread.sleep(2000);

        WebElement men = driver.findElement(LIST_ITEM_HEADER_CLASSNAME);
        String actualResult = men.getText();

        Assert.assertEquals(actualResult, MEN);
    }

    @Test
    public void testValeriyKanMenuWomen() throws InterruptedException {
        driver.get(URL_NBA_STORE);

        WebElement womenMenu = driver.findElement(TOP_NAV_WOMEN_XPATH);
        Thread.sleep(3000);

        womenMenu.click();
        Thread.sleep(2000);

        WebElement women = driver.findElement(LIST_ITEM_HEADER_CLASSNAME);

        String actualResult = women.getText();

        Assert.assertEquals(actualResult, WOMEN);
    }

    @Test
    public void testValeriyKanSearch() throws InterruptedException {
        driver.get(URL_NBA_STORE);

        WebElement search = driver.findElement(SEARCH_FIELD);
        Thread.sleep(3000);

        search.sendKeys(SEARCH_PRODUCT_NAME);
        Thread.sleep(3000);

        List<WebElement> searchResult = driver.findElements(SEARCH_RESULT_ITEMS);

        Assert.assertTrue(searchResult.size() >= 1);
    }
}
